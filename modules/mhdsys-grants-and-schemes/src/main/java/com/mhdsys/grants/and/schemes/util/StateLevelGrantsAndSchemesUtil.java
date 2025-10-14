package com.mhdsys.grants.and.schemes.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.AspiringAthleteCommonDTO;
import com.mhdsys.common.pojo.AwardWinnerCommonDTO;
import com.mhdsys.common.pojo.CouncilSportCompetitionDetailsCommonDTO;
import com.mhdsys.common.pojo.FinancialAssistanceCommonDTO;
import com.mhdsys.common.pojo.IntSportsCompCommonDTO;
import com.mhdsys.common.pojo.profile.CoachAndSportPersonDTO;
import com.mhdsys.common.pojo.profile.CoachSportPersonDTO;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AspiringAthlete;
import com.mhdsys.schema.model.AwardWinner;
import com.mhdsys.schema.model.FinancialAssistance;
import com.mhdsys.schema.model.IntSportsComp;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.AspiringAthleteLocalServiceUtil;
import com.mhdsys.schema.service.AwardWinnerLocalServiceUtil;
import com.mhdsys.schema.service.FinancialAssistanceLocalServiceUtil;
import com.mhdsys.schema.service.IntSportsCompLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = StateLevelGrantsAndSchemesUtil.class)

public class StateLevelGrantsAndSchemesUtil {
	private static Log LOGGER = LogFactoryUtil.getLog(StateLevelGrantsAndSchemesUtil.class);

	@Reference
	private static DateConversionUtil dateConversionUtil;

	@Reference
	FileUploadUtil fileUploadUtil;

	

