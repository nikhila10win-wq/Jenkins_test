package com.mhdsys.common.api.certificateVerification;

import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.schema.model.CertificateVerification;

public interface CertificateVerificationCommonApi {
	CertificateVerification saveCertificateVerification(CertificateVerificationCommonDTO certificateVerificationCommonDTO);
}
