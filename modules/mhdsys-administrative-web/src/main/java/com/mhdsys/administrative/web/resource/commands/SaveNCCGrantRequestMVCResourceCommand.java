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
import com.mhdsys.common.pojo.NCCGrantRequestCommonDTO;
import com.mhdsys.schema.model.NccGrantRequest;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSGRANTREQUESTWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADMINISTRATIVEWEB,
		"mvc.command.name=" + MhdsysAdministrativeWebPortletKeys.SAVE_NCC_GRANT_REQUEST_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SaveNCCGrantRequestMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(SaveNCCGrantRequestMVCResourceCommand.class);

	@Reference
	AdministrativeUtil administrativeUtil;

	@Reference
	AdministrativeCommonApi administrativeCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws PortletException {

	    LOGGER.info("Create NCC Grant Request Resource Command :::");

	    ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	    long nccGrantRequestId = ParamUtil.getLong(resourceRequest, "nccGrantRequestId");
	    String status = ParamUtil.getString(resourceRequest, "status");

	    LOGGER.info("GRANT REQUEST ID ::: " + nccGrantRequestId);

	    try {
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

	        // Set DTO from request
	        NCCGrantRequestCommonDTO grantDTO = administrativeUtil.setNccGrantRequest(resourceRequest, themeDisplay,nccGrantRequestId,status);

	        // Save Grant Request using service
	        NccGrantRequest savedRequest = administrativeCommonApi.saveNccGrantRequest(grantDTO);
	        LOGGER.info("Saved Grant Request: " + savedRequest);

	        if (Validator.isNotNull(savedRequest)) {
	            jsonObject.put("grantRequestSaved", true);
	        } else {
	            jsonObject.put("grantRequestSaved", false);
	        }

	        resourceResponse.getWriter().write(jsonObject.toString());

	    } catch (Exception e) {
	        LOGGER.error("Error while saving NCC Grant Request: ", e);
	    }

	    return false;
	}



}
