package com.mdsys.application.certificate.verification.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys;
import com.mhdsys.application.certificate.verification.util.ApplicationCertificateVerificationUtil;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.service.CertificateVerificationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
@Component(immediate = true, property = {
		"javax.portlet.name="
				+ MhdsysApplicationCertificateVerificationPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATIONLIST,
		"mvc.command.name="
				+ MhdsysApplicationCertificateVerificationPortletKeys.APPLICATION_CERTIFICATE_VERIFICATION_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class CertificateVerificationMVCRenderCommand implements MVCRenderCommand{
	
	@Reference
	ApplicationCertificateVerificationUtil applicationCertificateVerificationUtil;
	
	private Log LOGGER=LogFactoryUtil.getLog(CertificateVerificationMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
		boolean isDDD = RoleConstant.isDDD(user, themeDisplay.getCompanyId());
		
		LOGGER.info("MODE:=============:"+ParamUtil.getString(renderRequest, "mode"));
		LOGGER.info("isDDD:=============:"+isDDD);
		
		long certificateVerificationId=ParamUtil.getLong(renderRequest, "certificateVerificationId");
		CertificateVerification certificateVerification=CertificateVerificationLocalServiceUtil.getCertificateVerification(certificateVerificationId);
		CertificateVerificationCommonDTO certificateVerificationCommonDTO=applicationCertificateVerificationUtil.setCertificateVerificationCommonDTO(certificateVerification, themeDisplay);
		LOGGER.info("CertificateVerificationCommonDTO: "+certificateVerificationCommonDTO);
		renderRequest.setAttribute("verificationCommonDTO", certificateVerificationCommonDTO);
		renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "mode"));
		renderRequest.setAttribute("isDDD", isDDD);
		}
		catch (Exception e) {
			
		}
		return "/view.jsp";
	}

}
