package com.mhdsys.awards.web.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.AwardPointsCommonDTO;
import com.mhdsys.common.pojo.ObjectionCommonDTO;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.model.CompetitionLevelMaster;
import com.mhdsys.schema.model.Objection;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.AwardPointsLocalServiceUtil;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.ObjectionLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SportsCompLvlMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = AwardsUtil.class)
public class AwardsUtil {

	private static Log LOGGER = LogFactoryUtil.getLog(AwardsUtil.class);

	@Reference
	DateConversionUtil dateConversionUtil;

	@Reference
	FileUploadUtil fileUploadUtil;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static AwardPointsCommonDTO setAwardsPointDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long awardsPointsId) {

		AwardPointsCommonDTO awardPointsDTO = new AwardPointsCommonDTO();
		try {
			LOGGER.info("sport type: " + ParamUtil.getInteger(resourceRequest, "competitionLevel"));
			awardPointsDTO.setAwardPointsId(awardsPointsId);
			awardPointsDTO.setCompetitionLevelId(ParamUtil.getInteger(resourceRequest, "competitionLevel"));
			awardPointsDTO.setAwardYear(ParamUtil.getString(resourceRequest, "awardYear"));
			awardPointsDTO.setAwardNameId(ParamUtil.getInteger(resourceRequest, "awardName"));
			awardPointsDTO.setSportsCompetitionLevelId(ParamUtil.getInteger(resourceRequest, "sportsCompetitionLevel"));
			awardPointsDTO.setSportsNameId(ParamUtil.getInteger(resourceRequest, "sportsName"));
			awardPointsDTO.setSportsTypeId(ParamUtil.getInteger(resourceRequest, "sportsType"));
			awardPointsDTO.setCategory(ParamUtil.getLong(resourceRequest, "category"));
			awardPointsDTO.setWinner(ParamUtil.getString(resourceRequest, "winner"));
			awardPointsDTO.setFirstRunnerUp(ParamUtil.getString(resourceRequest, "firstRunnerUp"));
			awardPointsDTO.setSecondRunnerUp(ParamUtil.getString(resourceRequest, "secondRunnerUp"));
			awardPointsDTO.setParticipant(ParamUtil.getString(resourceRequest, "participant"));

			awardPointsDTO.setCreatedDate(new Date());
			awardPointsDTO.setModifiedDate(new Date());

			LOGGER.info("awards point obj: " + awardPointsDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return awardPointsDTO;

	}

	public AwardApplicationCommonDTO setAwardApplicationDTO(AwardApplication awardApplication) {

		AwardApplicationCommonDTO applicationDTO = new AwardApplicationCommonDTO();
		try {
			LOGGER.info("application obj: " + awardApplication);
			BeanPropertiesUtil.copyProperties(awardApplication, applicationDTO);

			applicationDTO.setAwardApplicationId(awardApplication.getAwardApplicationId());
			applicationDTO.setCategory(awardApplication.getCategory());
			applicationDTO.setCityOfCompetition(awardApplication.getCityOfCompetition());
			applicationDTO.setCoachName(awardApplication.getCoachName());
			if (awardApplication.getCompetitionLevelId() > 0) {
				CompetitionLevelMaster compLevel = CompetitionLevelMasterLocalServiceUtil
						.getCompetitionLevelMaster(awardApplication.getCompetitionLevelId());
				applicationDTO.setCompetitionLevel(compLevel.getName_en());
			}
			if (awardApplication.getCategory() > 0) {
				applicationDTO.setCategoryName(Validator
						.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory()))
								? CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory())
										.getName()
								: "");
			}
			Map<Integer, String> genderMap = Map.of(1, "Male", 2, "Female");
			LOGGER.debug("awardApplication.getCompetitionStartDate(): " + awardApplication.getCompetitionStartDate());
			if (Validator.isNotNull(awardApplication.getCompetitionStartDate())) {
				applicationDTO
						.setCompetitionStartDateStr((Validator.isNotNull(awardApplication.getCompetitionStartDate()))
								? formatter.format(awardApplication.getCompetitionStartDate())
								: "");
				LOGGER.debug("after conversion: " + applicationDTO.getCompetitionStartDateStr());
				LOGGER.debug("after conversion in date: " + applicationDTO.getCompetitionStartDate());
			}
			if (Validator.isNotNull(awardApplication.getCompetitionEndDate())) {
				applicationDTO.setCompetitionEndDateStr((Validator.isNotNull(awardApplication.getCompetitionEndDate()))
						? formatter.format(awardApplication.getCompetitionEndDate())
						: "");
			}
			if (Validator.isNotNull(awardApplication.getCoachFromDate())) {
				applicationDTO.setCoachFromDateStr((Validator.isNotNull(awardApplication.getCoachFromDate()))
						? formatter.format(awardApplication.getCoachFromDate())
						: "");
			}
			if (Validator.isNotNull(awardApplication.getCoachToDate())) {
				applicationDTO.setCoachToDateStr((Validator.isNotNull(awardApplication.getCoachToDate()))
						? formatter.format(awardApplication.getCoachToDate())
						: "");
			}
			try {
				if (Validator.isNotNull(awardApplication.getUserId())) {
					applicationDTO
							.setUserName(Validator.isNotNull(UserLocalServiceUtil.getUser(awardApplication.getUserId()))
									? UserLocalServiceUtil.getUser(awardApplication.getUserId()).getFullName()
									: "");
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			applicationDTO.setSportsDeskOffUserId(awardApplication.getSportsDeskOffUserId());
			applicationDTO.setSportsDeskOffName(awardApplication.getSportsDeskOffName());
			String genderName = genderMap.getOrDefault(awardApplication.getGender(), "");
			applicationDTO.setGenderName(genderName);
			applicationDTO.setCompetitionName(awardApplication.getCompetitionName());
			applicationDTO.setCompetitionPlace(awardApplication.getCompetitionPlace());
			applicationDTO.setCountryOfCompetition(awardApplication.getCountryOfCompetition());
			applicationDTO.setHighestPerformance(awardApplication.getHighestPerformance());
			applicationDTO.setMedalRecieved(awardApplication.getMedalRecieved());
			applicationDTO.setNoOfMedalRecieved(awardApplication.getNoOfMedalRecieved());
			applicationDTO.setNoOfParticipation(awardApplication.getNoOfParticipation());
			applicationDTO.setParticipationYear(awardApplication.getParticipationYear());
			applicationDTO.setSportId(awardApplication.getSportId());
			applicationDTO.setSportsName(
					Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(applicationDTO.getSportId()))
							? SportsMasterLocalServiceUtil.getSportsMaster(applicationDTO.getSportId()).getName_en()
							: "");
			applicationDTO.setUserId(awardApplication.getUserId());
			applicationDTO.setUserType(awardApplication.getUserType());
			Optional<Profile> profileOptional = ProfileLocalServiceUtil.getByUserId(awardApplication.getUserId());
			if (profileOptional.isPresent()) {
				Profile profile = profileOptional.get();
				applicationDTO.setRoleName(profile.getRoleName());
			}
			LOGGER.debug("awardApplicationID: " + applicationDTO.getAwardApplicationId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return applicationDTO;

	}

	public AwardPointsCommonDTO setAwardsPointsDTO(AwardPoints awardPoints) {
		LOGGER.info("Set Awards Points DTO ::::   ");
		AwardPointsCommonDTO awardPointsCommonDTO = new AwardPointsCommonDTO();
		try {
			LOGGER.debug("awards points obj: " + awardPoints);
			BeanPropertiesUtil.copyProperties(awardPoints, awardPointsCommonDTO);
			awardPointsCommonDTO.setCompetitionLevel(Validator.isNotNull(CompetitionLevelMasterLocalServiceUtil
					.getCompetitionLevelMaster(awardPoints.getCompetitionLevelId()))
							? CompetitionLevelMasterLocalServiceUtil
									.getCompetitionLevelMaster(awardPoints.getCompetitionLevelId()).getName_en()
							: "");

			awardPointsCommonDTO.setSportsName(
					Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(awardPoints.getSportsNameId()))
							? SportsMasterLocalServiceUtil.getSportsMaster(awardPoints.getSportsNameId()).getName_en()
							: "");

			awardPointsCommonDTO.setAwardName(Validator
					.isNotNull(AwardNameMasterLocalServiceUtil.getAwardNameMaster(awardPoints.getAwardNameId()))
							? AwardNameMasterLocalServiceUtil.getAwardNameMaster(awardPoints.getAwardNameId())
									.getName_en()
							: "");

			awardPointsCommonDTO.setSportsType(Validator
					.isNotNull(SportsTypeMasterLocalServiceUtil.getSportsTypeMaster(awardPoints.getSportsTypeId()))
							? SportsTypeMasterLocalServiceUtil.getSportsTypeMaster(awardPoints.getSportsTypeId())
									.getName_en()
							: "");

			awardPointsCommonDTO.setCategoryStr(
					Validator.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(awardPoints.getCategory()))
							? CategoryMasterLocalServiceUtil.getCategoryMaster(awardPoints.getCategory()).getName()
							: "");

			awardPointsCommonDTO.setAwardNameId(awardPoints.getAwardNameId());
			awardPointsCommonDTO.setCompetitionLevelId(awardPoints.getCompetitionLevelId());
			awardPointsCommonDTO.setSportsNameId(awardPoints.getSportsNameId());
			awardPointsCommonDTO.setSportsTypeId(awardPoints.getSportsTypeId());
			awardPointsCommonDTO.setCategory(awardPoints.getCategory());

			awardPointsCommonDTO.setAwardYear(awardPoints.getAwardYear());

			awardPointsCommonDTO.setWinner(awardPoints.getWinner());
			awardPointsCommonDTO.setFirstRunnerUp(awardPoints.getFirstRunnerUp());
			awardPointsCommonDTO.setSecondRunnerUp(awardPoints.getSecondRunnerUp());
			awardPointsCommonDTO.setParticipant(awardPoints.getParticipant());

			LOGGER.debug("awards point dto: " + awardPointsCommonDTO.getAwardPointsId());
		} catch (Exception e) {
			LOGGER.info(e);
			LOGGER.error(e.getMessage(), e);
		}
		return awardPointsCommonDTO;

	}

	public AwardApplicationCommonDTO setAwardsApplicationsDTO(AwardApplication awardApplication,
			ThemeDisplay themeDisplay) {

		AwardApplicationCommonDTO awardApplicationDTO = new AwardApplicationCommonDTO();
		try {
			LOGGER.debug("awards application obj: " + awardApplication);
			BeanPropertiesUtil.copyProperties(awardApplication, awardApplicationDTO);

			awardApplicationDTO.setAwardApplicationId(awardApplication.getAwardApplicationId());
			awardApplicationDTO.setAwardPointByDeskOff(awardApplication.getAwardPointByDeskOff());
			awardApplicationDTO.setCategory(awardApplication.getCategory());
			awardApplicationDTO.setCategoryStr(Validator
					.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory()))
							? CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory()).getName()
							: "");

			awardApplicationDTO.setCityOfCompetition(awardApplication.getCityOfCompetition());
			awardApplicationDTO.setCoachName(awardApplication.getCoachName());
			awardApplicationDTO.setCompetitionEndDateStr(null);
			awardApplicationDTO.setCompetitionLevel(Validator.isNotNull(CompetitionLevelMasterLocalServiceUtil
					.getCompetitionLevelMaster(awardApplication.getCompetitionLevelId()))
							? CompetitionLevelMasterLocalServiceUtil
									.getCompetitionLevelMaster(awardApplication.getCompetitionLevelId()).getName_en()
							: "");

			awardApplicationDTO.setCompetitionLevelId(awardApplication.getCompetitionLevelId());

			awardApplicationDTO.setAwardYear(awardApplication.getAwardYear());

			awardApplicationDTO.setCompetitionName(awardApplication.getCompetitionName());
			awardApplicationDTO.setCompetitionPlace(awardApplication.getCompetitionPlace());
			awardApplicationDTO.setCompetitionStartDateStr(null);
			awardApplicationDTO.setCountryOfCompetition(awardApplication.getCountryOfCompetition());
			awardApplicationDTO.setDeskOffRemarks(awardApplication.getDeskOffRemarks());
			awardApplicationDTO.setHighestPerformance(awardApplication.getHighestPerformance());
			awardApplicationDTO.setMedalRecieved(awardApplication.getMedalRecieved());
			awardApplicationDTO.setNoOfMedalRecieved(awardApplication.getNoOfMedalRecieved());
			awardApplicationDTO.setNoOfParticipation(awardApplication.getNoOfParticipation());
			awardApplicationDTO.setOverallDeskOffRemarks(awardApplication.getOverallDeskOffRemarks());
			awardApplicationDTO.setParticipationYear(awardApplication.getParticipationYear());
