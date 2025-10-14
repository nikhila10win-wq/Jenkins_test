package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.service.EventCertificateLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSEVENTDETAILSWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSEVENTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.EVENT_DETAILS_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class EventDetailsMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(EventDetailsMVCRenderCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String mode = ParamUtil.getString(renderRequest, "mode");
			long eventCertificateId = ParamUtil.getLong(renderRequest, "eventCertificateId");
			LOGGER.info("eventCertificateId: " + eventCertificateId);
			EventCertificate eventCertificate = EventCertificateLocalServiceUtil
					.getEventCertificate(eventCertificateId);
			renderRequest.setAttribute("eventCertificate",
					administrativeUtil.setEventCertificateCommonDTO(eventCertificate, themeDisplay));
			renderRequest.setAttribute("mode",mode);
		} catch (Exception e) {
		}
		return "/scout-and-guide/event-details.jsp";
	}

}
