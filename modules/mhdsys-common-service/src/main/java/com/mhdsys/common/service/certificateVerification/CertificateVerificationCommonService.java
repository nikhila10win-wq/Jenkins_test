package com.mhdsys.common.service.certificateVerification;



import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.certificateVerification.CertificateVerificationCommonApi;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.service.CertificateVerificationLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true, 
    service = CertificateVerificationCommonApi.class // Changed to match implemented interface
)
public class CertificateVerificationCommonService implements CertificateVerificationCommonApi {

    private static final Log _log = LogFactoryUtil.getLog(CertificateVerificationCommonService.class);

    @Override
    public CertificateVerification saveCertificateVerification(CertificateVerificationCommonDTO certificateVerificationCommonDTO) {
        try {
            _log.info("CERTIFICATE VERIFICATION SERVICE :::::: ");

            if (certificateVerificationCommonDTO.getCertificateVerificationId() > 0) {
                _log.info("UPDATE / EDIT ::::::::::::: ");
                CertificateVerification certificateVerification = CertificateVerificationLocalServiceUtil
                        .getCertificateVerification(certificateVerificationCommonDTO.getCertificateVerificationId());

                long certificateVerificationId = certificateVerificationCommonDTO.getCertificateVerificationId();
                _log.info("certificateVerificationId : " + certificateVerificationId);
                BeanPropertiesUtil.copyProperties(certificateVerificationCommonDTO, certificateVerification);
                certificateVerification.setCertificateVerificationId(certificateVerificationId);

                return CertificateVerificationLocalServiceUtil.updateCertificateVerification(certificateVerification);
            } else {
                CertificateVerification certificateVerification = CertificateVerificationLocalServiceUtil
                        .createCertificateVerification(CounterLocalServiceUtil.increment(CertificateVerification.class.getName()));

                long certificateVerificationId = certificateVerification.getCertificateVerificationId();
                _log.info("certificateVerificationId : " + certificateVerificationId);
                BeanPropertiesUtil.copyProperties(certificateVerificationCommonDTO, certificateVerification);
                certificateVerification.setCertificateVerificationId(certificateVerificationId);

                return CertificateVerificationLocalServiceUtil.addCertificateVerification(certificateVerification);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to save certificate verification", e);
        }
    }
}