//			awardApplicationDTO.setSportsName(awardApplication.getSportsName());
			awardApplicationDTO.setUserId(awardApplication.getUserId());
			awardApplicationDTO.setUserType(awardApplication.getUserType());
			if (awardApplication.getCategory() > 0) {
				awardApplicationDTO.setCategoryName(Validator
						.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory()))
								? CategoryMasterLocalServiceUtil.getCategoryMaster(awardApplication.getCategory())
										.getName()
								: "");
			}
			Map<Integer, String> genderMap = Map.of(1, "Male", 2, "Female");
			LOGGER.debug("awardApplication.getCompetitionStartDate(): " + awardApplication.getCompetitionStartDate());
			if (Validator.isNotNull(awardApplication.getCompetitionStartDate())) {
				awardApplicationDTO
						.setCompetitionStartDateStr((Validator.isNotNull(awardApplication.getCompetitionStartDate()))
								? formatter.format(awardApplication.getCompetitionStartDate())
								: "");
				LOGGER.debug("after conversion: " + awardApplicationDTO.getCompetitionStartDateStr());
				LOGGER.debug("after conversion in date: " + awardApplicationDTO.getCompetitionStartDate());
			}
			if (Validator.isNotNull(awardApplication.getCompetitionEndDate())) {
				awardApplicationDTO
						.setCompetitionEndDateStr((Validator.isNotNull(awardApplication.getCompetitionEndDate()))
								? formatter.format(awardApplication.getCompetitionEndDate())
								: "");
			}
			if (Validator.isNotNull(awardApplication.getCoachFromDate())) {
				awardApplicationDTO.setCoachFromDateStr((Validator.isNotNull(awardApplication.getCoachFromDate()))
						? formatter.format(awardApplication.getCoachFromDate())
						: "");
			}
			if (Validator.isNotNull(awardApplication.getCoachToDate())) {
				awardApplicationDTO.setCoachToDateStr((Validator.isNotNull(awardApplication.getCoachToDate()))
						? formatter.format(awardApplication.getCoachToDate())
						: "");
			}

			if (Validator.isNotNull(awardApplication.getUserId())) {
				awardApplicationDTO
						.setUserName(Validator.isNotNull(UserLocalServiceUtil.getUser(awardApplication.getUserId()))
								? UserLocalServiceUtil.getUser(awardApplication.getUserId()).getFullName()
								: "");
			}

			if (awardApplication.getSportId() > 0) {
				awardApplicationDTO.setSportsName(
						Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId())
										.getName_en()
								: "");
			}

			if (awardApplication.getSportsTypeId() > 0) {
				awardApplicationDTO.setSportsTypeStr(Validator.isNotNull(
						SportsTypeMasterLocalServiceUtil.getSportsTypeMaster(awardApplication.getSportsTypeId()))
								? SportsTypeMasterLocalServiceUtil
										.getSportsTypeMaster(awardApplication.getSportsTypeId()).getName_en()
								: "");
			}

			if (awardApplication.getSportsCompetitionLevelId() > 0) {
				awardApplicationDTO.setSportsCompetitionLevelStr(Validator.isNotNull(SportsCompLvlMasterLocalServiceUtil
						.getSportsCompLvlMaster(awardApplication.getSportsCompetitionLevelId()))
								? SportsCompLvlMasterLocalServiceUtil
										.getSportsCompLvlMaster(awardApplication.getSportsCompetitionLevelId())
										.getName_en()
								: "");
			}

			if (awardApplication.getAwardNameId() > 0) {
				awardApplicationDTO.setAwardNameStr(Validator.isNotNull(
						AwardNameMasterLocalServiceUtil.getAwardNameMaster(awardApplication.getAwardNameId()))
								? AwardNameMasterLocalServiceUtil.getAwardNameMaster(awardApplication.getAwardNameId())
										.getName_en()
								: "");
			}

			LOGGER.info("POINTS CREATION DATA IN EDIT :::   " + awardApplicationDTO.getAwardNameStr() + " / "
					+ awardApplicationDTO.getSportsCompetitionLevelStr() + " / "
					+ awardApplicationDTO.getSportsTypeStr());

			String genderName = genderMap.getOrDefault(awardApplication.getGender(), "");
			awardApplicationDTO.setGenderName(genderName);
			if (awardApplication.getCertificateId() > 0) {
				DLFileEntry CertificateFileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(awardApplication.getCertificateId());
				if (Validator.isNotNull(CertificateFileEntry)) {
					String fileName = CertificateFileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						awardApplicationDTO.setCertificateFileEntryName(trimmedFilename);
					}
					awardApplicationDTO.setCertificateFileURL(
							(fileUploadUtil.getPreviewURL(awardApplication.getCertificateId(), themeDisplay)));
				}
			}

			if (awardApplication.getAppointmentLetter() > 0) {
				DLFileEntry appointmentLetter = DLFileEntryLocalServiceUtil
						.getFileEntry(awardApplication.getAppointmentLetter());
				if (Validator.isNotNull(appointmentLetter)) {
					String fileName = appointmentLetter.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						awardApplicationDTO.setAppointmentLetterName(trimmedFilename);
					}
					awardApplicationDTO.setAppointmentLetterUrl(
							(fileUploadUtil.getPreviewURL(awardApplication.getAppointmentLetter(), themeDisplay)));
				}
			}

			if (awardApplication.getUserType().equalsIgnoreCase(AwardsCommonConstants.SPORTS_APPLICATION_FOR_COACH)
					&& awardApplication.getUndertakingId() > 0) {
				DLFileEntry undertakingFileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(awardApplication.getUndertakingId());
				if (Validator.isNotNull(undertakingFileEntry)) {
					String fileName = undertakingFileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						awardApplicationDTO.setUndertakingFileEntryName(trimmedFilename);
					}
					awardApplicationDTO.setUndertakingFileURL(
							(fileUploadUtil.getPreviewURL(awardApplication.getUndertakingId(), themeDisplay)));
				}

			}

			LOGGER.debug("awards application dto: " + awardApplicationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return awardApplicationDTO;

	}

	public AwardApplicationCommonDTO setApplicationDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long applicationId) {
		AwardApplicationCommonDTO applicationDTO = new AwardApplicationCommonDTO();
		try {

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isSportsDeskOfficer = RoleConstant.isSportsDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());

			// boolean isAssociation = RoleConstant.isAssociation(user,
			// themeDisplay.getCompanyId());
			// boolean isHoAdmin = RoleConstant.isHOAdmin(user,
			// themeDisplay.getCompanyId());

			LOGGER.debug("awards application id: " + applicationId);

			AwardApplication application = AwardApplicationLocalServiceUtil.getAwardApplication(applicationId);
			if (Validator.isNotNull(application)) {

				BeanPropertiesUtil.copyProperties(application, applicationDTO);

				applicationDTO.setAwardApplicationId(applicationId);

				applicationDTO.setCategory(application.getCategory());

				applicationDTO.setCityOfCompetition(application.getCityOfCompetition());
				applicationDTO.setCoachName(application.getCoachName());
				applicationDTO.setCompetitionEndDate(application.getCompetitionEndDate());
				applicationDTO.setCompetitionStartDate(application.getCompetitionStartDate());
				applicationDTO.setCompetitionLevelId(application.getCompetitionLevelId());
				applicationDTO.setCompetitionName(application.getCompetitionName());
				applicationDTO.setCompetitionPlace(application.getCompetitionPlace());
				applicationDTO.setCountryOfCompetition(application.getCountryOfCompetition());
				applicationDTO.setHighestPerformance(application.getHighestPerformance());
				applicationDTO.setMedalRecieved(application.getMedalRecieved());
				applicationDTO.setNoOfMedalRecieved(application.getNoOfMedalRecieved());
				applicationDTO.setNoOfParticipation(application.getNoOfParticipation());
				applicationDTO.setParticipationYear(application.getParticipationYear());
				applicationDTO.setSportId(application.getSportId());
				applicationDTO.setUserId(application.getUserId());
				applicationDTO.setCompetitionStartDate(application.getCompetitionStartDate());
				applicationDTO.setCompetitionEndDate(application.getCompetitionEndDate());
				applicationDTO.setUserType(application.getUserType());
				applicationDTO.setAwardPointByDeskOff(application.getAwardPointByDeskOff());
				applicationDTO.setDeskOffRemarks(application.getDeskOffRemarks());

				applicationDTO.setOverallDeskOffRemarks(application.getOverallDeskOffRemarks());
				applicationDTO.setAwardPointByAsso(application.getAwardPointByAsso());
				applicationDTO.setOverallAssoRemarks(application.getOverallAssoRemarks());
				applicationDTO.setAwardPointByHo(application.getAwardPointByHo());
				applicationDTO.setOverallHoRemarks(application.getOverallHoRemarks());

				if (isSportsDeskOfficer) {
					applicationDTO.setAwardPointByDeskOff(ParamUtil.getString(resourceRequest, "awardPointByDeskOff"));
					applicationDTO.setDeskOffRemarks(ParamUtil.getString(resourceRequest, "deskOffRemarks"));
					applicationDTO
							.setOverallDeskOffRemarks(ParamUtil.getString(resourceRequest, "overallDeskOffRemarks"));
					applicationDTO.setDeskofficerUserId(themeDisplay.getUserId());

				} else if (isAssistantDirector || isDeputyDirector) {
					applicationDTO.setDirectorRemarks(ParamUtil.getString(resourceRequest, "directorRemarks"));
					applicationDTO.setDirectorComments(ParamUtil.getString(resourceRequest, "directorComments"));
				} else if (isDeskOfficer) {
					applicationDTO.setMainDeskOffRemarks(ParamUtil.getString(resourceRequest, "mainDeskOffRemarks"));
				} else if (isAssociation) {

					applicationDTO.setAppointmentLetter(application.getAppointmentLetter());
					applicationDTO.setAssociationName(application.getAssociationName());
					applicationDTO.setOverallAssoRemarks(ParamUtil.getString(resourceRequest, "overallAssoRemarks"));
					// files upload
//					UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
//					ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
//							uploadPortletRequest);
//
//					if (uploadPortletRequest != null && uploadPortletRequest.getFile("appointmentLetter") != null) {
//						long appointmentLetter = uploadFile(uploadPortletRequest, "appointmentLetter", CommonUtilityConstant.AWARD_FOLDER,
//								serviceContext);
//						LOGGER.info("appointmentLetter Document file: " + appointmentLetter);
//						applicationDTO.setAppointmentLetter(appointmentLetter);
//					}
				}

//				else if (isAssociation) {
//					applicationDTO.setAwardPointByAsso(ParamUtil.getString(resourceRequest, "awardPointByAssociation"));
//					applicationDTO.setOverallAssoRemarks(ParamUtil.getString(resourceRequest, "overallAssoRemarks"));
//					applicationDTO.setAssociationUserId(themeDisplay.getUserId());
//				} else if (isHoAdmin) {
//					applicationDTO.setAwardPointByHo(ParamUtil.getString(resourceRequest, "awardPointByHo"));
//					applicationDTO.setOverallHoRemarks(ParamUtil.getString(resourceRequest, "overallHoRemarks"));
//					applicationDTO.setHoUserId(themeDisplay.getUserId());
//				}

			}

			LOGGER.debug("awards application object: " + application);
			return applicationDTO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return applicationDTO;

	}

	public ObjectionCommonDTO setObjectionDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long objectionId) {

		ObjectionCommonDTO objectionCommonDTO = new ObjectionCommonDTO();
		try {

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			LOGGER.debug("Player Name : " + ParamUtil.getString(resourceRequest, "playerName"));

			if (isDeskOfficer || isDeputyDirector) {

				Objection objection = ObjectionLocalServiceUtil.getObjection(objectionId);
				objectionCommonDTO.setObjectionId(objectionId);
				objectionCommonDTO.setPlayerName(objection.getPlayerName());
				objectionCommonDTO.setObjectorName(objection.getObjectorName());
				objectionCommonDTO.setEmail(objection.getEmail());
				objectionCommonDTO.setMobileNo(objection.getMobileNo());
				objectionCommonDTO.setCategory(objection.getCategory());
				objectionCommonDTO.setSportNo(objection.getSportNo());
				objectionCommonDTO.setAwardYear(objection.getAwardYear());
				objectionCommonDTO.setSportName(objection.getSportName());
				objectionCommonDTO.setAwardType(objection.getAwardType());
				objectionCommonDTO.setDistrict(objection.getDistrict());
				objectionCommonDTO.setPlace(objection.getPlace());
				objectionCommonDTO.setObjectionSummary(objection.getObjectionSummary());
				objectionCommonDTO.setUserId(objection.getUserId());

				if (isDeskOfficer) {
					objectionCommonDTO.setDoVerification(ParamUtil.getString(resourceRequest, "doVerification"));
					objectionCommonDTO.setDoRemarks(ParamUtil.getString(resourceRequest, "doRemarks"));

				} else if (isDeputyDirector) {
					objectionCommonDTO.setDoVerification(objection.getDoVerification());
					objectionCommonDTO.setDoRemarks(objection.getDoRemarks());
					objectionCommonDTO.setDdVerification(ParamUtil.getString(resourceRequest, "ddVerification"));
					objectionCommonDTO.setDdRemarks(ParamUtil.getString(resourceRequest, "ddRemarks"));
				}
//				objectionCommonDTO.setFinalRemarksByFed(ParamUtil.getString(resourceRequest, "finalRemarksByFed"));
//
//				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
//				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
//						uploadPortletRequest);
//				long objectionFileId = 0;
//				objectionFileId = fileUploadUtil.uploadFile(uploadPortletRequest, "objectionFile", "Awards",
//						serviceContext);
//				LOGGER.debug("objection attachment id : " + objectionFileId);
//				objectionCommonDTO.setObjectionFileEntryId(objectionFileId);
//				fileUploadUtil.setFilePermissionByRoleName(objectionFileId, serviceContext, themeDisplay,
//						RoleConstant.DESK_OFFICER);

			} else {

				objectionCommonDTO.setObjectionId(objectionId);
				objectionCommonDTO.setPlayerName(ParamUtil.getString(resourceRequest, "playerName"));
				objectionCommonDTO.setObjectorName(ParamUtil.getString(resourceRequest, "objectorName"));
				objectionCommonDTO.setEmail(ParamUtil.getString(resourceRequest, "email"));
				objectionCommonDTO.setMobileNo(ParamUtil.getString(resourceRequest, "mobileNo"));
				objectionCommonDTO.setCategory(ParamUtil.getString(resourceRequest, "category"));
				objectionCommonDTO.setSportNo(ParamUtil.getLong(resourceRequest, "sportNo"));
				objectionCommonDTO.setAwardYear(ParamUtil.getString(resourceRequest, "awardYear"));
				objectionCommonDTO.setSportName(ParamUtil.getLong(resourceRequest, "sportName"));
				objectionCommonDTO.setAwardType(ParamUtil.getLong(resourceRequest, "awardType"));
				objectionCommonDTO.setDistrict(ParamUtil.getLong(resourceRequest, "district"));
				objectionCommonDTO.setPlace(ParamUtil.getString(resourceRequest, "place"));
				objectionCommonDTO.setObjectionSummary(ParamUtil.getString(resourceRequest, "objectionSummary"));
				objectionCommonDTO.setUserId(user.getUserId());

			}

			LOGGER.debug("objection dto : " + objectionCommonDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return objectionCommonDTO;

	}

	public ObjectionCommonDTO setObjectionDTO(Objection objection) {

		ObjectionCommonDTO commonDTO = new ObjectionCommonDTO();
		try {
			LOGGER.debug("objection obj: " + objection);
			BeanPropertiesUtil.copyProperties(objection, commonDTO);

			commonDTO.setObjectionId(objection.getObjectionId());
			commonDTO.setAwardType(objection.getAwardType());

			commonDTO.setAwardYear(objection.getAwardYear());
			commonDTO.setCategory(objection.getCategory());
			commonDTO.setDistrict(objection.getDistrict());

			commonDTO.setDistrictStr(
					Validator.isNotNull(DistrictMasterLocalServiceUtil.getDistrictMaster(objection.getDistrict()))
							? DistrictMasterLocalServiceUtil.getDistrictMaster(objection.getDistrict())
									.getDistrictName_en()
							: "");

			commonDTO.setSportNameStr(
					Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(objection.getSportName()))
							? SportsMasterLocalServiceUtil.getSportsMaster(objection.getSportName()).getName_en()
							: "");

			commonDTO.setAwardStr(
					Validator.isNotNull(AwardNameMasterLocalServiceUtil.getAwardNameMaster(objection.getAwardType()))
							? AwardNameMasterLocalServiceUtil.getAwardNameMaster(objection.getAwardType()).getName_en()
							: "");

			commonDTO.setEmail(objection.getEmail());
			commonDTO.setFinalPointsByHo(objection.getFinalPointsByHo());
			commonDTO.setFinalRemarksByHO(objection.getFinalRemarksByHO());
			commonDTO.setFinalRemarksByFed(objection.getFinalRemarksByFed());
			commonDTO.setMobileNo(objection.getMobileNo());
			commonDTO.setObjectionId(objection.getObjectionId());
			commonDTO.setObjectionSummary(objection.getObjectionSummary());
			commonDTO.setObjectorName(objection.getObjectorName());
			commonDTO.setPlace(objection.getPlace());
			commonDTO.setPlayerName(objection.getPlayerName());
			commonDTO.setSportName(objection.getSportName());

			commonDTO.setSportNo(objection.getSportNo());
			commonDTO.setUserId(objection.getUserId());

			LOGGER.debug("commonDTO: " + commonDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return commonDTO;

	}

	public AwardApplicationCommonDTO setAwardApplicationCommonDTO(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long applicationId) {

		AwardApplicationCommonDTO awardApplicationDTO = new AwardApplicationCommonDTO();
		try {
			LOGGER.debug("awardApplicationDTO Util ::: ");
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
			awardApplicationDTO.setGender(ParamUtil.getInteger(resourceRequest, "gender"));

			awardApplicationDTO.setStatus(ParamUtil.getString(resourceRequest, "status"));

			awardApplicationDTO.setCoachFromDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "coachFromDate")));
			awardApplicationDTO.setCoachToDate(
					dateConversionUtil.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "coachToDate")));
			awardApplicationDTO.setSportpersonName(ParamUtil.getString(resourceRequest, "sportpersonName"));

			awardApplicationDTO.setOtherSportName(ParamUtil.getString(resourceRequest, "otherSportName"));
			awardApplicationDTO.setCampaignDescription(ParamUtil.getString(resourceRequest, "campaignDescription"));

			awardApplicationDTO.setCompetitionStartDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionStartDate")));
			awardApplicationDTO.setCompetitionEndDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionEndDate")));
			awardApplicationDTO.setNoOfMedalRecieved(ParamUtil.getLong(resourceRequest, "noOfMedals"));
			awardApplicationDTO.setNoOfParticipation(ParamUtil.getLong(resourceRequest, "sportsPersons"));
			LOGGER.debug("Data  Second ::: " + ParamUtil.getString(resourceRequest, "competitionName"));

			awardApplicationDTO.setAwardYear(ParamUtil.getString(resourceRequest, "awardYear"));
			awardApplicationDTO.setAwardNameId(ParamUtil.getInteger(resourceRequest, "awardName"));
			awardApplicationDTO
					.setSportsCompetitionLevelId(ParamUtil.getInteger(resourceRequest, "sportsCompetitionLevel"));
			awardApplicationDTO.setSportsTypeId(ParamUtil.getInteger(resourceRequest, "sportsType"));

			AwardPoints awardPoints = null;

			LOGGER.info("Dynamic Query Started ::: ");

			DynamicQuery dynamicQuery = AwardPointsLocalServiceUtil.dynamicQuery();

			dynamicQuery
					.add(RestrictionsFactoryUtil.eq("awardNameId", ParamUtil.getLong(resourceRequest, "awardName")));
			dynamicQuery
					.add(RestrictionsFactoryUtil.eq("awardYear", ParamUtil.getString(resourceRequest, "awardYear")));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("sportsCompetitionLevelId",
					ParamUtil.getLong(resourceRequest, "sportsCompetitionLevel")));
			dynamicQuery.add(
					RestrictionsFactoryUtil.eq("sportsTypeId", ParamUtil.getLong(resourceRequest, "sportsType")));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("category", ParamUtil.getLong(resourceRequest, "category")));
			dynamicQuery
					.add(RestrictionsFactoryUtil.eq("sportsNameId", ParamUtil.getLong(resourceRequest, "sportsName")));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("competitionLevelId",
					ParamUtil.getLong(resourceRequest, "competitionLevel")));

			List<AwardPoints> results = AwardPointsLocalServiceUtil.dynamicQuery(dynamicQuery);

			LOGGER.info("AWARD POINTS SIZE  : " + results);

			if (results.size() > 0) {
				LOGGER.info("Awards Points Available : ");
				awardPoints = results.get(0);

				if (awardApplicationDTO.getMedalRecieved().equalsIgnoreCase("Gold")) {

					awardApplicationDTO.setPoints(awardPoints.getWinner());

				} else if (awardApplicationDTO.getMedalRecieved().equalsIgnoreCase("Silver")) {

					awardApplicationDTO.setPoints(awardPoints.getFirstRunnerUp());

				}
				if (awardApplicationDTO.getMedalRecieved().equalsIgnoreCase("Bronze")) {

					awardApplicationDTO.setPoints(awardPoints.getSecondRunnerUp());

				} else {

					awardApplicationDTO.setPoints(awardPoints.getParticipant());

				}

			}

			LOGGER.info("DTO ::: " + awardApplicationDTO);
			awardApplicationDTO.setCreateDate(new Date());
			awardApplicationDTO.setModifiedDate(new Date());
			awardApplicationDTO.setUserId(themeDisplay.getUserId());
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long competitionCertificateId = 0;
			long separateUndertakingFileId = 0;
			if (applicationId > 0) {
				long competitionCertificateEntryId = ParamUtil.getLong(resourceRequest,
						"competitionCertificateFileEntyId");
				long seperateUndertakingFileEntyId = ParamUtil.getLong(resourceRequest,
						"seperateUndertakingFileEntyId");
				LOGGER.info("competitionCertificateEntryId: " + competitionCertificateEntryId
						+ "seperateUndertakingFileEntyId: " + seperateUndertakingFileEntyId);
				if (competitionCertificateEntryId > 0) {
					competitionCertificateId = competitionCertificateEntryId;
				} else {
					competitionCertificateId = fileUploadUtil.uploadFile(uploadPortletRequest, "competitionCertificate",
							CommonUtilityConstant.AWARD_FOLDER, serviceContext);
				}
				if (seperateUndertakingFileEntyId > 0) {
					separateUndertakingFileId = seperateUndertakingFileEntyId;
				} else {
					separateUndertakingFileId = fileUploadUtil.uploadFile(uploadPortletRequest, "separateUndertaking",
							CommonUtilityConstant.AWARD_FOLDER, serviceContext);
				}
			} else {
				competitionCertificateId = fileUploadUtil.uploadFile(uploadPortletRequest, "competitionCertificate",
						CommonUtilityConstant.AWARD_FOLDER, serviceContext);
				separateUndertakingFileId = fileUploadUtil.uploadFile(uploadPortletRequest, "separateUndertaking",
						CommonUtilityConstant.AWARD_FOLDER, serviceContext);
			}
			LOGGER.info("competitionCertificateId: " + competitionCertificateId);
			awardApplicationDTO.setCertificateId(competitionCertificateId);
			awardApplicationDTO.setUndertakingId(separateUndertakingFileId);
