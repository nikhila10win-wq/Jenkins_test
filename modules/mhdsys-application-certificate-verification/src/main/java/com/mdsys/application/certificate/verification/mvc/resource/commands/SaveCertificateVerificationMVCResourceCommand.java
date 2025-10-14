package com.mdsys.application.certificate.verification.mvc.resource.commands;
import com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys;
import com.mhdsys.application.certificate.verification.util.ApplicationCertificateVerificationUtil;
import com.mhdsys.common.api.certificateVerification.CertificateVerificationCommonApi;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.schema.model.CertificateVerification;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysApplicationCertificateVerificationPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATION,
		"mvc.command.name="
				+ MhdsysApplicationCertificateVerificationPortletKeys.SAVEAPPLICATIONCERTIFICATEVERIFICATIONMVCRESOURCECOMMAND }, service = MVCResourceCommand.class)
public class SaveCertificateVerificationMVCResourceCommand implements MVCResourceCommand{
	
	
	
    private Log LOGGER=LogFactoryUtil.getLog(SaveCertificateVerificationMVCResourceCommand.class);
    
    @Reference
    ApplicationCertificateVerificationUtil applicationCertificateVerificationUtil;
    
    @Reference
    CertificateVerificationCommonApi certificateVerificationCommonApi;
    
    
    
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("========================== Certificate Verification Save Method Start");
		LOGGER.info(ParamUtil.getString(resourceRequest, "firstName")+"++++++++++++++First Name");
	    ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

	    try {
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	        CertificateVerificationCommonDTO certificateVerificationCommonDTO =
	            applicationCertificateVerificationUtil.setCertificateVerificationCommonDTO(resourceRequest, themeDisplay);

	        LOGGER.info("CertificateVerificationCommonDTO DTO: " + certificateVerificationCommonDTO);

	        CertificateVerification certificateVerification =
	            certificateVerificationCommonApi.saveCertificateVerification(certificateVerificationCommonDTO);
           LOGGER.info("certificateVerification.getCertificateVerificationId():"+certificateVerification.getCertificateVerificationId());
	        boolean status = Validator.isNotNull(certificateVerification);
	        jsonObject.put("status", status);
	        if(status)
	        jsonObject.put("certificateVerificationId", certificateVerification.getCertificateVerificationId());

	        
	        resourceResponse.setContentType("application/json");
	        resourceResponse.getWriter().write(jsonObject.toString());

	    } catch (Exception e) {
	        LOGGER.error("Exception in serveResource: ", e);
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

	    LOGGER.info("========================== Certificate Verification Save Method End");
	    return true;
		
	}
	
}