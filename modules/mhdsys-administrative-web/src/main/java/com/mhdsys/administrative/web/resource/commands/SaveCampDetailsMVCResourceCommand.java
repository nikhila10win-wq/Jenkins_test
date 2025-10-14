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
import com.mhdsys.common.pojo.NCCGrantCommonDTO;
import com.mhdsys.schema.model.NCCGrant;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSNCCSTUDENTLISTWEB,
		"mvc.command.name=" + MhdsysAdministrativeWebPortletKeys.SAVE_CAMP_DETAILS_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SaveCampDetailsMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(SaveCampDetailsMVCResourceCommand.class);

	@Reference
	AdministrativeUtil administrativeUtil;

	@Reference
	AdministrativeCommonApi administrativeCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws PortletException {

	    LOGGER.info("Create NCC Grant Resource Command :::");

	    ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	    long grantId = ParamUtil.getLong(resourceRequest, "nccGrantId");

	    LOGGER.info("GRANT ID ::: " + grantId);

	    try {
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

	        // Set DTO from request
	        NCCGrantCommonDTO grantDTO = administrativeUtil.setNCCGrant(resourceRequest, themeDisplay,grantId);

	        // Save Grant using service
	        NCCGrant savedGrant = administrativeCommonApi.saveNccGrant(grantDTO);
	        LOGGER.info("Saved Grant: " + savedGrant);

	        if (Validator.isNotNull(savedGrant)) {
	            jsonObject.put("grantSaved", true);
	        } else {
	            jsonObject.put("grantSaved", false);
	        }

	        resourceResponse.getWriter().write(jsonObject.toString());

	    } catch (Exception e) {
	        LOGGER.error("Error while saving NCC Grant: ", e);
	    }

	    return false;
	}



}
