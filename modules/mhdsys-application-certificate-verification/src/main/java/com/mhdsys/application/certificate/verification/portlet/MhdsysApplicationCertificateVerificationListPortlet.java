package com.mhdsys.application.certificate.verification.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.service.CertificateVerificationLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysApplicationCertificateVerificationList",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/certificate-verification-list.jsp",
		"javax.portlet.name=" + MhdsysApplicationCertificateVerificationPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATIONLIST,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.security-role-ref=power-user,user"

		 }, service = Portlet.class)



public class MhdsysApplicationCertificateVerificationListPortlet extends MVCPortlet{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysApplicationCertificateVerificationListPortlet.class);
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
		boolean  isDDD= RoleConstant.isDDD(user, themeDisplay.getCompanyId());
		/*
		 * if (!isDDD) { HttpServletResponse httpServletResponse =
		 * PortalUtil.getHttpServletResponse(renderResponse); String redirectURL =
		 * themeDisplay.getPortalURL() + "/group/guest/dashboard";
		 * httpServletResponse.sendRedirect(redirectURL); return; // Stop further
		 * rendering }
		 */
		List<CertificateVerification>certificateList=CertificateVerificationLocalServiceUtil.getCertificateVerifications(-1, -1);
		renderRequest.setAttribute("mode", "view");
		renderRequest.setAttribute("certificateList", certificateList);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		super.render(renderRequest, renderResponse);
	}

}
