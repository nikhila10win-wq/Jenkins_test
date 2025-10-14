package com.mhdsys.registration.web.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.RegistrationDTO;
import com.mhdsys.common.pojo.SchoolCollegeOfficerRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.OfficerDesignation;
import com.mhdsys.schema.service.OfficerDesignationLocalServiceUtil;

import java.util.Date;
import java.util.Map;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = RegistrationUtil.class)
public class RegistrationUtil {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	DateConversionUtil dateConversionUtil;
	@Reference
	FileUploadUtil fileUploadUtil;

	private static final Map<String, String> REG_TYPE = Map.of("SchoolCollege", "MHSCHL", "SportsAssociation", "MHSPAS",
			"SportsComplex", "MHSPCO", "YouthInstitute", "MHYTIN", "Desk Officer", "MHSDDO", "LocalSelfGov", "MHLSGV",
			"Sport Person", "MHSPTP", "Coach", "MHSPTC");

	public UserInformationModel setUserInformationModule(ResourceRequest resourceRequest, String regType) {

		UserInformationModel userInformationModel = new UserInformationModel();
		try {
			LOGGER.info("reg type: " + regType);

			if (regType.equalsIgnoreCase("Desk Officer")) {
				userInformationModel.setFirstName(ParamUtil.getString(resourceRequest, "departmentName"));
			} else if (regType.equalsIgnoreCase("Association")) {
				userInformationModel.setFirstName(ParamUtil.getString(resourceRequest, "associationName"));
			} else {
				userInformationModel.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			}

			userInformationModel.setLastName(".");
			userInformationModel.setEmail(ParamUtil.getString(resourceRequest, "email"));
			userInformationModel.setScreenName(REG_TYPE.getOrDefault(regType, "Default"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return userInformationModel;
	}

	public UserInformationModel setInformationModule(ResourceRequest resourceRequest, String regType) {

		UserInformationModel userInformationModel = new UserInformationModel();
		try {
			LOGGER.info("reg type: " + regType);
			userInformationModel.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			userInformationModel.setLastName(".");
			userInformationModel.setEmail(ParamUtil.getString(resourceRequest, "email"));
			userInformationModel.setScreenName(REG_TYPE.getOrDefault(regType, "Default"));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return userInformationModel;
	}

	public RegistrationDTO setRegistrationDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {

		RegistrationDTO registrationDTO = new RegistrationDTO();
		try {
			LOGGER.info("Registration Util ::: ");
			registrationDTO.setState(ParamUtil.getInteger(resourceRequest, "state"));
			registrationDTO.setDivision(ParamUtil.getInteger(resourceRequest, "division"));
			registrationDTO.setDistrict(ParamUtil.getInteger(resourceRequest, "district"));
			registrationDTO.setTaluka(ParamUtil.getInteger(resourceRequest, "taluka"));
			registrationDTO.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			registrationDTO.setPrincipalName(ParamUtil.getString(resourceRequest, "principalName"));
			registrationDTO.setSportsTeacher(ParamUtil.getString(resourceRequest, "sportsTeacher"));
			registrationDTO.setSchoolRegNo(ParamUtil.getString(resourceRequest, "schoolRegNo"));
			registrationDTO.setUdiseCode(ParamUtil.getString(resourceRequest, "udiseCode"));

			registrationDTO.setAadharNo(ParamUtil.getString(resourceRequest, "aadharNo"));
			registrationDTO.setMobileNo(ParamUtil.getString(resourceRequest, "mobileNo"));
			registrationDTO.setLandlineNo(ParamUtil.getString(resourceRequest, "landlineNo"));
			registrationDTO.setEmail(ParamUtil.getString(resourceRequest, "email"));
			registrationDTO.setWebsite(ParamUtil.getString(resourceRequest, "website"));

			registrationDTO.setDepartmentName(ParamUtil.getString(resourceRequest, "departmentName"));
			registrationDTO.setSubDepartment(ParamUtil.getString(resourceRequest, "subDepartment"));
			registrationDTO.setOfficialName(ParamUtil.getString(resourceRequest, "officialName"));
			registrationDTO.setDesignation(ParamUtil.getString(resourceRequest, "designation"));
			registrationDTO.setSecretaryOrgName(ParamUtil.getString(resourceRequest, "secretaryOrgName"));
			registrationDTO.setOrganizationName(ParamUtil.getString(resourceRequest, "organizationName"));
			String foundingDateStr = ParamUtil.getString(resourceRequest, "foundingDate");
			if (Validator.isNotNull(foundingDateStr)) {
				registrationDTO.setFoundingDate(dateConversionUtil.convertStringToDateFormat(foundingDateStr));
			}
			registrationDTO.setSecretaryName(ParamUtil.getString(resourceRequest, "secretaryName"));
			registrationDTO.setAssociationRegNo(ParamUtil.getString(resourceRequest, "associationRegNo"));
			registrationDTO.setAssociationName(ParamUtil.getString(resourceRequest, "associationName"));
			registrationDTO.setEmployeeId(ParamUtil.getLong(resourceRequest, "employeeId"));

			registrationDTO.setCreatedDate(new Date());
			registrationDTO.setModifiedDate(new Date());
			registrationDTO.setUserId(themeDisplay.getUserId());
			LOGGER.info("Registration obj: " + registrationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return registrationDTO;

	}

	public SchoolCollegeOfficerRegistrationCommonDTO setSchoolCollegeOfficerReg(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {

		SchoolCollegeOfficerRegistrationCommonDTO schoolCollegeOfficer = new SchoolCollegeOfficerRegistrationCommonDTO();
		try {
			LOGGER.info("schoolCollegeOfficer Util ::: ");
			schoolCollegeOfficer.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			schoolCollegeOfficer.setLastName(ParamUtil.getString(resourceRequest, "lastName"));
			LOGGER.info("email: " + ParamUtil.getString(resourceRequest, "email"));
			schoolCollegeOfficer.setEmailId(ParamUtil.getString(resourceRequest, "email"));
			schoolCollegeOfficer.setMothersName(ParamUtil.getString(resourceRequest, "mothersName"));
			schoolCollegeOfficer.setFathersName(ParamUtil.getString(resourceRequest, "fathersName"));
			schoolCollegeOfficer.setCurrentDesignation(ParamUtil.getLong(resourceRequest, "currentDesignation"));
			schoolCollegeOfficer.setGender(ParamUtil.getLong(resourceRequest, "gender"));
			schoolCollegeOfficer.setSchoolOrCollegeName(ParamUtil.getString(resourceRequest, "schoolOrCollegeName"));

			schoolCollegeOfficer.setAadharNumber(ParamUtil.getString(resourceRequest, "aadharNumber"));
			schoolCollegeOfficer.setMobileNumber(ParamUtil.getString(resourceRequest, "mobileNumber"));
			schoolCollegeOfficer.setType(ParamUtil.getString(resourceRequest, "type"));
			schoolCollegeOfficer.setCreateDate(new Date());
			schoolCollegeOfficer.setModifiedDate(new Date());
			schoolCollegeOfficer.setUserId(themeDisplay.getUserId());
			schoolCollegeOfficer.setCurrentDesignationName(Validator.isNotNull(OfficerDesignationLocalServiceUtil
					.getOfficerDesignation(schoolCollegeOfficer.getCurrentDesignation()))
							? OfficerDesignationLocalServiceUtil
									.getOfficerDesignation(schoolCollegeOfficer.getCurrentDesignation()).getName()
							: "");
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long aadharaadharCardRecieptId = fileUploadUtil.uploadFile(uploadPortletRequest, "aadharCardReciept",
					CommonUtilityConstant.REGISTRATION_FOLDER, serviceContext);
			LOGGER.info("aadharaadharCardRecieptId: " + aadharaadharCardRecieptId);
			schoolCollegeOfficer.setAadharCardRecieptFileEntryId(aadharaadharCardRecieptId);
//			fileUploadUtil.setFilePermissionByRoleName(aadharaadharCardRecieptId, serviceContext, themeDisplay,
//					RoleConstant.SCHOOLCOLLEGE);
//			fileUploadUtil.setFilePermissionByRoleName(aadharaadharCardRecieptId, serviceContext, themeDisplay,
//					RoleConstant.GUEST);
			LOGGER.info("schoolCollegeOfficer obj: " + schoolCollegeOfficer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return schoolCollegeOfficer;

	}

	public AwardApplicationCommonDTO setAwardApplicationCommonDTO(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {

		AwardApplicationCommonDTO awardApplicationDTO = new AwardApplicationCommonDTO();
		try {
			LOGGER.info("awardApplicationDTO Util ::: ");
			awardApplicationDTO.setCompetitionLevelId(ParamUtil.getLong(resourceRequest, "competitionLevel"));
			awardApplicationDTO.setCompetitionName(ParamUtil.getString(resourceRequest, "competitionName"));
			awardApplicationDTO.setCompetitionPlace(ParamUtil.getString(resourceRequest, "competitionPlace"));
			awardApplicationDTO.setParticipationYear(ParamUtil.getString(resourceRequest, "participationYear"));
			awardApplicationDTO.setSportId(ParamUtil.getLong(resourceRequest, "sportsName"));
			awardApplicationDTO.setCountryOfCompetition(ParamUtil.getString(resourceRequest, "countryOfCompetition"));
			awardApplicationDTO.setCityOfCompetition(ParamUtil.getString(resourceRequest, "cityOfCompetition"));
			awardApplicationDTO.setMedalRecieved(ParamUtil.getString(resourceRequest, "medalRecieved"));
			awardApplicationDTO.setCategory(ParamUtil.getLong(resourceRequest, "category"));
			awardApplicationDTO.setHighestPerformance(ParamUtil.getString(resourceRequest, "highestPerformance"));
			awardApplicationDTO.setCoachName(ParamUtil.getString(resourceRequest, "coachName"));
			awardApplicationDTO.setUserType(ParamUtil.getString(resourceRequest, "userType"));

			awardApplicationDTO.setCompetitionStartDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionStartDate")));
			awardApplicationDTO.setCompetitionEndDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionEndDate")));
			awardApplicationDTO.setNoOfMedalRecieved(ParamUtil.getLong(resourceRequest, "noOfMedals"));
			awardApplicationDTO.setNoOfParticipation(ParamUtil.getLong(resourceRequest, "sportsPersons"));
			LOGGER.info("Data  Second ::: " + ParamUtil.getString(resourceRequest, "competitionName"));
			LOGGER.info("DTO ::: " + awardApplicationDTO);
			awardApplicationDTO.setCreateDate(new Date());
			awardApplicationDTO.setModifiedDate(new Date());
			awardApplicationDTO.setUserId(themeDisplay.getUserId());
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long competitionCertificateId = fileUploadUtil.uploadFile(uploadPortletRequest, "competitionCertificate",
					CommonUtilityConstant.REGISTRATION_FOLDER, serviceContext);
			LOGGER.info("competitionCertificateId: " + competitionCertificateId);
			awardApplicationDTO.setCertificateId(competitionCertificateId);
			fileUploadUtil.setFilePermissionByRoleName(competitionCertificateId, serviceContext, themeDisplay,
					RoleConstant.SCHOOLCOLLEGE);
			LOGGER.info("awardApplicationDTO obj: " + awardApplicationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return awardApplicationDTO;

	}

}
