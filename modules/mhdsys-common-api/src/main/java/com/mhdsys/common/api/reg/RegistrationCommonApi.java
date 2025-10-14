package com.mhdsys.common.api.reg;

import com.liferay.portal.kernel.model.User;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.RegistrationDTO;
import com.mhdsys.common.pojo.RegistrationFormTypeCommonDTO;
import com.mhdsys.common.pojo.SchoolCollegeOfficerRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.schema.model.Registration;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.model.SportPersonCoachRegistration;

import java.util.List;

public interface RegistrationCommonApi {
	List<RegistrationFormTypeCommonDTO> getAllRegistrationFormTypes();

	RegistrationFormTypeCommonDTO getByFormTypeName(String formTypeName);

	Registration saveRegistration(RegistrationDTO registrationDTO, UserInformationModel userInformationModel, String regType);
	SchoolCollegeOfficerRegistration saveSchoolCollegeOfficerRegistration(SchoolCollegeOfficerRegistrationCommonDTO schoolCollegeOfficerDTO, UserInformationModel userInformationModel,
			User user);
	SportPersonCoachRegistration saveSportPersonRegistration(AwardApplicationCommonDTO awardApplicationCommonDTO, UserInformationModel userInformationModel,String regTyep);
	User createUserInternal(UserInformationModel userModule, String regType);
}
