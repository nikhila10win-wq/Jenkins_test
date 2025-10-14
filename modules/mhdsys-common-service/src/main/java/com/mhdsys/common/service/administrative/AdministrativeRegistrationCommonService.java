package com.mhdsys.common.service.administrative;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
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
import com.mhdsys.common.api.administrative.AdministrativeCommonApi;
import com.mhdsys.common.pojo.EventCertificateCommonDTO;
import com.mhdsys.common.pojo.NCCGrantCommonDTO;
import com.mhdsys.common.pojo.NCCGrantRequestCommonDTO;
import com.mhdsys.common.pojo.ScoutAndGuideRegistrationCommonDTO;
import com.mhdsys.common.pojo.StudentRegistrationCommonDTO;
import com.mhdsys.common.pojo.UnitRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.util.CompanyUtil;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.NCCGrant;
import com.mhdsys.schema.model.NccGrantRequest;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.model.UnitRegistration;
import com.mhdsys.schema.service.EventCertificateLocalServiceUtil;
import com.mhdsys.schema.service.NCCGrantLocalServiceUtil;
import com.mhdsys.schema.service.NccGrantRequestLocalServiceUtil;
import com.mhdsys.schema.service.ScoutAndGuideRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.UnitRegistrationLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = AdministrativeCommonApi.class)

