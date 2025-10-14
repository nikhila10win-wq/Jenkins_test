package com.mhdsys.administrative.web.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.api.administrative.AdministrativeCommonApi;
import com.mhdsys.common.pojo.UnitRegistrationCommonDTO;
import com.mhdsys.schema.model.UnitRegistration;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSUNITREGWEB,
		"mvc.command.name=" + MhdsysAdministrativeWebPortletKeys.UNIT_REGISTRATION_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class UnitRegistrationMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(ScoutAndGuideRegistrationMVCResourceCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;
	@Reference
	AdministrativeCommonApi administrativeCommonApi;
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws PortletException {

	    LOGGER.info("Create Unit Registration Resource Command ::: ");

	    ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	    long unitRegistrationId = ParamUtil.getLong(resourceRequest, "unitRegistrationId");

	    LOGGER.info("UNIT REGISTRATION ID ::: " + unitRegistrationId);

	    try {
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

	        // Set DTO from resource request
	        UnitRegistrationCommonDTO unitDTO = administrativeUtil.setUnitRegistration(resourceRequest, themeDisplay);

	        // Save Unit Registration
	        UnitRegistration savedUnit = administrativeCommonApi.saveUnitRegistration(unitDTO);
	        LOGGER.info("Saved Unit Registration: " + savedUnit);

	        if (Validator.isNotNull(savedUnit)) {
	            jsonObject.put("createUnit", true);
	        } else {
	            jsonObject.put("createUnit", false);
	        }

	        resourceResponse.getWriter().write(jsonObject.toString());

	    } catch (Exception e) {
	        LOGGER.error("Error while saving unit registration: ", e);
	    }

	    return false;
	}


}