	public FinancialAssistanceCommonDTO setFinancialAssistanceCommonDTO(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long financialAssistanceId) {
		FinancialAssistanceCommonDTO commonDTO = new FinancialAssistanceCommonDTO();
		try {

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			if (isDeskOfficer || isDeputyDirector) {
				FinancialAssistance financialAssistance = FinancialAssistanceLocalServiceUtil
						.getFinancialAssistance(financialAssistanceId);
				BeanPropertiesUtil.copyProperties(financialAssistance, commonDTO);

				if (isDeskOfficer) {
					commonDTO.setHoVerification(ParamUtil.getString(resourceRequest, "hoVerification"));
					commonDTO.setHoRemarks(ParamUtil.getString(resourceRequest, "hoRemarks"));

				} else if (isDeputyDirector) {
					commonDTO.setDdVerification(ParamUtil.getString(resourceRequest, "ddVerification"));
					commonDTO.setDdRemarks(ParamUtil.getString(resourceRequest, "ddRemarks"));

				}

			} else {

				LOGGER.info("ParamUtil.getString(resourceRequest, \"applicantNameF\"):::::::::::::"+ ParamUtil.getString(resourceRequest, "applicantNameF"));
				commonDTO.setFullName(ParamUtil.getString(resourceRequest, "applicantNameF"));
				commonDTO.setAddress(ParamUtil.getString(resourceRequest, "address"));
				commonDTO.setPinCode(ParamUtil.getLong(resourceRequest, "pincodeF"));
				commonDTO.setMobileNumber(ParamUtil.getString(resourceRequest, "contactNumber"));
				commonDTO.setEmailId(ParamUtil.getString(resourceRequest, "emailId"));
				commonDTO.setFinancialAssistanceId(ParamUtil.getLong(resourceRequest, "financialAssistanceId"));
				// Sports Performance
				commonDTO.setSportsPerformanceDetails(ParamUtil.getString(resourceRequest, "sportsPerformanceDetails"));
				commonDTO.setCertifiedLetter(ParamUtil.getString(resourceRequest, "certifiedLetter"));
//	        commonDTO.setCertifiedLetterFile(ParamUtil.getLong(resourceRequest, "certifiedLetterFile"));

				// Financial Details
				commonDTO.setAssistanceMatter(ParamUtil.getString(resourceRequest, "assistanceMatter"));
//	        commonDTO.setAssistanceMatterFile(ParamUtil.getLong(resourceRequest, "assistanceMatterFile"));

				commonDTO.setItemsEstimate(ParamUtil.getString(resourceRequest, "itemsEstimate"));
//	        commonDTO.setItemsEstimateFile(ParamUtil.getLong(resourceRequest, "itemsEstimateFile"));

				commonDTO.setExpenseBudget(ParamUtil.getString(resourceRequest, "expenseBudget"));
//	        commonDTO.setExpenseBudgetFile(ParamUtil.getLong(resourceRequest, "expenseBudgetFile"));

				commonDTO.setPriceList(ParamUtil.getString(resourceRequest, "priceList"));
//	        commonDTO.setPriceListFile(ParamUtil.getLong(resourceRequest, "priceListFile"));

				commonDTO.setExpenditureEstimate(ParamUtil.getString(resourceRequest, "expenditureEstimate"));
//	        commonDTO.setExpenditureEstimateFile(ParamUtil.getLong(resourceRequest, "expenditureEstimateFile"));

				commonDTO.setWorkReport(ParamUtil.getString(resourceRequest, "workReport"));
//	        commonDTO.setWorkReportFile(ParamUtil.getLong(resourceRequest, "workReportFile"));

				// Declaration
				LOGGER.info("ParamUtil.getBoolean(resourceRequest, \"isDeclarationAccepted\" "
						+ ParamUtil.getBoolean(resourceRequest, "isDeclarationAccepted"));
				commonDTO.setDeclarationAccepted(ParamUtil.getBoolean(resourceRequest, "isDeclarationAccepted", false));

				// User and Dates
				commonDTO.setUserId(themeDisplay.getUserId());
				commonDTO.setCreateDate(new Date());
				commonDTO.setModifiedDate(new Date());

				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
						uploadPortletRequest);

				commonDTO.setCertifiedLetterFile(handleFileUpload(uploadPortletRequest, "certifiedLetterFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setAssistanceMatterFile(handleFileUpload(uploadPortletRequest, "assistanceMatterFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setItemsEstimateFile(handleFileUpload(uploadPortletRequest, "itemsEstimateFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setExpenseBudgetFile(handleFileUpload(uploadPortletRequest, "expenseBudgetFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setPriceListFile(
						handleFileUpload(uploadPortletRequest, "priceListFile", serviceContext, "Grants And Schemes"));
				commonDTO.setExpenditureEstimateFile(handleFileUpload(uploadPortletRequest, "expenditureEstimateFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setWorkReportFile(
						handleFileUpload(uploadPortletRequest, "workReportFile", serviceContext, "Grants And Schemes"));
			}
		} catch (Exception e) {
			LOGGER.info("Error in setFinancialAssistanceCommonDTO: " + e.getMessage());
		}
		return commonDTO;
	}

	public IntSportsCompCommonDTO setIntSportsCompCommonDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long intSportsCompId) {
		IntSportsCompCommonDTO commonDTO = new IntSportsCompCommonDTO();
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());

			if (isDeskOfficer || isDeputyDirector) {
				IntSportsComp intSportsComp = IntSportsCompLocalServiceUtil.getIntSportsComp(intSportsCompId);
				BeanPropertiesUtil.copyProperties(intSportsComp, commonDTO);

				if (isDeskOfficer) {
					commonDTO.setHoVerification(ParamUtil.getString(resourceRequest, "hoVerification"));
					commonDTO.setHoRemarks(ParamUtil.getString(resourceRequest, "hoRemarks"));

				} else if (isDeputyDirector) {
					commonDTO.setDdVerification(ParamUtil.getString(resourceRequest, "ddVerification"));
					commonDTO.setDdRemarks(ParamUtil.getString(resourceRequest, "ddRemarks"));

				}

			} else {

				// Basic Information
				// commonDTO.setIntSportsCompId(ParamUtil.getLong(resourceRequest,
				// "intSportsCompId"));
				commonDTO.setFullName(ParamUtil.getString(resourceRequest, "fullName"));
				commonDTO.setDob(ParamUtil.getString(resourceRequest, "dob"));
				commonDTO.setMobileNo(ParamUtil.getString(resourceRequest, "mobileNo"));
				commonDTO.setEmailId(ParamUtil.getString(resourceRequest, "emailId"));

				// Competition Details
				commonDTO.setCompetitionLevel(ParamUtil.getString(resourceRequest, "competitionLevel"));
				commonDTO.setCompetitionName(ParamUtil.getString(resourceRequest, "competitionName"));
				commonDTO.setCompetitionYear(ParamUtil.getString(resourceRequest, "competitionYear"));
//			commonDTO.setCompetitionDate(dateConversionUtil
//					.convertStringToDateFormat(ParamUtil.getString(resourceRequest, "competitionDate")));

				commonDTO.setCompetitionPlace(ParamUtil.getString(resourceRequest, "competitionPlace"));
				commonDTO.setCompetitionPerformance(ParamUtil.getString(resourceRequest, "competitionPerformance"));
				commonDTO.setCompetitionRank(ParamUtil.getString(resourceRequest, "competitionRank"));

				// Declaration
				commonDTO.setDeclarationAccepted(ParamUtil.getBoolean(resourceRequest, "isDeclarationAccepted", false));

				// User and Dates
				commonDTO.setUserId(themeDisplay.getUserId());
				commonDTO.setCreateDate(new Date());
				commonDTO.setModifiedDate(new Date());

				// Handle file uploads
				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
						uploadPortletRequest);

				// Document uploads
				commonDTO.setPanCard(
						handleFileUpload(uploadPortletRequest, "panCard", serviceContext, "Grants And Schemes"));
				commonDTO.setDomicileCertificate(handleFileUpload(uploadPortletRequest, "domicileCertificate",
						serviceContext, "Grants And Schemes"));
				commonDTO.setPassBook(
						handleFileUpload(uploadPortletRequest, "passBook", serviceContext, "Grants And Schemes"));
				commonDTO.setAadhaarCard(
						handleFileUpload(uploadPortletRequest, "aadhaarCard", serviceContext, "Grants And Schemes"));
				commonDTO.setCancelledCheck(
						handleFileUpload(uploadPortletRequest, "cancelledCheck", serviceContext, "Grants And Schemes"));

				commonDTO.setSelectionLetter(handleFileUpload(uploadPortletRequest, "selectionLetter", serviceContext,
						"Grants And Schemes"));
				commonDTO.setCompetitionCertificate(handleFileUpload(uploadPortletRequest, "competitionCertificate",
						serviceContext, "Grants And Schemes"));

				List<Long> actualItemBudgetsIds = new ArrayList<>();

				// Process existing files (kept in the UI)
				String[] existingItemBudgets = ParamUtil.getParameterValues(resourceRequest, "existingItemBudgets");
				String[] existingItemBudgetsIds = ParamUtil.getParameterValues(resourceRequest,
						"existingItemBudgetsIds");

				if (existingItemBudgets != null && existingItemBudgets.length > 0) {
					if (existingItemBudgets.length == existingItemBudgetsIds.length) {

						for (int i = 0; i < existingItemBudgets.length; i++) {
							String fileIdStr = existingItemBudgetsIds[i];
							LOGGER.info("Existing File - Name: " + ", ID: " + fileIdStr);

							try {
								long fileEntryId = Long.parseLong(fileIdStr);
								DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

								actualItemBudgetsIds.add(fileEntry.getFileEntryId());

							} catch (Exception e) {
								LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
							}
						}

						Method setItemBudgets = commonDTO.getClass().getMethod("setItemBudgets", String.class);
						setItemBudgets.invoke(commonDTO, actualItemBudgetsIds.toString());
					}
				}

				// Process new actual geo tag photos
				if (uploadPortletRequest.getFile("actualItemBudgets") != null) {
					File[] actualStorageFiles = uploadPortletRequest.getFiles("actualItemBudgets");
					LOGGER.info("SIZE ::::   " + actualStorageFiles.length);
					String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
							"actualItemBudgetsNames");

					for (int i = 0; i < actualStorageFiles.length; i++) {
						File file = actualStorageFiles[i];
						String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
						LOGGER.info("File Name ::::  " + fileName);
						long fileEntryId = multipleFileUpload(uploadPortletRequest, "actualItemBudgets",
								"Grants And Schemes", serviceContext, fileName, file);
						actualItemBudgetsIds.add(fileEntryId);
					}

					LOGGER.info("Budget Items Files : " + actualItemBudgetsIds.toString());
					Method setItemBudgets = commonDTO.getClass().getMethod("setItemBudgets", String.class);
					setItemBudgets.invoke(commonDTO, actualItemBudgetsIds.toString());
				}

//			commonDTO.setAffidavit(
//					handleFileUpload(uploadPortletRequest, "affidavitFile", serviceContext, "Grants And Schemes"));
//			commonDTO.setGuranteeLetter(
//					handleFileUpload(uploadPortletRequest, "guranteeLetterFile", serviceContext, "Grants And Schemes"));

			}

		} catch (Exception e) {
			LOGGER.error("Error in setIntSportsCompCommonDTO: " + e.getMessage(), e);
		}
		return commonDTO;
	}
	
	

	public AwardWinnerCommonDTO setAwardWinnerCommonDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long awardWinnerId) {
		AwardWinnerCommonDTO commonDTO = new AwardWinnerCommonDTO();

		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			if (isDeskOfficer || isDeputyDirector) {
				AwardWinner awardWinner = AwardWinnerLocalServiceUtil.getAwardWinner(awardWinnerId);
				BeanPropertiesUtil.copyProperties(awardWinner, commonDTO);

				if (isDeskOfficer) {
					commonDTO.setHoVerification(ParamUtil.getString(resourceRequest, "hoVerification"));
					commonDTO.setHoRemarks(ParamUtil.getString(resourceRequest, "hoRemarks"));

				} else if (isDeputyDirector) {
					commonDTO.setDdVerification(ParamUtil.getString(resourceRequest, "ddVerification"));
					commonDTO.setDdRemarks(ParamUtil.getString(resourceRequest, "ddRemarks"));

				}

			} else {

				commonDTO.setAwardWinnerId(ParamUtil.getLong(resourceRequest, "awardWinnerId"));

				// Personal Details
				commonDTO.setFullName(ParamUtil.getString(resourceRequest, "fullNameA"));
				commonDTO.setDob(ParamUtil.getDate(resourceRequest, "dobA", new SimpleDateFormat("yyyy-MM-dd")));
				commonDTO.setMobileNumber(ParamUtil.getString(resourceRequest, "mobileNumberA"));
				commonDTO.setEmailId(ParamUtil.getString(resourceRequest, "emailIdA"));

				// Competition Details
				commonDTO.setCompetitionLevel(ParamUtil.getString(resourceRequest, "competitionLevelA"));
				commonDTO.setCompetitionName(ParamUtil.getString(resourceRequest, "competitionNameA"));
				commonDTO.setCompetitionYear(ParamUtil.getInteger(resourceRequest, "competitionYearA"));
				commonDTO.setCompetitionDate(
						ParamUtil.getDate(resourceRequest, "competitionDateA", new SimpleDateFormat("yyyy-MM-dd")));
				commonDTO.setPlaceOfCompetition(ParamUtil.getString(resourceRequest, "placeOfCompetitionA"));
				commonDTO.setCompetitionPerformance(ParamUtil.getString(resourceRequest, "competitionPerformanceA"));
				commonDTO.setCompetitionRank(ParamUtil.getString(resourceRequest, "competitionRankA"));

				// Bank Details
				commonDTO.setBankName(ParamUtil.getString(resourceRequest, "bankNameA"));
				commonDTO.setIfscCode(ParamUtil.getString(resourceRequest, "ifscCodeA"));
				commonDTO.setAccountNumber(ParamUtil.getString(resourceRequest, "accountNumberA"));

				// Declaration
				commonDTO
						.setDeclarationAccepted(ParamUtil.getBoolean(resourceRequest, "isDeclarationAcceptedA", false));

				// User and Dates
				commonDTO.setUserId(themeDisplay.getUserId());
				commonDTO.setCreateDate(new Date());
				commonDTO.setModifiedDate(new Date());

				// File Uploads
				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
						uploadPortletRequest);

				commonDTO.setAadhaarCardFile(handleFileUpload(uploadPortletRequest, "aadhaarCardFile", serviceContext,
						"Grants And Schemes"));
				commonDTO.setPanCardFile(
						handleFileUpload(uploadPortletRequest, "panCardFile", serviceContext, "Grants And Schemes"));
				commonDTO.setDomicileCertificateFile(handleFileUpload(uploadPortletRequest, "domicileCertificateFile",
						serviceContext, "Grants And Schemes"));

				commonDTO.setCompetitionDrawFile(handleFileUpload(uploadPortletRequest, "competitionDrawFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setOrganizationSelectionLetterFile(handleFileUpload(uploadPortletRequest,
						"organizationSelectionLetterFile", serviceContext, "Grants And Schemes"));
				commonDTO.setCompetitionResultFile(handleFileUpload(uploadPortletRequest, "competitionResultFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setCompetitionCertificateFile(handleFileUpload(uploadPortletRequest,
						"competitionCertificateFile", serviceContext, "Grants And Schemes"));

				commonDTO.setBankStatementFile(handleFileUpload(uploadPortletRequest, "bankStatementFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setBankPassbookFile(handleFileUpload(uploadPortletRequest, "bankPassbookFile", serviceContext,
						"Grants And Schemes"));
				commonDTO.setCancelledCheckFile(handleFileUpload(uploadPortletRequest, "cancelledCheckFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setPlayersConsentLetterFile(handleFileUpload(uploadPortletRequest, "playersConsentLetterFile",
						serviceContext, "Grants And Schemes"));
				commonDTO.setAffidavitFile(
						handleFileUpload(uploadPortletRequest, "affidavitFile", serviceContext, "Grants And Schemes"));
			}
		} catch (Exception e) {
			LOGGER.error("Error in setAwardWinnerCommonDTO: " + e.getMessage(), e);
		}

		return commonDTO;
	}
	
	public AspiringAthleteCommonDTO setAspiringAthleteCommonDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
	        long aspiringAthleteId) {

	    AspiringAthleteCommonDTO commonDTO = new AspiringAthleteCommonDTO();

	    try {
	        User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
	        boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
	        boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());

	        if (isDeskOfficer || isDeputyDirector) {
	            AspiringAthlete aspiringAthlete = AspiringAthleteLocalServiceUtil.getAspiringAthlete(aspiringAthleteId);
	            BeanPropertiesUtil.copyProperties(aspiringAthlete, commonDTO);

	        } else {
	            commonDTO.setAspiringAthleteId(ParamUtil.getLong(resourceRequest, "aspiringAthleteId"));

	            // Personal Details
	            commonDTO.setNameOfApplicant(ParamUtil.getString(resourceRequest, "nameOfApplicant"));
	            commonDTO.setAddress(ParamUtil.getString(resourceRequest, "address"));
	            commonDTO.setPinCode(ParamUtil.getLong(resourceRequest, "pinCode"));
	            commonDTO.setContactNumber(ParamUtil.getLong(resourceRequest, "contactNumber"));
	            commonDTO.setEmailId(ParamUtil.getString(resourceRequest, "emailId"));

	            // Sport Details
	            commonDTO.setSportName(ParamUtil.getString(resourceRequest, "sportName"));
	            commonDTO.setSportType(ParamUtil.getString(resourceRequest, "sportType"));
	            commonDTO.setCompetitionLevel(ParamUtil.getString(resourceRequest, "competitionLevel"));
	            commonDTO.setCompetitionName(ParamUtil.getString(resourceRequest, "competitionName"));
	            commonDTO.setPlaceOfCompetition(ParamUtil.getString(resourceRequest, "placeOfCompetition"));
	            commonDTO.setCompetitionDate(ParamUtil.getDate(resourceRequest, "competitionDate", new SimpleDateFormat("yyyy-MM-dd")));
	            commonDTO.setCompetitionRank(ParamUtil.getString(resourceRequest, "competitionRank"));
	            commonDTO.setMedalAchieved(ParamUtil.getString(resourceRequest, "medalAchieved"));

	            // Previous Competition
	            commonDTO.setPrevCompetitionName(ParamUtil.getString(resourceRequest, "prevCompetitionName"));
	            commonDTO.setPrevPlace(ParamUtil.getString(resourceRequest, "prevPlace"));
	            commonDTO.setPastCompetitionYear(ParamUtil.getLong(resourceRequest, "pastCompetitionYear"));
	            commonDTO.setPastCompetitionDate(ParamUtil.getDate(resourceRequest, "pastCompetitionDate", new SimpleDateFormat("yyyy-MM-dd")));
	            commonDTO.setPastCompetitionRank(ParamUtil.getString(resourceRequest, "pastCompetitionRank"));
	            commonDTO.setGrantedSport(ParamUtil.getString(resourceRequest, "grantedSport"));
	            commonDTO.setGrantedSportName(ParamUtil.getString(resourceRequest, "grantedSportName"));

	            // Financial Details
	            commonDTO.setTravelCost(ParamUtil.getString(resourceRequest, "travelCost"));
	            commonDTO.setEntryFees(ParamUtil.getString(resourceRequest, "entryFees"));
	            commonDTO.setHotelFees(ParamUtil.getString(resourceRequest, "hotelFees"));
	            commonDTO.setMealCost(ParamUtil.getString(resourceRequest, "mealCost"));
	            commonDTO.setPreCompetitionTrainingFees(ParamUtil.getString(resourceRequest, "preCompetitionTrainingFees"));
	            commonDTO.setTrainingEquipmentCost(ParamUtil.getString(resourceRequest, "trainingEquipmentCost"));
	            commonDTO.setGuidanceFeeFromExpert(ParamUtil.getString(resourceRequest, "guidanceFeeFromExpert"));
	            commonDTO.setTrainingFee(ParamUtil.getString(resourceRequest, "trainingFee"));
	            commonDTO.setCostOfImportSportEquipment(ParamUtil.getString(resourceRequest, "costOfImportSportEquipment"));
	            commonDTO.setTotalCost(ParamUtil.getString(resourceRequest, "totalCost"));

	            // Bank Details
	            commonDTO.setBankName(ParamUtil.getString(resourceRequest, "bankName"));
	            commonDTO.setIfscCode(ParamUtil.getString(resourceRequest, "ifscCode"));
	            commonDTO.setAccountNumber(ParamUtil.getLong(resourceRequest, "accountNumber"));

	            // Declaration
	            
	            commonDTO.setDeclarationAccepted(ParamUtil.getBoolean(resourceRequest, "isDeclarationAcceptedCa", false));

	            // User and Dates
	            commonDTO.setUserId(themeDisplay.getUserId());
	            commonDTO.setCreateDate(new Date());
	            commonDTO.setModifiedDate(new Date());

	            // File Uploads
	            UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
	            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadPortletRequest);

	            commonDTO.setDomicileFile(handleFileUpload(uploadPortletRequest, "domicileFile", serviceContext, "Grants And Schemes"));
	            commonDTO.setSelectionLetterFile(handleFileUpload(uploadPortletRequest, "selectionLetterFile", serviceContext, "Grants And Schemes"));
	            commonDTO.setSelectionCertificateFile(handleFileUpload(uploadPortletRequest, "selectionCertificateFile", serviceContext, "Grants And Schemes"));
	            commonDTO.setNewRecordCertificateFile(handleFileUpload(uploadPortletRequest, "newRecordCertificateFile", serviceContext, "Grants And Schemes"));
	            commonDTO.setBankStatement(handleFileUpload(uploadPortletRequest, "bankStatement", serviceContext, "Grants And Schemes"));
	            commonDTO.setAffidavit(handleFileUpload(uploadPortletRequest, "affidavit", serviceContext, "Grants And Schemes"));
	            commonDTO.setAffidavitBondpaper(handleFileUpload(uploadPortletRequest, "affidavitBondpaper", serviceContext, "Grants And Schemes"));
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error in setAspiringAthleteCommonDTO: " + e.getMessage(), e);
	    }

	    return commonDTO;
	}

	
	
	public AwardWinnerCommonDTO getAwardWinnerCommonDTO(AwardWinner awardWinner, ThemeDisplay themeDisplay) {
		AwardWinnerCommonDTO awardWinnerCommonDTO = new AwardWinnerCommonDTO();
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			BeanPropertiesUtil.copyProperties(awardWinner, awardWinnerCommonDTO);
			LOGGER.info(awardWinnerCommonDTO);
		} catch (Exception e) {
			LOGGER.info("Error :::::::::::::::::: " + e.getMessage());
		}
		return awardWinnerCommonDTO;
	}

	public IntSportsCompCommonDTO getIntSportsCompCommonDTO(IntSportsComp intSportsComp, ThemeDisplay themeDisplay) {
		IntSportsCompCommonDTO intSportsCompCommonDTO = new IntSportsCompCommonDTO();
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			BeanPropertiesUtil.copyProperties(intSportsComp, intSportsCompCommonDTO);
			LOGGER.info(intSportsCompCommonDTO);
		} catch (Exception e) {
			LOGGER.info("Error :::::::::::::::::: " + e.getMessage());
		}
		return intSportsCompCommonDTO;
	}

	public FinancialAssistanceCommonDTO getFinancialAssistanceCommonDTO(FinancialAssistance financialAssistance,
			ThemeDisplay themeDisplay) {
		FinancialAssistanceCommonDTO financialAssistanceCommonDTO = new FinancialAssistanceCommonDTO();
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			BeanPropertiesUtil.copyProperties(financialAssistance, financialAssistanceCommonDTO);
			LOGGER.info(financialAssistanceCommonDTO);
		} catch (Exception e) {
			LOGGER.info("Error :::::::::::::::::: " + e.getMessage());
		}
		return financialAssistanceCommonDTO;
	}

	private long handleFileUpload(UploadPortletRequest uploadPortletRequest, String fileParamName,
			ServiceContext serviceContext, String folderName) {
		try {
			if (uploadPortletRequest != null && uploadPortletRequest.getFile(fileParamName) != null) {
				return fileUploadUtil.uploadFile(uploadPortletRequest, fileParamName, folderName, serviceContext);
			}
		} catch (Exception e) {
			LOGGER.error("Error uploading file for: " + fileParamName, e);
		}

		return 0L;
	}

	// File Upload
	public static long multipleFileUpload(UploadPortletRequest uploadPortletRequest, String fileParamName,
			String folderName, ServiceContext serviceContext, String fileName, File file) {
		try {
			LOGGER.info("fileName:" + fileName);
			fileName = HtmlUtil.stripHtml(fileName);
			fileName = HtmlParserUtil.extractText(fileName);
			LOGGER.info(fileName);
			LOGGER.info("File ::  " + file);
			Date date = new Date();
			if (Validator.isNotNull(file) && Validator.isNotNull(fileName)) {
				String description = fileName;
				fileName = date.getTime() + "_" + fileName;
				String mimeType = uploadPortletRequest.getContentType(fileParamName);
				String title = fileName;
				if (validatePDFFileType(fileName, mimeType) || validateImageFileType(fileName, mimeType)) {
					LOGGER.debug("file" + file + "  fileName " + fileName);
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
						LOGGER.error(
								"Exception in uploading file in FileUpload :: uploadClaimFile ::" + e.getMessage());
						LOGGER.error(e);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return 0;
	}

	public void setFilePermissionByRoleName(long fileEntryId, ServiceContext serviceContext, ThemeDisplay themeDisplay,
			String roleName) {
		try {
			Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), roleName);
			ResourcePermissionLocalServiceUtil.setResourcePermissions(serviceContext.getCompanyId(),
					DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntryId),
					role.getRoleId(), new String[] { ActionKeys.VIEW, ActionKeys.DOWNLOAD, ActionKeys.ADD_DOCUMENT,
							ActionKeys.ADD_FILE, ActionKeys.ADD_ATTACHMENT });
		} catch (PortalException e) {
			LOGGER.error(e);
		}
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
//			Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ._-]+$", Pattern.CASE_INSENSITIVE);
//			Matcher matcher = pattern.matcher(orgiFileName);
//			boolean matchFound = matcher.find();
//			LOGGER.info("matchFound---" + matchFound);
		Date date = new Date();
		if (Validator.isNotNull(file) && Validator.isNotNull(fileName)) {
			String description = fileName;
			fileName = date.getTime() + "_" + fileName;
			String mimeType = uploadPortletRequest.getContentType(fileParamName);
			String title = fileName;
			if (validatePDFFileType(orgiFileName, mimeType) || validateImageFileType(orgiFileName, mimeType)) {
				LOGGER.debug("file" + file + "  fileName " + fileName);
//						long repositoryId = themeDisplay.getScopeGroupId();
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

	public boolean deleteFileEntries(HashSet<Long> oldFiles) {
		boolean flag = true;
		if (oldFiles.size() > 0) {
			for (Long fileEntryId : oldFiles) {
				try {
					DLAppServiceUtil.deleteFileEntry(fileEntryId);
				} catch (PortalException e) {
					e.printStackTrace();
					flag = false;
				}
			}
			return flag;
		}
		return false;
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

	/**
	 * Validate Audio file
	 */
	public boolean validateAudioFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("audio/mpeg");
		List<String> validExt = new ArrayList<String>();
		validExt.add("mp3");
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
	 * Validate Video file
	 */
	public boolean validateVideoFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("video/mp4");
		List<String> validExt = new ArrayList<String>();
		validExt.add("mp4");
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
	 * 
	 * @param themeDisplay
	 * @param folderName
	 * @return
	 * @throws PortalException
	 */
	public static Folder getFolder(long groupId, String folderName) throws PortalException {

		Folder folder = DLAppServiceUtil.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName);
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folder;

	}

	/**
	 * 
	 * @param fileEntryId
	 * @param themeDisplay
	 * @return
	 * @throws PortalException
	 */
	public static String getDownloadURL(long fileEntryId, ThemeDisplay themeDisplay) throws PortalException {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
		;
		FileVersion fileVersion = fileEntry.getLatestFileVersion();
		String downloadURL = DLURLHelperUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK);
		return downloadURL;
	}

	/**
	 * 
	 * @param fileEntryId
	 * @param themeDisplay
	 * @return
	 * @throws PortalException
	 */
	public String getPreviewURL(long fileEntryId, ThemeDisplay themeDisplay) throws PortalException {
		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
		FileVersion fileVersion = fileEntry.getLatestFileVersion();
		return DLURLHelperUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK);
	}

	public static String getFileName(long fileEntryId, ThemeDisplay themeDisplay) throws PortalException {
		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
		return fileEntry.getDescription();
	}

	public JSONObject getUploadedFileDetails(ThemeDisplay themeDisplay, long fileEntryId) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {
			jsonObject.put("fileId", fileEntryId);
			jsonObject.put("fileName", getFileName(fileEntryId, themeDisplay));
			jsonObject.put("filePreviewUrl", getPreviewURL(fileEntryId, themeDisplay));
			return jsonObject;
		} catch (Exception e) {
			return JSONFactoryUtil.createJSONObject();
		}

	}

	/**
	 * Validate BMP file types
	 */
	public boolean validateBMPFileType(String fileName, String mimeType) {
		// List of valid MIME types
		List<String> validMime = new ArrayList<>();
		validMime.add("image/bmp");

		// List of valid extensions
		List<String> validExt = new ArrayList<>();
		validExt.add("bmp");

		// Validate MIME type
		boolean isValidMime = validMime.contains(mimeType);

		// Extract file extension and validate
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1).toLowerCase();
		}
		boolean isValidExt = validExt.contains(extension);

		// Return true if both MIME type and extension are valid
		return isValidMime && isValidExt;
	}

	public boolean validatePPTFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("application/vnd.ms-powerpoint");
		List<String> validExt = new ArrayList<String>();
		validExt.add("ppt");

		boolean isValidMime = validMime.contains(mimeType);
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		boolean isValidExt = validExt.contains(extension);
		return isValidMime && isValidExt;
	}

	public boolean validatePPTXFileType(String fileName, String mimeType) {
		List<String> validMime = new ArrayList<String>();
		validMime.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		List<String> validExt = new ArrayList<String>();
		validExt.add("pptx");

		boolean isValidMime = validMime.contains(mimeType);
		String extension = StringPool.BLANK;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		boolean isValidExt = validExt.contains(extension);
		return isValidMime && isValidExt;
	}

	public void setFilePermissionsByRoleNames(long fileEntryId, ServiceContext serviceContext,
			ThemeDisplay themeDisplay, List<String> roleNames) {
		try {
			for (String roleName : roleNames) {
				Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), roleName);
				if (role != null) {
					ResourcePermissionLocalServiceUtil.setResourcePermissions(serviceContext.getCompanyId(),
							DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
							String.valueOf(fileEntryId), role.getRoleId(),
							new String[] { ActionKeys.VIEW, ActionKeys.DOWNLOAD });
				} else {
					LOGGER.warn("Role not found: " + roleName);
				}
			}
		} catch (PortalException e) {
			LOGGER.error("Error while setting file permissions: " + e.getMessage(), e);
		}
	}

	public long multipleCertificateUpload(UploadPortletRequest uploadPortletRequest, String fileParamName,
			String folderName, ServiceContext serviceContext, String fileName, File file) throws IOException {

		LOGGER.info("fileName:" + fileName);
		fileName = HtmlUtil.stripHtml(fileName);
		fileName = HtmlParserUtil.extractText(fileName);
		LOGGER.info(fileName);
		LOGGER.info("File ::  " + file);
		Date date = new Date();
		if (Validator.isNotNull(file) && Validator.isNotNull(fileName)) {
			String description = fileName;
			fileName = date.getTime() + "_" + fileName;

			// Get mimeType from the file itself rather than the request parameter
			String mimeType = Files.probeContentType(file.toPath());
			if (mimeType == null) {
				mimeType = MimeTypesUtil.getContentType(file);
			}

			String title = fileName;
			if (validatePDFFileType(fileName, mimeType) || validateImageFileType(fileName, mimeType)) {
				LOGGER.debug("file" + file + "  fileName " + fileName);
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
					LOGGER.error("Exception in uploading file in FileUpload :: uploadClaimFile ::" + e.getMessage());
					LOGGER.error(e);
				}
			} else {
				LOGGER.error("Invalid file type for file: " + fileName + " with mimeType: " + mimeType);
			}
		}
		return 0;
	}

	/* Get default user to add other users */
	public User getDefaultUser() {
		User defaultUser = null;
		Company company = null;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			defaultUser = userLocalService.getDefaultUser(company.getCompanyId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return defaultUser;
	}

	public User getCompanyAdminUser(long companyId, long adminRoleId) {
		User adminUser = null;
		List<User> adminUsersList = userLocalService.getRoleUsers(adminRoleId); // get it from role util
		if (Validator.isNotNull(adminUsersList) && adminUsersList.size() > 0) {
			adminUser = adminUsersList.get(0);
		}
		return adminUser;
	}

	public long getDefaultCompanyId() {
		Company company = null;
		long companyId = 0l;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			companyId = company.getCompanyId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return companyId;
	}

	public long getDefaultScopeGroupId() {
		Company company = null;
		long groupId = 0l;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			groupId = company.getGroup().getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return groupId;
	}

	public long getSiteGroupId(long companyId) {
		try {
			// return groupLocalService.fetchFriendlyURLGroup(companyId,
			// "/maharashtra-sports").getGroupId();
			return groupLocalService.getGroup(companyId, "Guest").getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public long getUsercompanyId(long userId) {
		User user = null;
		try {
			user = userLocalService.getUser(userId);
			return user.getCompanyId();
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return 0;

	}

	public long getGlobalCompanyGroupId() {
		long groupId = 0l;
		Company company = null;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			groupId = company.getGroup().getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return groupId;
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

	public long[] getRoleId(String regType) {
		long[] roleIds = new long[1];
		Role role = null;
		try {
			role = roleLocalService.getRole(getDefaultCompanyId(), regType);
			roleIds[0] = role.getRoleId();
		} catch (Exception e) {
			roleIds[0] = 0l;
			LOGGER.error(e.getMessage());
		}
		return roleIds;
	}

	public CouncilSportCompetitionDetailsCommonDTO setCouncilSportCompetitionDetails(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {
		CouncilSportCompetitionDetailsCommonDTO dto = new CouncilSportCompetitionDetailsCommonDTO();
		try {
			dto.setUserId(themeDisplay.getUserId());
			dto.setCreateDate(new Date());
			dto.setModifiedDate(new Date());

			// Basic Fields
			dto.setCouncilOrganizationName(ParamUtil.getString(resourceRequest, "councilOrganizationName"));
			dto.setCouncilOrganizationAddress(ParamUtil.getString(resourceRequest, "councilOrganizationAddress"));
			dto.setCouncilCompetitionName(ParamUtil.getString(resourceRequest, "councilCompetitionName"));
			dto.setCouncilCompetitionLevel(ParamUtil.getString(resourceRequest, "councilCompetitionLevel"));
			dto.setCouncilCompetitionSponsor(ParamUtil.getString(resourceRequest, "councilCompetitionSponsor"));
			dto.setCouncilSponsorAddress(ParamUtil.getString(resourceRequest, "councilSponsorAddress"));
			dto.setCouncilTeamPlayersCount(ParamUtil.getInteger(resourceRequest, "councilTeamPlayersCount"));
			dto.setCouncilCompetitionVenue(ParamUtil.getString(resourceRequest, "councilCompetitionVenue"));
			dto.setCouncilCompetitionDuration(ParamUtil.getDate(resourceRequest, "councilCompetitionDuration",
					new SimpleDateFormat("yyyy-MM-dd")));
			dto.setCouncilEstimatedCost(ParamUtil.getDouble(resourceRequest, "councilEstimatedCost"));
			dto.setCouncilFederationConsent(ParamUtil.getString(resourceRequest, "councilFederationConsent"));
			dto.setCouncilRegistrationNumber(ParamUtil.getString(resourceRequest, "councilRegistrationNumber"));
			dto.setCouncilDirectorateApprovalNumber(
					ParamUtil.getString(resourceRequest, "councilDirectorateApprovalNumber"));

			// Upload File Attachments
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					resourceRequest);

			dto.setCouncilPlayerListAttachmentId(handleFileUpload(uploadRequest, "councilPlayerListAttachment",
					serviceContext, "Grants And Schemes"));
			dto.setCouncilEstimatedCostAttachmentId(handleFileUpload(uploadRequest, "councilEstimatedCostAttachment",
					serviceContext, "Grants And Schemes"));
			dto.setCouncilFederationConsentAttachmentId(handleFileUpload(uploadRequest,
					"councilFederationConsentAttachment", serviceContext, "Grants And Schemes"));
			dto.setCouncilDirectorateApprovalAttachmentId(handleFileUpload(uploadRequest,
					"councilDirectorateApprovalAttachment", serviceContext, "Grants And Schemes"));
			dto.setCouncilDistrictAffiliationAttachmentId(handleFileUpload(uploadRequest,
					"councilDistrictAffiliationAttachment", serviceContext, "Grants And Schemes"));
			dto.setCouncilCertificateAffiliationAttachmentId(handleFileUpload(uploadRequest,
					"councilCertificateAffiliationAttachment", serviceContext, "Grants And Schemes"));
			// organization
			dto.setCouncilRule3Approval(ParamUtil.getString(resourceRequest, "councilRule3Approval"));
			dto.setCouncilDirectorateApproval(ParamUtil.getString(resourceRequest, "councilDirectorateApproval"));
			dto.setCouncilPrescribedFormatDSO(ParamUtil.getString(resourceRequest, "councilPrescribedFormatDSO"));
			dto.setCouncilCaStatement(ParamUtil.getString(resourceRequest, "councilCaStatement"));
			dto.setCouncilOfficeBearersList(ParamUtil.getString(resourceRequest, "councilOfficeBearersList"));
			dto.setCouncilOrganizationCertificate(
					ParamUtil.getString(resourceRequest, "councilOrganizationCertificate"));
			dto.setCouncilSportsTrainingPrevious(ParamUtil.getString(resourceRequest, "councilSportsTrainingPrevious"));
			dto.setCouncilSportsTrainingDetails(ParamUtil.getString(resourceRequest, "councilSportsTrainingDetails"));
			dto.setCouncilSportsGrantFormDetails(ParamUtil.getString(resourceRequest, "councilSportsGrantFormDetails"));

			// Handle file uploads

			dto.setCouncilPrescribedFormatAttachmentId(handleFileUpload(uploadRequest,
					"councilPrescribedFormatAttachment", serviceContext, "Grants And Schemes"));
			dto.setCouncilCaStatementAttachmentId(handleFileUpload(uploadRequest, "councilCaStatementAttachment",
					serviceContext, "Grants And Schemes"));
			dto.setCouncilOfficeBearersAttachmentId(handleFileUpload(uploadRequest, "councilOfficeBearersAttachment",
					serviceContext, "Grants And Schemes"));
			dto.setCouncilOrganizationCertificateAttachmentId(handleFileUpload(uploadRequest,
					"councilOrganizationCertificateAttachment", serviceContext, "Grants And Schemes"));
			dto.setCouncilConcernLetterAttachmentId(handleFileUpload(uploadRequest, "councilConcernLetterAttachment",
					serviceContext, "Grants And Schemes"));
			dto.setCouncilCostEstimationAttachmentId(handleFileUpload(uploadRequest, "councilCostEstimationAttachment",
					serviceContext, "Grants And Schemes"));
			//post competition
			dto.setCouncilDetailedReportAttachmentId(handleFileUpload(uploadRequest, "councilDetailedReportAttachment",
					serviceContext, "Grants And Schemes"));

			dto.setCouncilAuditedStatementAttachmentId(handleFileUpload(uploadRequest,
					"councilAuditedStatementAttachment", serviceContext, "Grants And Schemes"));

			dto.setCouncilTeamPlayersListAttachmentId(handleFileUpload(uploadRequest,
					"councilTeamPlayersListAttachment", serviceContext, "Grants And Schemes"));

			dto.setCouncilSouvenirAttachmentId(handleFileUpload(uploadRequest, "councilSouvenirAttachment",
					serviceContext, "Grants And Schemes"));

			dto.setCouncilAffidavitAttachmentId(handleFileUpload(uploadRequest, "councilAffidavitAttachment",
					serviceContext, "Grants And Schemes"));

			// Basic Input Fields
			dto.setCouncilFundsRaised(ParamUtil.getDouble(resourceRequest, "councilFundsRaised"));
			dto.setCouncilActualLoss(ParamUtil.getDouble(resourceRequest, "councilActualLoss"));
			dto.setCouncilSouvenirIssued(ParamUtil.getString(resourceRequest, "councilSouvenirIssued"));

			// Hidden input storing multiple file names as string (comma-separated)
			List<Long> councilCompetitionPhotosIds = new ArrayList<>();

			// 1. Process existing competition photos (from hidden input)
			String[] existingPhotos = ParamUtil.getParameterValues(resourceRequest, "councilCompetitionPhotosHidden");

			if (existingPhotos != null && existingPhotos.length > 0) {
			    for (String fileIdStr : existingPhotos) {
			        try {
			            long fileEntryId = Long.parseLong(fileIdStr.trim());
			            DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			            councilCompetitionPhotosIds.add(fileEntry.getFileEntryId());
			        } catch (Exception e) {
			            LOGGER.error("Error processing existing competition photo ID: " + fileIdStr, e);
			        }
			    }
			}

			// 2. Process newly uploaded competition photos
			if (uploadRequest.getFile("councilCompetitionPhotos") != null) {
			    File[] uploadedPhotos = uploadRequest.getFiles("councilCompetitionPhotos");
			    String[] uploadedPhotoNames = ParamUtil.getParameterValues(resourceRequest, "councilCompetitionPhotosNames");

			    for (int i = 0; i < uploadedPhotos.length; i++) {
			        File file = uploadedPhotos[i];
			        String fileName = (uploadedPhotoNames != null && uploadedPhotoNames.length > i)
			                ? uploadedPhotoNames[i]
			                : "unknown";

			        try {
			            long fileEntryId = multipleFileUpload(uploadRequest, "councilCompetitionPhotos",
			                    "Grants And Schemes", serviceContext, fileName, file);
			            councilCompetitionPhotosIds.add(fileEntryId);
			        } catch (Exception e) {
			            LOGGER.error("Error uploading new competition photo: " + fileName, e);
			        }
			    }
			}

			// 3. Set to DTO
			dto.setCouncilCompetitionPhotosId(councilCompetitionPhotosIds.toString());

			// Declaration checkbox
			dto.setDeclarationAccepted(ParamUtil.getBoolean(resourceRequest, "isDeclarationAccepted"));

		} catch (Exception e) {
			LOGGER.error("Error setting CouncilSportCompetitionDetails DTO: " + e.getMessage(), e);
		}
		return dto;
	}

	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private UserLocalService userLocalService;
	@Reference
	private GroupLocalService groupLocalService;
	@Reference
	private RoleLocalService roleLocalService;

}
