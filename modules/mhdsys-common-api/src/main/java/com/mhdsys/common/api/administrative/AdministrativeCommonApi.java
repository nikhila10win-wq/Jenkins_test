package com.mhdsys.common.api.administrative;

import com.liferay.portal.kernel.model.User;
import com.mhdsys.common.pojo.EventCertificateCommonDTO;
import com.mhdsys.common.pojo.NCCGrantCommonDTO;
import com.mhdsys.common.pojo.NCCGrantRequestCommonDTO;
import com.mhdsys.common.pojo.ScoutAndGuideRegistrationCommonDTO;
import com.mhdsys.common.pojo.StudentRegistrationCommonDTO;
import com.mhdsys.common.pojo.UnitRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.NCCGrant;
import com.mhdsys.schema.model.NccGrantRequest;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.model.UnitRegistration;

public interface AdministrativeCommonApi {
	UnitRegistration saveUnitRegistration(UnitRegistrationCommonDTO unitRegistrationCommonDTO);
	User createUserInternal(UserInformationModel userModule, String regType);
	ScoutAndGuideRegistration saveScoutAndGuideRegistration(ScoutAndGuideRegistrationCommonDTO scoutAndGuideRegistrationCommonDTO, UserInformationModel userInformationModel,
			User user);
	StudentRegistration saveStudentRegistration(StudentRegistrationCommonDTO studentDTO);
	EventCertificate saveEventCertificate(EventCertificateCommonDTO eventDTO);
	NCCGrant saveNccGrant(NCCGrantCommonDTO grantDTO);
	NccGrantRequest saveNccGrantRequest(NCCGrantRequestCommonDTO grantDTO);
}
