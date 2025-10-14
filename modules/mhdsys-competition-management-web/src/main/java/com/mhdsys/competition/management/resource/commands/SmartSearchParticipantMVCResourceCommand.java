package com.mhdsys.competition.management.resource.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_SCHEDULED_LIST_MANAGEMENTWEB,

		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.SMART_SEARCH_PARTICIPANT_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class SmartSearchParticipantMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("smart search started");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			String searchName = ParamUtil.getString(resourceRequest, "participantName");
			LOGGER.info("searchName: "+searchName);
//			registration dependency
			List<Object[]> participantNames = SchoolCollegeOfficerRegistrationLocalServiceUtil.getParticipantNames(searchName);
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		    for (Object[] row : participantNames) {
		        String name = (String) row[0]; // fullname
		        long userId = (long) row[1]; // fullname
		        LOGGER.info("participantName: " + name+"userId :"+userId);
		        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		        jsonObj.put("name", name);
		        jsonObj.put("userId", userId);

		        jsonArray.put(jsonObj); 
		    }

		    resourceResponse.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("smart searchended");
		return false;
	}

}
