package com.mhdsys.common.service.registration;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.pwd.PwdToolkitUtil;
import com.mhdsys.common.api.reg.RegistrationCommonApi;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.RegistrationDTO;
import com.mhdsys.common.pojo.RegistrationFormTypeCommonDTO;
import com.mhdsys.common.pojo.SchoolCollegeOfficerRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.util.CompanyUtil;
import com.mhdsys.common.util.UserUtil;
import com.mhdsys.schema.model.Registration;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.model.SportPersonCoachRegistration;
import com.mhdsys.schema.service.RegistrationFormTypeLocalService;
import com.mhdsys.schema.service.RegistrationLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.SportPersonCoachRegistrationLocalServiceUtil;

import java.util.Calendar;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = RegistrationCommonApi.class)
public class RegistrationCommonService implements RegistrationCommonApi {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	private RegistrationFormTypeLocalService registrationTypeLocalService;

	@Reference
	CompanyUtil companyUtil;

	@Override
	public List<RegistrationFormTypeCommonDTO> getAllRegistrationFormTypes() {
		return registrationTypeLocalService.getRegistrationFormTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS).stream()
				.map(entity -> {
					RegistrationFormTypeCommonDTO dto = new RegistrationFormTypeCommonDTO();
					BeanPropertiesUtil.copyProperties(entity, dto);
					return dto;
				}).toList();
	}

	@Override
	public RegistrationFormTypeCommonDTO getByFormTypeName(String formTypeName) {
		RegistrationFormTypeCommonDTO dto = new RegistrationFormTypeCommonDTO();
		BeanPropertiesUtil.copyProperties(registrationTypeLocalService.getByFormTypeName(formTypeName), dto);
		return dto;
	}

	@Override
	public Registration saveRegistration(RegistrationDTO registrationDTO, UserInformationModel userInformationModel,
			String regType) {
		LOGGER.info("userId:::" + userInformationModel.getUserId());
		try {
			User user = createUserInternal(userInformationModel, regType);

			if (Validator.isNotNull(user)) {
				LOGGER.info("User ::: " + user.getUserId());
				LOGGER.info("USER ::: " + user.getFirstName());
			}

			Registration registration = RegistrationLocalServiceUtil
					.createRegistration(CounterLocalServiceUtil.increment(Registration.class.getName()));

			long registrationId = registration.getRegistrationId();
			LOGGER.info("Registration Id : " + registrationId);
			BeanPropertiesUtil.copyProperties(registrationDTO, registration);
			registration.setRegistrationId(registrationId);
			LOGGER.info(registration.getAadharNo() + "Registration Id : " + registrationId);
			return RegistrationLocalServiceUtil.addRegistration(registration);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Reference
	UserUtil userUtil;

//	@Reference
//	EmailNotificationApi emailNotificationApi;
@Override
	public User createUserInternal(UserInformationModel userModule, String regType) {
		if (Validator.isNull(userModule))
			return null;
		User user = null;
		try {
			User defaultuser = companyUtil.getDefaultUser();
			PasswordPolicy defaultPasswordPolicy = PasswordPolicyLocalServiceUtil
					.getDefaultPasswordPolicy(companyUtil.getDefaultCompanyId());
			String generatedPassword = PwdToolkitUtil.generate(defaultPasswordPolicy);
			LOGGER.info("generatePassword:::" + generatedPassword);
			int birthdayMonth = Calendar.JANUARY;
			int birthdayDay = 1;
			int birthdayYear = 1970;
			String jobTitle = StringPool.BLANK;
			long groupId = companyUtil.getSiteGroupId(defaultuser.getCompanyId());
			long[] groupIds = { groupId };
			long[] organizationIds = null;
			long[] roleIds = companyUtil.getRoleId(regType);
			long[] userGroupIds = null;
			LOGGER.info("userModule::::" + userModule);
			user = UserLocalServiceUtil.addUser(defaultuser.getUserId(), defaultuser.getCompanyId(), false,
					generatedPassword, generatedPassword, false, userModule.getScreenName(), userModule.getEmail(),
					LocaleUtil.ENGLISH, userModule.getFirstName(), userModule.getMiddleName(),
					Validator.isNotNull(userModule.getLastName()) ? userModule.getLastName() : "N.A", 0l, 0l, true,
					birthdayMonth, birthdayDay, birthdayYear, jobTitle, 1, groupIds, organizationIds, roleIds,
					userGroupIds, false, new ServiceContext());
			user.setEmailAddressVerified(false);
			user.setStatus(WorkflowConstants.STATUS_APPROVED);
			user.setAgreedToTermsOfUse(true);
			user.setScreenName(user.getScreenName() + user.getUserId());
			user = UserLocalServiceUtil.updateUser(user);
			UserLocalServiceUtil.addPasswordPolicyUsers(defaultPasswordPolicy.getPasswordPolicyId(),
					new long[] { user.getUserId() });
			if (Validator.isNotNull(user)) {
				try {
					LOGGER.info("password::::" + user.getPassword());
					user.setGoogleUserId(generatedPassword);
					LOGGER.info("password::::" + user.getPassword());
					String subject = "Registration Successfull";
					String body = "<p>Hi,&nbsp;<br />\r\n" + "&nbsp;<br />\r\n"
							+ "Greetings of the day!&nbsp;<br />\r\n" + "&nbsp;<br />\r\n"
							+ "You have successfully registered with Maharashtra Director of Sports and Youth Services &nbsp;.&nbsp;<br />\r\n"
							+ "Below are the user credentials - &nbsp;.&nbsp;<br />\r\n" + "User ID : "
							+ user.getScreenName() + "&nbsp;<br />\r\n" + "Password : " + generatedPassword
							+ "<br />\r\n" + "<p><br />\r\n" + "Thanks and Regards,<p><br />\r\n" + "Admin<p><br />\r\n"
							+ "Maharashtra Director of Sports and Youth Services\r\n" + "<p><br />\r\n"
							+ "&nbsp;<br />\r\n" + "&nbsp;<br />\r\n"
							+ "***This is a system generated mail. Please do not reply.***&nbsp;</p>\r\n" + "\r\n"
							+ "<p> </p>\r\n" + "";
//					emailNotificationApi.sendEmail("noreply.mhdsys@gmail.com", user.getEmailAddress(), subject, body);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			}
			return user;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public SchoolCollegeOfficerRegistration saveSchoolCollegeOfficerRegistration(
			SchoolCollegeOfficerRegistrationCommonDTO schoolCollegeOfficerDTO,
			UserInformationModel userInformationModel, User user) {
		try {
//			User user = createUserInternal(userInformationModel, regType);

			if (Validator.isNotNull(user)) {
				LOGGER.info("User ::: " + user.getUserId());
				LOGGER.info("USER ::: " + user.getFirstName());
			}

			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = SchoolCollegeOfficerRegistrationLocalServiceUtil
					.createSchoolCollegeOfficerRegistration(
							CounterLocalServiceUtil.increment(SchoolCollegeOfficerRegistration.class.getName()));

			long schoolCollegeOfficerRegistrationId = schoolCollegeOfficerRegistration
					.getSchoolCollegeOfficerRegistrationId();
			LOGGER.info("schoolCollegeOfficerRegistrationId Id : " + schoolCollegeOfficerRegistrationId);
			BeanPropertiesUtil.copyProperties(schoolCollegeOfficerDTO, schoolCollegeOfficerRegistration);
			//reassigning the userid
			//schoolCollegeOfficerRegistration.setUserId(userInformationModel.getUserId());
			schoolCollegeOfficerRegistration.setUserId(user.getUserId());
			schoolCollegeOfficerRegistration.setEmail(user.getEmailAddress());
			schoolCollegeOfficerRegistration.setSchoolCollegeOfficerRegistrationId(schoolCollegeOfficerRegistrationId);
			return SchoolCollegeOfficerRegistrationLocalServiceUtil
					.addSchoolCollegeOfficerRegistration(schoolCollegeOfficerRegistration);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public SportPersonCoachRegistration saveSportPersonRegistration(AwardApplicationCommonDTO awardApplicationCommonDTO,
			UserInformationModel userInformationModel, String regType) {
		try {
			User user = createUserInternal(userInformationModel, regType);

			if (Validator.isNotNull(user)) {
				LOGGER.info("User ::: " + user.getUserId());
				LOGGER.info("USER ::: " + user.getFirstName());
			}

			SportPersonCoachRegistration sportPersonCoachRegistration = SportPersonCoachRegistrationLocalServiceUtil
					.createSportPersonCoachRegistration(
							CounterLocalServiceUtil.increment(SportPersonCoachRegistration.class.getName()));

			long sportPersonRegistrationId = sportPersonCoachRegistration.getSportPersonRegistrationId();
			LOGGER.info("sportPersonRegistrationId Id : " + sportPersonRegistrationId);
			BeanPropertiesUtil.copyProperties(awardApplicationCommonDTO, sportPersonCoachRegistration);
			sportPersonCoachRegistration.setSportPersonRegistrationId(sportPersonRegistrationId);
			return SportPersonCoachRegistrationLocalServiceUtil
					.addSportPersonCoachRegistration(sportPersonCoachRegistration);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	

}
