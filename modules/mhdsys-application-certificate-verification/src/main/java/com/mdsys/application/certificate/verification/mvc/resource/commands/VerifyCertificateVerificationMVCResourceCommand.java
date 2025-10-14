package com.mdsys.application.certificate.verification.mvc.resource.commands;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys;
import com.mhdsys.application.certificate.verification.util.ApplicationCertificateVerificationUtil;
import com.mhdsys.common.api.certificateVerification.CertificateVerificationCommonApi;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.service.CertificateVerificationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysApplicationCertificateVerificationPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATIONLIST,
		"mvc.command.name="
				+ MhdsysApplicationCertificateVerificationPortletKeys.VERIFYAPPLICATIONCERTIFICATEVERIFICATIONMVCRESOURCECOMMAND }, service = MVCResourceCommand.class)

public class VerifyCertificateVerificationMVCResourceCommand implements MVCResourceCommand{
	private Log LOGGER=LogFactoryUtil.getLog(VerifyCertificateVerificationMVCResourceCommand.class);
	
	@Reference
	ApplicationCertificateVerificationUtil applicationCertificateVerificationUtil;
	
	@Reference
	CertificateVerificationCommonApi certificateVerificationCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
	   LOGGER.info("----------------------Verify Start--------------------------------");
	   try {
		   JSONObject jsonObject = JSONFactoryUtil.createJSONObject(); 
		LOGGER.info( "certificateVerificationId:::::::   "+ ParamUtil.getLong(resourceRequest, "certificateVerificationId"));  
	   long certificateVerificationId=ParamUtil.getLong(resourceRequest, "certificateVerificationId");
	   CertificateVerification certificateVerification=CertificateVerificationLocalServiceUtil.getCertificateVerification(certificateVerificationId);
	   certificateVerification.setCertificateVerificationId(certificateVerificationId);
	   certificateVerification.setApproveReject(ParamUtil.getBoolean(resourceRequest, "approveReject"));
	   certificateVerification.setRemarks(ParamUtil.getString(resourceRequest, "remarks"));
	   ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	   CertificateVerificationCommonDTO certificateVerificationCommonDTO=applicationCertificateVerificationUtil.setCertificateVerificationCommonDTO(certificateVerification, themeDisplay);
	   LOGGER.info("CertificateVerificationCommonDTO====================>"+certificateVerificationCommonDTO);
	   CertificateVerification certificate=certificateVerificationCommonApi.saveCertificateVerification(certificateVerificationCommonDTO);
	   
	   
	   
	   boolean status = Validator.isNotNull(certificate);
       jsonObject.put("status", status);
       if(status)
	    jsonObject.put("certificateVerificationId", certificateVerification.getCertificateVerificationId());


       
       resourceResponse.setContentType("application/json");
       resourceResponse.getWriter().write(jsonObject.toString());
	   }
	   catch (Exception e) {
		LOGGER.info("Verify Error : " + e.getMessage());
		 try {
	            JSONObject errorObject = JSONFactoryUtil.createJSONObject();
	            errorObject.put("status", false);
	            errorObject.put("error", "Server error occurred");
	            resourceResponse.setContentType("application/json");
	            resourceResponse.getWriter().write(errorObject.toString());
	        } catch (Exception exception) {
	            LOGGER.error("Failed to write error response: ", exception);
	        }
	}
	   
	   LOGGER.info("----------------------Verify End--------------------------------");
		return false;
	}

}