//			fileUploadUtil.setFilePermissionByRoleName(competitionCertificateId, serviceContext, themeDisplay,
//					RoleConstant.SCHOOLCOLLEGE);
			awardApplicationDTO.setAwardApplicationId(applicationId);
			LOGGER.info("awardApplicationDTO obj: " + awardApplicationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return awardApplicationDTO;

	}

	public AwardApplication updateDraftStatus(long applicationId, String status) {

		AwardApplication awardApplication = AwardApplicationLocalServiceUtil.fetchAwardApplication(applicationId);

		if (awardApplication != null) {
			LOGGER.info("Updating AwardApplication ID: " + awardApplication.getAwardApplicationId());

			if (!awardApplication.getStatus().equalsIgnoreCase("Save")) {

				if (!awardApplication.getStatus().equalsIgnoreCase("Applied")) {
					awardApplication.setStatus(status);
				}
			}

			AwardApplicationLocalServiceUtil.updateAwardApplication(awardApplication);

			LOGGER.info("Successfully updated AwardApplication ID: " + applicationId);
		} else {
			LOGGER.warn("AwardApplication not found for ID: " + applicationId);
		}
		return awardApplication;

	}

	public static long uploadFile(UploadPortletRequest uploadPortletRequest, String fileParamName, String folderName,
			ServiceContext serviceContext) {

		File file = uploadPortletRequest.getFile(fileParamName);
		String fileName = uploadPortletRequest.getFileName(fileParamName);
		String orgiFileName = uploadPortletRequest.getFileName(fileParamName);
		LOGGER.info("fileName:" + fileName + ", orgiFileName:" + orgiFileName);
		fileName = HtmlUtil.stripHtml(fileName);
		// fileName = HtmlUtil.toInputSafe(fileName);
		// fileName = HtmlUtil.escape(fileName);
		// fileName = HtmlUtil.escapeJS(fileName);
		// fileName = HtmlUtil.escapeHREF(fileName);
		fileName = HtmlParserUtil.extractText(fileName);
		LOGGER.info(orgiFileName);
//		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ._-]+$", Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(orgiFileName);
//		boolean matchFound = matcher.find();
//		LOGGER.info("matchFound---" + matchFound);
		Date date = new Date();
		if (Validator.isNotNull(file) && Validator.isNotNull(fileName)) {
			String description = fileName;
			fileName = date.getTime() + "_" + fileName;
			String mimeType = uploadPortletRequest.getContentType(fileParamName);
			String title = fileName;
			if (validatePDFFileType(orgiFileName, mimeType) || validateImageFileType(orgiFileName, mimeType)) {
				LOGGER.debug("file" + file + "  fileName " + fileName);
//					long repositoryId = themeDisplay.getScopeGroupId();
				long repositoryId = getDefaultSiteGroupId();
				LOGGER.info("repositoryid: " + repositoryId);
				try {
					Folder folder = getFolder(repositoryId, folderName);
					if (Validator.isNotNull(folder)) {
						Role adminRole = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
								RoleConstants.ADMINISTRATOR);
						List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
						long userId = 20122;
						if (adminUsers != null && !adminUsers.isEmpty()) {
							userId = adminUsers.get(0).getUserId();
						}
						User user1 = UserLocalServiceUtil.getUser(userId);
						PermissionChecker checker = PermissionCheckerFactoryUtil.create(user1);
						PermissionThreadLocal.setPermissionChecker(checker);
						LOGGER.info("folder:" + folder.getFolderId());
						FileEntry fileEntry = DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(),
								fileName, mimeType, title, description, StringPool.BLANK, file, serviceContext);
						LOGGER.info("fileEntry.getFileEntryId() :" + fileEntry.getFileEntryId());
						return fileEntry.getFileEntryId();

					}

				} catch (Exception e) {
					LOGGER.error("Exception in uploadinf file  in FileUpload :: uploadClaimFile ::" + e.getMessage());
					LOGGER.error(e);
				}
			}
		}

		return 0;
	}

	public static boolean validatePDFFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("application/pdf");
		List<String> validExt = new ArrayList<String>();
		validExt.add("pdf");
		boolean isValidMime = validMime.contains(mimeType);
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		boolean isValidExt = validExt.contains(extension);
		LOGGER.debug("isValidMime " + isValidMime + "isValidExt" + isValidExt);
		return isValidMime && isValidExt;
	}

	public boolean validateDOCFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		List<String> validExt = new ArrayList<String>();
		validExt.add("docx");
		boolean isValidMime = validMime.contains(mimeType);
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		boolean isValidExt = validExt.contains(extension);
		return isValidMime && isValidExt;
	}

	/**
	 * Validate Image file
	 */
	public static boolean validateImageFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("image/jpeg");
		validMime.add("image/png");
		List<String> validExt = new ArrayList<String>();
		validExt.add("jpg");
		validExt.add("jpeg");
		validExt.add("png");
		boolean isValidMime = validMime.contains(mimeType);
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		boolean isValidExt = validExt.contains(extension);
		return isValidMime && isValidExt;
	}

	public static long getDefaultSiteGroupId() {
		try {
			// Fetch the default company
			Company company = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long companyId = company.getCompanyId();

			// Get the default site's group
			Group group = GroupLocalServiceUtil.getGroup(companyId, "Guest");
			return group.getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	public static Folder getFolder(long groupId, String folderName) throws PortalException {

		Folder folder = DLAppServiceUtil.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName);
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folder;

	}

}
