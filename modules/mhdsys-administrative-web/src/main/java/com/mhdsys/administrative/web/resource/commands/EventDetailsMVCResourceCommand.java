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
import com.mhdsys.common.pojo.EventCertificateCommonDTO;
import com.mhdsys.schema.model.EventCertificate;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSEVENTDETAILSWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.EVENT_DETAILS_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class EventDetailsMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(EventDetailsMVCResourceCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;
	@Reference
	AdministrativeCommonApi administrativeCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Create Event Certificate Resource Command ::: ");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long eventCertificateId = ParamUtil.getLong(resourceRequest, "eventCertificateId");

		LOGGER.info("EVENT CERTIFICATE ID ::: " + eventCertificateId);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			// Set DTO from resource request
			EventCertificateCommonDTO eventDTO = administrativeUtil.setEventCertificate(resourceRequest, themeDisplay);

			// Save Event Certificate
			EventCertificate savedEvent = administrativeCommonApi.saveEventCertificate(eventDTO);
			LOGGER.info("Saved Event Certificate: " + savedEvent);

			if (Validator.isNotNull(savedEvent)) {
				jsonObject.put("createEvent", true);
			} else {
				jsonObject.put("createEvent", false);
			}

			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error("Error while saving event certificate: ", e);
		}

		return false;
	}

}
