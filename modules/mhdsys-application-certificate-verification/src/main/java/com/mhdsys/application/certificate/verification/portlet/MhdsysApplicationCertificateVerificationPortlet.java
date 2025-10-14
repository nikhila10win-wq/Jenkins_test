package com.mhdsys.application.certificate.verification.portlet;


import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider.Option;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys;
import com.mhdsys.application.certificate.verification.util.ApplicationCertificateVerificationUtil;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.CertificateVerificationLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author USER
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysApplicationCertificateVerification",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MhdsysApplicationCertificateVerificationPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATION,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MhdsysApplicationCertificateVerificationPortlet extends MVCPortlet {
	@Reference
	ApplicationCertificateVerificationUtil applicationCertificateVerificationUtil;
	
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysApplicationCertificateVerificationPortlet.class);
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
		boolean isSportsPerson = RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId());
		/*
		 * if (!isSportsPerson) { HttpServletResponse httpServletResponse =
		 * PortalUtil.getHttpServletResponse(renderResponse); String redirectURL =
		 * themeDisplay.getPortalURL() + "/group/guest/dashboard";
		 * httpServletResponse.sendRedirect(redirectURL); return; // Stop further
		 * rendering }
		 */
		  
		 CertificateVerification certificateVerification=CertificateVerificationLocalServiceUtil.findByUserId(themeDisplay.getUserId());
		 if(Validator.isNotNull(certificateVerification)) {
			 LOGGER.info("=====>Data Already Present=====================================================================================>");
			 CertificateVerificationCommonDTO certificateVerificationCommonDTO=applicationCertificateVerificationUtil.setCertificateVerificationCommonDTO(certificateVerification, themeDisplay);
			 LOGGER.info("CertificateVerificationCommonDTO:::::::::::::"+certificateVerificationCommonDTO);
		     renderRequest.setAttribute("verificationCommonDTO", certificateVerificationCommonDTO);
		     renderRequest.setAttribute("mode", "view");
		 }else {
			 LOGGER.info("=====>Data Not Present==============================================================================================>");
			
			 SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = fetchSportsPersonBaseOnUserIdAndCurrentDesignation(themeDisplay.getUserId());

			    if (schoolCollegeOfficerRegistration != null) {
			        certificateVerification = CertificateVerificationLocalServiceUtil.createCertificateVerification(0);
			        Optional<Profile> profile=ProfileLocalServiceUtil.getByUserId(themeDisplay.getUserId());
			        BeanPropertiesUtil.copyProperties(schoolCollegeOfficerRegistration, certificateVerification);
			        certificateVerification.setDateOfBirth(profile.get().getDateOfBirth());
			        LOGGER.info("certificateVerification"+certificateVerification);
			        CertificateVerificationCommonDTO certificateVerificationCommonDTO = applicationCertificateVerificationUtil.setCertificateVerificationCommonDTO(certificateVerification, themeDisplay);
			        renderRequest.setAttribute("verificationCommonDTO", certificateVerificationCommonDTO);
			        
			    } 
			    renderRequest.setAttribute("mode", "add");
		 }
		 renderRequest.setAttribute("isSportsPerson", isSportsPerson);
		super.render(renderRequest, renderResponse);
		}
		catch (Exception e) {
			LOGGER.info(e);
		}
		
	}
	
	private SchoolCollegeOfficerRegistration fetchSportsPersonBaseOnUserIdAndCurrentDesignation(long userId) {
		DynamicQuery dynamicQuery = SchoolCollegeOfficerRegistrationLocalServiceUtil.dynamicQuery();
		dynamicQuery
		    .add(RestrictionsFactoryUtil.eq("currentDesignation", 1L))
		    .add(RestrictionsFactoryUtil.eq("userId", userId));

		List<SchoolCollegeOfficerRegistration> results = SchoolCollegeOfficerRegistrationLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (!results.isEmpty()) {
			return results.get(0); 
		}
		return null;
	}
}