public class AdministrativeRegistrationCommonService implements AdministrativeCommonApi{
//	@Reference
//	EmailNotificationApi emailNotificationApi;
	@Reference
	CompanyUtil companyUtil;
	private static Log LOGGER = LogFactoryUtil.getLog(AdministrativeRegistrationCommonService.class);
	@Override
	public UnitRegistration saveUnitRegistration(UnitRegistrationCommonDTO unitDTO) {
	    try {
	        LOGGER.info("UNIT REGISTRATION DTO ::: " + unitDTO);

	        if (unitDTO.getUnitRegistrationId() > 0) {
	            LOGGER.info("UPDATE MODE");

	            UnitRegistration unit = UnitRegistrationLocalServiceUtil.getUnitRegistration(unitDTO.getUnitRegistrationId());
	            long unitId = unit.getUnitRegistrationId();
	            LOGGER.info("Unit ID : " + unitId);

	            BeanPropertiesUtil.copyProperties(unitDTO, unit);
	            unit.setUnitRegistrationId(unitId);
	            unit.setModifiedDate(new Date());

	            return UnitRegistrationLocalServiceUtil.updateUnitRegistration(unit);
	        } else {
	            UnitRegistration unit = UnitRegistrationLocalServiceUtil.createUnitRegistration(
	                CounterLocalServiceUtil.increment(UnitRegistration.class.getName())
	            );

	            long unitId = unit.getUnitRegistrationId();
	            LOGGER.info("Generated Unit ID : " + unitId);

	            BeanPropertiesUtil.copyProperties(unitDTO, unit);
	            unit.setUnitRegistrationId(unitId);
	            unit.setCreateDate(new Date());
	            unit.setModifiedDate(new Date());

	            return UnitRegistrationLocalServiceUtil.addUnitRegistration(unit);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving Unit Registration: ", e);
	    }

	    return null;
	}
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
	public ScoutAndGuideRegistration saveScoutAndGuideRegistration(
			ScoutAndGuideRegistrationCommonDTO scoutAndGuideRegistrationCommonDTO,
			UserInformationModel userInformationModel, User user) {
	    try {
	        if (Validator.isNotNull(user)) {
	            LOGGER.info("User ::: " + user.getUserId());
	            LOGGER.info("USER ::: " + user.getFirstName());
	        }

	        ScoutAndGuideRegistration scoutRegistration = ScoutAndGuideRegistrationLocalServiceUtil
	                .createScoutAndGuideRegistration(
	                        CounterLocalServiceUtil.increment(ScoutAndGuideRegistration.class.getName()));

	        long scoutAndGuideRegistrationId = scoutRegistration.getScoutAndGuideRegistrationId();
	        LOGGER.info("ScoutAndGuideRegistrationId : " + scoutAndGuideRegistrationId);

	        BeanPropertiesUtil.copyProperties(scoutAndGuideRegistrationCommonDTO, scoutRegistration);

	        // Assign user info
	        scoutRegistration.setUserId(user.getUserId());
	        scoutRegistration.setEmailId(user.getEmailAddress());
	        scoutRegistration.setScoutAndGuideRegistrationId(scoutAndGuideRegistrationId);

	        return ScoutAndGuideRegistrationLocalServiceUtil.addScoutAndGuideRegistration(scoutRegistration);

	    } catch (Exception e) {
	        LOGGER.error("Error while saving ScoutAndGuideRegistration: ", e);
	    }

	    return null;
	}
	@Override
	public StudentRegistration saveStudentRegistration(StudentRegistrationCommonDTO studentDTO) {
	    try {
	        LOGGER.info("STUDENT REGISTRATION DTO ::: " + studentDTO);

	        if (studentDTO.getStudentRegistrationId() > 0) {
	            LOGGER.info("UPDATE MODE");

	            StudentRegistration student = StudentRegistrationLocalServiceUtil.getStudentRegistration(studentDTO.getStudentRegistrationId());
	            long studentId = student.getStudentRegistrationId();
	            LOGGER.info("Student ID : " + studentId);

	            BeanPropertiesUtil.copyProperties(studentDTO, student);
	            student.setStudentRegistrationId(studentId);
	            student.setModifiedDate(new Date());

	            return StudentRegistrationLocalServiceUtil.updateStudentRegistration(student);
	        } else {
	            StudentRegistration student = StudentRegistrationLocalServiceUtil.createStudentRegistration(
	                CounterLocalServiceUtil.increment(StudentRegistration.class.getName())
	            );

	            long studentId = student.getStudentRegistrationId();
	            LOGGER.info("Generated Student ID : " + studentId);

	            BeanPropertiesUtil.copyProperties(studentDTO, student);
	            student.setStudentRegistrationId(studentId);

	            return StudentRegistrationLocalServiceUtil.addStudentRegistration(student);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving Student Registration: ", e);
	    }

	    return null;
	}
	@Override
	public EventCertificate saveEventCertificate(EventCertificateCommonDTO eventDTO) {
	    try {
	        LOGGER.info("EVENT CERTIFICATE DTO ::: " + eventDTO);

	        if (eventDTO.getEventCertificateId() > 0) {
	            LOGGER.info("UPDATE MODE");

	            EventCertificate event = EventCertificateLocalServiceUtil.getEventCertificate(eventDTO.getEventCertificateId());
	            long eventId = event.getEventCertificateId();
	            LOGGER.info("Event ID : " + eventId);

	            BeanPropertiesUtil.copyProperties(eventDTO, event);
	            event.setEventCertificateId(eventId);
	            event.setModifiedDate(new Date());

	            return EventCertificateLocalServiceUtil.updateEventCertificate(event);
	        } else {
	            EventCertificate event = EventCertificateLocalServiceUtil.createEventCertificate(
	                CounterLocalServiceUtil.increment(EventCertificate.class.getName())
	            );

	            long eventId = event.getEventCertificateId();
	            LOGGER.info("Generated Event ID : " + eventId);

	            BeanPropertiesUtil.copyProperties(eventDTO, event);
	            event.setEventCertificateId(eventId);
	            event.setCreateDate(new Date());
	            event.setModifiedDate(new Date());

	            return EventCertificateLocalServiceUtil.addEventCertificate(event);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving Event Certificate: ", e);
	    }

	    return null;
	}
	@Override
	public NCCGrant saveNccGrant(NCCGrantCommonDTO grantDTO) {
	    try {
	        LOGGER.info("NCC GRANT DTO ::: " + grantDTO);

	        if (grantDTO.getNccGrantId() > 0) {
	            LOGGER.info("UPDATE MODE");

	            NCCGrant grant = NCCGrantLocalServiceUtil.getNCCGrant(grantDTO.getNccGrantId());
	            long grantId = grant.getNccGrantId();
	            LOGGER.info("Grant ID : " + grantId);

	            BeanPropertiesUtil.copyProperties(grantDTO, grant);
	            grant.setNccGrantId(grantId);
	            grant.setModifiedDated(new Date());

	            return NCCGrantLocalServiceUtil.updateNCCGrant(grant);
	        } else {
	            NCCGrant grant = NCCGrantLocalServiceUtil.createNCCGrant(
	                CounterLocalServiceUtil.increment(NCCGrant.class.getName())
	            );

	            long grantId = grant.getNccGrantId();
	            LOGGER.info("Generated Grant ID : " + grantId);

	            BeanPropertiesUtil.copyProperties(grantDTO, grant);
	            grant.setNccGrantId(grantId);
	            grant.setCreateDate(new Date());
	            grant.setModifiedDated(new Date());

	            return NCCGrantLocalServiceUtil.addNCCGrant(grant);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving NCC Grant: ", e);
	    }

	    return null;
	}

	@Override
	public NccGrantRequest saveNccGrantRequest(NCCGrantRequestCommonDTO grantDTO) {
	    try {
	        LOGGER.info("NCC GRANT REQUEST DTO ::: " + grantDTO);

	        if (grantDTO.getNccGrantRequestId() > 0) {
	            LOGGER.info("UPDATE MODE");

	            NccGrantRequest grant = NccGrantRequestLocalServiceUtil.getNccGrantRequest(grantDTO.getNccGrantRequestId());
	            long grantRequestId = grant.getNccGrantRequestId();
	            LOGGER.info("Grant Request ID : " + grantRequestId);

	            BeanPropertiesUtil.copyProperties(grantDTO, grant);
	            grant.setNccGrantRequestId(grantRequestId);
	            grant.setModifiedDate(new Date());

	            return NccGrantRequestLocalServiceUtil.updateNccGrantRequest(grant);
	        } else {
	            NccGrantRequest grant = NccGrantRequestLocalServiceUtil.createNccGrantRequest(
	                CounterLocalServiceUtil.increment(NccGrantRequest.class.getName())
	            );

	            long grantRequestId = grant.getNccGrantRequestId();
	            LOGGER.info("Generated Grant Request ID : " + grantRequestId);

	            BeanPropertiesUtil.copyProperties(grantDTO, grant);
	            grant.setNccGrantRequestId(grantRequestId);
	            grant.setUserId(grantDTO.getUserId());
	            grant.setCreateDate(new Date());
	            grant.setModifiedDate(new Date());

	            return NccGrantRequestLocalServiceUtil.addNccGrantRequest(grant);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving NCC Grant Request: ", e);
	    }

	    return null;
	}

}
