package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.schema.model.Registration;
import com.mhdsys.schema.service.RegistrationLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSCHOOLCOLLEGEOFFICERWEB,
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,

		"mvc.command.name="
				+ RegistrationWebPortletKeys.SMART_SEARCH_SCHOOL_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class SmartSearchSchoolMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("smart search started");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			String searchName = ParamUtil.getString(resourceRequest, "schoolOrCollegeName");
			LOGGER.info("searchName: "+searchName);
			List<Registration> registrations = findSchoolsByNameAndRegNo(searchName);
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
	        for (Registration reg : registrations) {
	            JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
	            jsonObj.put("schoolName", reg.getFirstName());
	            jsonArray.put(jsonObj);
	        }
		    resourceResponse.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("smart searchended");
		return false;
	}
	public List<Registration> findSchoolsByNameAndRegNo(String searchName) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			    Registration.class, Registration.class.getClassLoader());
	    // schoolOrCollegeName like %searchName%
	    if (Validator.isNotNull(searchName)) {
	        dynamicQuery.add(
	            RestrictionsFactoryUtil.ilike("firstName", "%" + searchName + "%")
	        );
	    }

	    // schoolRegNo is not null
	    dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("schoolRegNo"));
	    List<Registration> registrations = RegistrationLocalServiceUtil.dynamicQuery(dynamicQuery);

	    for (Registration reg : registrations) {
	        LOGGER.info("Found School: " + reg.getFirstName());
	    }
	    return RegistrationLocalServiceUtil.dynamicQuery(dynamicQuery);
	   
	}


}
