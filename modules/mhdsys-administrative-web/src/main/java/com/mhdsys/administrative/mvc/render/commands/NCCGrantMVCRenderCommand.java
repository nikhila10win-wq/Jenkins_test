package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.NCCGrant;
import com.mhdsys.schema.service.EventCertificateLocalServiceUtil;
import com.mhdsys.schema.service.NCCGrantLocalServiceUtil;
import com.mhdsys.schema.service.UnitRegistrationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADDGRANTWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSGRANTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.NCC_GRANT_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class NCCGrantMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(NCCGrantMVCRenderCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String mode = ParamUtil.getString(renderRequest, "mode");
			long nccGrantId = ParamUtil.getLong(renderRequest, "nccGrantId");
			LOGGER.info("nccGrantId: " + nccGrantId);
			NCCGrant nccGrant = NCCGrantLocalServiceUtil.getNCCGrant(nccGrantId);
			renderRequest.setAttribute("nccGrant",administrativeUtil.setNCCGrantCommonDTO(nccGrant, themeDisplay));
			renderRequest.setAttribute("unitReg", UnitRegistrationLocalServiceUtil.getUnitRegistrations(-1, -1));
//			renderRequest.setAttribute("eventCertificate",
//					administrativeUtil.setEventCertificateCommonDTO(eventCertificate, themeDisplay));
			if(Validator.isNotNull(nccGrant)) {
				if(nccGrant.getStatus().equalsIgnoreCase("Draft")) {
					mode ="edit";

				}
			}
			renderRequest.setAttribute("mode",mode);
		} catch (Exception e) {
		}
		return "/ncc/unit-details.jsp";
	}

}
