package com.mhdsys.competition.management.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.common.pojo.CompetitionMasterCommonDTO;
import com.mhdsys.common.pojo.CompetitionScheduleCommonDTO;
import com.mhdsys.common.pojo.PTTeacherApplicationCommonDTO;
import com.mhdsys.common.pojo.ResultUploadCommonDTO;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionMaster;
import com.mhdsys.schema.model.CompetitionResultUpload;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionWorkflowStatusLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = CompetitionUtil.class)

public class CompetitionUtil {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Reference
	DateConversionUtil dateConversionUtil;
	@Reference
	FileUploadUtil fileUploadUtil;

	public CompetitionMasterCommonDTO setCompetitionMaster(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {

		CompetitionMasterCommonDTO competitionMaster = new CompetitionMasterCommonDTO();
		try {
			LOGGER.info("sport type: " + ParamUtil.getInteger(resourceRequest, "sportType"));
			competitionMaster.setSportTypeId(ParamUtil.getInteger(resourceRequest, "sportType"));
			competitionMaster.setSportId(ParamUtil.getLong(resourceRequest, "sportName"));
			competitionMaster.setCategoryId(ParamUtil.getInteger(resourceRequest, "category"));
			competitionMaster.setUnderForteen(ParamUtil.getBoolean(resourceRequest, "underForteen"));
			competitionMaster.setUnderSeventeen(ParamUtil.getBoolean(resourceRequest, "underSeventeen"));
			competitionMaster.setUnderNineteen(ParamUtil.getBoolean(resourceRequest, "underNineteen"));
			competitionMaster.setUnderForteenCutOffDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "underForteenCutOffDate")));
			competitionMaster.setUnderSeventeenCutOffDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "underSeventeenCutOffDate")));
			competitionMaster.setUnderNineteenCutOffDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "underNineteenCutOffDate")));
			competitionMaster.setFees(ParamUtil.getInteger(resourceRequest, "fees"));
			competitionMaster.setCurrentYear(ParamUtil.getBoolean(resourceRequest, "currentYear"));
			competitionMaster.setCreateDate(new Date());
			competitionMaster.setModifiedDate(new Date());
			competitionMaster.setUserId(themeDisplay.getUserId());
			LOGGER.info("competitionmaster obj: " + competitionMaster);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionMaster;

	}

	public CompetitionMasterCommonDTO setCompetitionMasterDTO(CompetitionMaster competitionMaster) {

		CompetitionMasterCommonDTO competitionMasterDTO = new CompetitionMasterCommonDTO();
		try {
			LOGGER.info("competitionmaster obj: " + competitionMaster);
			BeanPropertiesUtil.copyProperties(competitionMaster, competitionMasterDTO);
			competitionMasterDTO.setSportName(
					Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(competitionMaster.getSportId()))
							? SportsMasterLocalServiceUtil.getSportsMaster(competitionMaster.getSportId()).getName_en()
							: "");
			competitionMasterDTO.setCategoryName(Validator
					.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(competitionMaster.getCategoryId()))
							? CategoryMasterLocalServiceUtil.getCategoryMaster(competitionMaster.getCategoryId())
									.getName()
							: "");
			Map<Integer, String> sportTypeMap = Map.of(1, CompetitionCommonConstant.GRANTED, 2,
					CompetitionCommonConstant.NON_GRANTED);

			String sportType = sportTypeMap.getOrDefault(competitionMaster.getSportTypeId(), "");
			competitionMasterDTO.setSportType(sportType);
			LOGGER.info("competitionMasterDTO: " + competitionMasterDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionMasterDTO;

	}

	public CompetitionInitiationCommonDTO setCompetitionInitiation(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long competitionMasterId) {

		CompetitionInitiationCommonDTO competitionInitiationDTO = new CompetitionInitiationCommonDTO();
		try {
			competitionInitiationDTO.setCompetitionMasterId(competitionMasterId);
			competitionInitiationDTO.setUnderForteen(ParamUtil.getBoolean(resourceRequest, "underForteen"));
			competitionInitiationDTO.setUnderSeventeen(ParamUtil.getBoolean(resourceRequest, "underSeventeen"));
			competitionInitiationDTO.setUnderNineteen(ParamUtil.getBoolean(resourceRequest, "underNineteen"));
			competitionInitiationDTO.setCompetitionStartDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionStartDate")));
			competitionInitiationDTO.setCompetitionEndDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionEndDate")));
			competitionInitiationDTO.setFees(ParamUtil.getDouble(resourceRequest, "fees"));
			competitionInitiationDTO.setVenueDetails(ParamUtil.getString(resourceRequest, "venueDetails"));
			competitionInitiationDTO.setAffiliationFeesByTotalCount(
					ParamUtil.getDouble(resourceRequest, "affiliationFeesByTotalCount"));
			competitionInitiationDTO
					.setAffiliationFeesByCategory(ParamUtil.getDouble(resourceRequest, "affiliationFeesByCategory"));
			competitionInitiationDTO.setSelfDeclaration(ParamUtil.getBoolean(resourceRequest, "selfDeclaration"));
			competitionInitiationDTO.setDistrictOrTaluka(ParamUtil.getInteger(resourceRequest, "districtOrTaluka"));
			competitionInitiationDTO.setTaluka(ParamUtil.getLong(resourceRequest, "taluka"));
			competitionInitiationDTO.setCreateDate(new Date());
			competitionInitiationDTO.setModifiedDate(new Date());
			competitionInitiationDTO.setUserId(themeDisplay.getUserId());
			CompetitionMaster competitionMaster = CompetitionMasterLocalServiceUtil
					.getCompetitionMaster(competitionMasterId);
			if (competitionMasterId > 0 && Validator.isNotNull(competitionMaster)) {
				competitionInitiationDTO.setSportId(competitionMaster.getSportId());
				competitionInitiationDTO.setCategoryId(competitionMaster.getCategoryId());
				LOGGER.info("sport id: " + competitionInitiationDTO.getSportId());
			}
			LOGGER.info("competitionmaster obj: " + competitionInitiationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionInitiationDTO;

	}

	public CompetitionInitiationCommonDTO setCompetitionInitiationDTO(CompetitionInitiation competitionInitiation) {

		CompetitionInitiationCommonDTO competitionInitiationDTO = new CompetitionInitiationCommonDTO();
		try {
			LOGGER.info("competitionmaster obj: " + competitionInitiationDTO);
			BeanPropertiesUtil.copyProperties(competitionInitiation, competitionInitiationDTO);
			if (competitionInitiation.getSportId() > 0) {
				competitionInitiationDTO.setSportName(Validator
						.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId())
										.getName_en()
								: "");
			}
			competitionInitiationDTO.setCategoryName(Validator
					.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(competitionInitiation.getCategoryId()))
							? CategoryMasterLocalServiceUtil.getCategoryMaster(competitionInitiation.getCategoryId())
									.getName()
							: "");
			if (Validator.isNotNull(competitionInitiation.getCompetitionStartDate())) {
				competitionInitiationDTO.setCompetitionStartDateStr(
						(Validator.isNotNull(competitionInitiation.getCompetitionStartDate()))
								? formatter.format(competitionInitiation.getCompetitionStartDate())
								: "");
			}
			if (Validator.isNotNull(competitionInitiation.getCompetitionEndDate())) {
				competitionInitiationDTO
						.setCompetitionEndDateStr((Validator.isNotNull(competitionInitiation.getCompetitionEndDate()))
								? formatter.format(competitionInitiation.getCompetitionEndDate())
								: "");
			}
			Map<Integer, String> districtOrTalukaMap = Map.of(1, CompetitionCommonConstant.DISTRICT, 2,
					CompetitionCommonConstant.TALUKA);
			String disOrTaluka = districtOrTalukaMap.getOrDefault(competitionInitiation.getDistrictOrTaluka(), "");
			competitionInitiationDTO.setDistrictOrTalukaName(disOrTaluka);

			LOGGER.info("competitionMasterDTO: " + competitionInitiationDTO.getCompetitionInitiationId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionInitiationDTO;

	}

	public PTTeacherApplicationCommonDTO setPtTeacherApplication(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long competitionInitiationId) {

		PTTeacherApplicationCommonDTO ptTeacherApplicationDTO = new PTTeacherApplicationCommonDTO();
		try {
			long ptTeacherApplicationId = ParamUtil.getLong(resourceRequest, "competitionScheduledId");
			String participantName = ParamUtil.getString(resourceRequest, "participantName");
			ptTeacherApplicationDTO.setCompetitionInitiationId(competitionInitiationId);
			ptTeacherApplicationDTO
					.setCountOfTeamOrIndividual(ParamUtil.getInteger(resourceRequest, "countOfTeamOrIndividual"));
			ptTeacherApplicationDTO.setFees(ParamUtil.getDouble(resourceRequest, "fees"));
			ptTeacherApplicationDTO.setAffiliationFees(ParamUtil.getDouble(resourceRequest, "affiliationFees"));
			ptTeacherApplicationDTO.setTotalFees(ParamUtil.getDouble(resourceRequest, "totalFees"));
			ptTeacherApplicationDTO.setParticipantName(participantName);
			ptTeacherApplicationDTO.setParticipantUserId(ParamUtil.getLong(resourceRequest, "participantUserId"));
			ptTeacherApplicationDTO.setCreateDate(new Date());
			ptTeacherApplicationDTO.setModifiedDate(new Date());
			ptTeacherApplicationDTO.setUserId(themeDisplay.getUserId());
			ptTeacherApplicationDTO
					.setPtTeacherApplicationId(ParamUtil.getLong(resourceRequest, "ptTeacherApplicationId"));
			if (participantName.isEmpty()) {
				ptTeacherApplicationDTO.setStatus(CompetitionWorkflowStatusLocalServiceUtil
						.getCompetitionWorkflowStatus(CompetitionCommonConstant.PROCESS1_PENDING)
						.getCompetitionWorkflowStatusId());
			} else {
				ptTeacherApplicationDTO.setStatus(CompetitionWorkflowStatusLocalServiceUtil
						.getCompetitionWorkflowStatus(CompetitionCommonConstant.PROCESS2_PENDING)
						.getCompetitionWorkflowStatusId());
			}
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long feesPaymentRecieptId = 0;
			if (ptTeacherApplicationId > 0) {
				feesPaymentRecieptId = ParamUtil.getLong(resourceRequest, "feesRecieptFileEntryId");
				LOGGER.info("feesPaymentRecieptId: " + feesPaymentRecieptId);
			} else {
				feesPaymentRecieptId = fileUploadUtil.uploadFile(uploadPortletRequest, "feesPaymentReciept",
						"Competition", serviceContext);
			}
			LOGGER.info("feesPaymentRecieptId: " + feesPaymentRecieptId);
			ptTeacherApplicationDTO.setFeesRecieptFileEntryId(feesPaymentRecieptId);
			fileUploadUtil.setFilePermissionByRoleName(feesPaymentRecieptId, serviceContext, themeDisplay,
					RoleConstant.PT_TEACHER);
			LOGGER.info("ptTeacherApplicationDTO obj: " + ptTeacherApplicationDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ptTeacherApplicationDTO;

	}

	public PTTeacherApplicationCommonDTO setPtTeacherApplicationDTO(PTTeacherApplication ptTeacherApplication,
			ThemeDisplay themeDisplay) {

		PTTeacherApplicationCommonDTO ptTeacherApplicationCommonDTO = new PTTeacherApplicationCommonDTO();
		try {
			LOGGER.info("ptTeacherApplication obj: " + ptTeacherApplication);
			BeanPropertiesUtil.copyProperties(ptTeacherApplication, ptTeacherApplicationCommonDTO);
			ptTeacherApplicationCommonDTO.setStatusName(Validator.isNotNull(CompetitionWorkflowStatusLocalServiceUtil
					.getCompetitionWorkflowStatus(ptTeacherApplication.getStatus()))
							? CompetitionWorkflowStatusLocalServiceUtil
									.getCompetitionWorkflowStatus(ptTeacherApplication.getStatus()).getDescription()
							: "");
			CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil
					.getCompetitionInitiation(ptTeacherApplication.getCompetitionInitiationId());
			if (ptTeacherApplication.getCompetitionInitiationId() > 0 && Validator.isNotNull(competitionInitiation)) {
				ptTeacherApplicationCommonDTO.setSportName(Validator
						.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId())
										.getName_en()
								: "");
			}
			DLFileEntry feesReceiptEntry = DLFileEntryLocalServiceUtil
					.getFileEntry(ptTeacherApplication.getFeesRecieptFileEntryId());
			if (Validator.isNotNull(feesReceiptEntry)) {
				String fileName = feesReceiptEntry.getFileName();
				if (fileName.contains("_")) {
					int underscoreIndex = fileName.indexOf('_');
					String trimmedFilename = fileName.substring(underscoreIndex + 1);
					ptTeacherApplicationCommonDTO.setFileEntryName(trimmedFilename);
				}
				ptTeacherApplicationCommonDTO.setFileURL(
						fileUploadUtil.getPreviewURL(ptTeacherApplication.getFeesRecieptFileEntryId(), themeDisplay));
			}
			LOGGER.info("ptTeacherApplicationCommonDTO: " + ptTeacherApplicationCommonDTO.getCompetitionInitiationId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ptTeacherApplicationCommonDTO;

	}

	public CompetitionScheduleCommonDTO setCompetitionSchedule(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {

		CompetitionScheduleCommonDTO competitionScheduleDTO = new CompetitionScheduleCommonDTO();
		try {
			competitionScheduleDTO
					.setPtTeacherApplicationId(ParamUtil.getLong(resourceRequest, "ptTeacherApplicationId"));
			competitionScheduleDTO
					.setCompetitionInitiationId(ParamUtil.getLong(resourceRequest, "competitionInitiationId"));
			competitionScheduleDTO.setCompetitionName(ParamUtil.getString(resourceRequest, "competitionName"));
			competitionScheduleDTO.setGender(ParamUtil.getBoolean(resourceRequest, "gender"));
			competitionScheduleDTO.setDistrictOrTaluka(ParamUtil.getInteger(resourceRequest, "districtOrTaluka"));
			competitionScheduleDTO.setRegistrationStartDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "registartionStartDate")));
			competitionScheduleDTO.setRegistrationEndDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "registartionEndDate")));
			competitionScheduleDTO.setLastSubmissionDate(dateConversionUtil
					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "lastDateOfSubmission")));
			competitionScheduleDTO
					.setCompetitionOrganizerName(ParamUtil.getString(resourceRequest, "competitionOrganizerName"));
			competitionScheduleDTO.setCompetitionOrganizerContact(
					ParamUtil.getString(resourceRequest, "competitionOrganizerContact"));
			competitionScheduleDTO.setSrNumber(ParamUtil.getInteger(resourceRequest, "srNumber"));
			LOGGER.info("strat time" + ParamUtil.getString(resourceRequest, "startTime"));
			competitionScheduleDTO.setStartTime(
					dateConversionUtil.convertStringToTimeFormat(ParamUtil.getString(resourceRequest, "startTime")));
			competitionScheduleDTO.setReportingTime(dateConversionUtil
					.convertStringToTimeFormat(ParamUtil.getString(resourceRequest, "reportingTime")));

			competitionScheduleDTO.setCreateDate(new Date());
			competitionScheduleDTO.setModifiedDate(new Date());
			competitionScheduleDTO.setUserId(themeDisplay.getUserId());
			LOGGER.info("competitionScheduleDTO obj: " + competitionScheduleDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionScheduleDTO;

	}

	public CompetitionScheduleCommonDTO setCompetitionScheduledDTO(CompetitionSchedule competitionSchedule,
			CompetitionInitiation competitionInitiation) {

		CompetitionScheduleCommonDTO competitionScheduleCommonDTO = new CompetitionScheduleCommonDTO();
		try {
			LOGGER.info("competitionScheduleCommonDTO obj: " + competitionScheduleCommonDTO);
			BeanPropertiesUtil.copyProperties(competitionSchedule, competitionScheduleCommonDTO);
			competitionScheduleCommonDTO
					.setRegistrationStartDateStr(Validator.isNotNull(competitionSchedule.getRegistrationStartDate())
							? formatter.format(competitionSchedule.getRegistrationStartDate())
							: "");

			competitionScheduleCommonDTO
					.setRegistrationEndDateStr(Validator.isNotNull(competitionSchedule.getRegistrationEndDate())
							? formatter.format(competitionSchedule.getRegistrationEndDate())
							: "");

			competitionScheduleCommonDTO
					.setLastSubmissionDateStr(Validator.isNotNull(competitionSchedule.getLastSubmissionDate())
							? formatter.format(competitionSchedule.getLastSubmissionDate())
							: "");

			LOGGER.info("competitionSchedule.getStartTime(): " + competitionSchedule.getStartTime());
			competitionScheduleCommonDTO.setFormattedStartTime(Validator.isNotNull(competitionSchedule.getStartTime())
					? dateConversionUtil.formatTimeForView(competitionSchedule.getStartTime())
					: "");

			competitionScheduleCommonDTO
					.setFormattedReportingTime(Validator.isNotNull(competitionSchedule.getReportingTime())
							? dateConversionUtil.formatTimeForView(competitionSchedule.getReportingTime())
							: "");

			competitionScheduleCommonDTO.setCompetitionScheduledId(competitionSchedule.getCompetitionScheduleId());
			competitionScheduleCommonDTO
					.setCompetitionStartDateStr(formatter.format(competitionInitiation.getCompetitionStartDate()));
			competitionScheduleCommonDTO
					.setCompetitionEndDateStr(formatter.format(competitionInitiation.getCompetitionEndDate()));
			competitionScheduleCommonDTO.setSportName(Validator
					.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId()))
							? SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId())
									.getName_en()
							: "");
			LOGGER.info("competitionScheduledId: " + competitionScheduleCommonDTO.getCompetitionScheduledId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return competitionScheduleCommonDTO;

	}

	public ResultUploadCommonDTO setResultUpload(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {

		ResultUploadCommonDTO resultUploadCommonDTO = new ResultUploadCommonDTO();
		try {
			resultUploadCommonDTO
					.setCompetitionScheduledId(ParamUtil.getLong(resourceRequest, "competitionScheduledId"));
			resultUploadCommonDTO
					.setCompetitionInitiationId(ParamUtil.getLong(resourceRequest, "competitionInitiationId"));
			resultUploadCommonDTO.setReachedGround(ParamUtil.getBoolean(resourceRequest, "reachedGround"));
			resultUploadCommonDTO.setWinnerName(ParamUtil.getString(resourceRequest, "winnerName"));
			resultUploadCommonDTO.setFirstRunnerUpName(ParamUtil.getString(resourceRequest, "firstRunnerUpName"));
			resultUploadCommonDTO.setSecondRunnerUpName(ParamUtil.getString(resourceRequest, "secondRunnerUpName"));
			resultUploadCommonDTO.setThirdRunnerUpName(ParamUtil.getString(resourceRequest, "thirdRunnerUpName"));
			resultUploadCommonDTO.setSelfDeclaration(ParamUtil.getBoolean(resourceRequest, "selfDeclaration"));
			resultUploadCommonDTO.setCreateDate(new Date());
			resultUploadCommonDTO.setModifiedDate(new Date());
			resultUploadCommonDTO.setUserId(themeDisplay.getUserId());
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long firstScoreSheetFileEntryId = fileUploadUtil.uploadFile(uploadPortletRequest, "firstRunnerUpScoreSheet",
					"Competition", serviceContext);
			long secondAndThirdRunnerUpScoreSheetId = fileUploadUtil.uploadFile(uploadPortletRequest,
					"secondAndThirdRunnerUpScoreSheet", "Competition", serviceContext);
			LOGGER.info("firstScoreSheetFileEntryId: " + firstScoreSheetFileEntryId);
			LOGGER.info("secondAndThirdRunnerUpScoreSheetId: " + secondAndThirdRunnerUpScoreSheetId);
			resultUploadCommonDTO.setFirstScoreSheetFileEntryId(firstScoreSheetFileEntryId);
			resultUploadCommonDTO.setSecondAndThirdScoreSheetFileEntryId(secondAndThirdRunnerUpScoreSheetId);
//			List<String> roles = Arrays.asList(RoleConstant.PT_TEACHER, RoleConstant.PRINCIPAL);
//			fileUploadUtil.setFilePermissionsByRoleNames(firstScoreSheetFileEntryId, serviceContext, themeDisplay,
//					RoleConstant.PT_TEACHER);
			LOGGER.info("resultUploadCommonDTO obj: " + resultUploadCommonDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultUploadCommonDTO;

	}

	public ResultUploadCommonDTO setCompetitionResultUploadDTO(CompetitionResultUpload competitionResultUpload,
			CompetitionSchedule competitionSchedule, ThemeDisplay themeDisplay) {

		ResultUploadCommonDTO resultUploadCommonDTO = new ResultUploadCommonDTO();
		try {
			LOGGER.info("ResultUploadCommonDTO obj: " + competitionResultUpload);
			BeanPropertiesUtil.copyProperties(competitionResultUpload, resultUploadCommonDTO);

			resultUploadCommonDTO.setCompetitionScheduledId(competitionSchedule.getCompetitionScheduleId());
			resultUploadCommonDTO.setCompetitionName(competitionSchedule.getCompetitionName());
			if (resultUploadCommonDTO.getFirstScoreSheetFileEntryId() > 0) {
				DLFileEntry firstScoreSheetFileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(resultUploadCommonDTO.getFirstScoreSheetFileEntryId());
				if (Validator.isNotNull(firstScoreSheetFileEntry)) {
					String fileName = firstScoreSheetFileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedfirstScoreSheetFilename = fileName.substring(underscoreIndex + 1);
						resultUploadCommonDTO.setFirstFileEntryName(trimmedfirstScoreSheetFilename);
					}
					resultUploadCommonDTO.setFirstFileURL(fileUploadUtil
							.getPreviewURL(resultUploadCommonDTO.getFirstScoreSheetFileEntryId(), themeDisplay));
				}
			}
			if (resultUploadCommonDTO.getSecondAndThirdScoreSheetFileEntryId() > 0) {
				DLFileEntry secondScoreSheetFileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(resultUploadCommonDTO.getSecondAndThirdScoreSheetFileEntryId());
				if (Validator.isNotNull(secondScoreSheetFileEntry)) {
					String fileName = secondScoreSheetFileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedSecondScoreSheetFilename = fileName.substring(underscoreIndex + 1);
						resultUploadCommonDTO.setSecondFileEntryName(trimmedSecondScoreSheetFilename);
					}
					resultUploadCommonDTO.setSecondFileURL(fileUploadUtil.getPreviewURL(
							resultUploadCommonDTO.getSecondAndThirdScoreSheetFileEntryId(), themeDisplay));
				}
			}

			LOGGER.info("competitionResultUploadid: " + resultUploadCommonDTO.getCompetitionResultUploadId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultUploadCommonDTO;

	}
}
