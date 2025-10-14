package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.NccGrantRequest;
import com.mhdsys.schema.service.NccGrantRequestLocalServiceUtil;
import com.mhdsys.schema.service.UnitRegistrationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSGRANTREQUESTWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADMINISTRATIVEWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.NCC_GRANT_REQUEST_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class NCCGrantRequestMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(NCCGrantRequestMVCRenderCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			String mode = ParamUtil.getString(renderRequest, "mode");
			long nccGrantRequestId = ParamUtil.getLong(renderRequest, "nccGrantRequestId");
			LOGGER.info("nccGrantRequestId: " + nccGrantRequestId);
			NccGrantRequest nccGrantRequest = NccGrantRequestLocalServiceUtil.getNccGrantRequest(nccGrantRequestId);
			renderRequest.setAttribute("unitReg", UnitRegistrationLocalServiceUtil.getUnitRegistrations(-1, -1));
			renderRequest.setAttribute("nccGrant", nccGrantRequest);
//			renderRequest.setAttribute("eventCertificate",
//					administrativeUtil.setEventCertificateCommonDTO(eventCertificate, themeDisplay));
			renderRequest.setAttribute("nccGrantRequestId", nccGrantRequestId);
			renderRequest.setAttribute("isDeskOfficer", RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			if (Validator.isNotNull(nccGrantRequest)) {
				if (nccGrantRequest.getStatus().equalsIgnoreCase("Draft")) {
					mode = "edit";

				}
			}
			renderRequest.setAttribute("mode", mode);
		} catch (Exception e) {
		}
		return "/ncc/grant-request.jsp";
	}

}
