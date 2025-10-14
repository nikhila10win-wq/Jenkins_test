package com.mhdsys.youth.award.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.common.pojo.AwardYouthOrgCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.AwardYouthLocalServiceUtil;
import com.mhdsys.schema.service.AwardYouthOrgLocalServiceUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = YouthAwardCommonUtil.class)

public class YouthAwardCommonUtil {
	private static Log LOGGER = LogFactoryUtil.getLog(YouthAwardCommonUtil.class);

	@Reference
	FileUploadUtil fileUploadUtil;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public AwardYouthCommonDTO setAwardYouthCommonDTO(ResourceRequest request, ThemeDisplay themeDisplay) {
		AwardYouthCommonDTO commonDTO = new AwardYouthCommonDTO();
		try {
			
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean  isDeskOfficer= RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean  isAssistantDirector= RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean  isHOAdmin= RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			//boolean  isDeputyDirector= RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			if(isDeskOfficer || isAssistantDirector || isHOAdmin ) {
				AwardYouth awardYouth=AwardYouthLocalServiceUtil.getAwardYouth(ParamUtil.getLong(request, "awardYouthId"));
				BeanPropertiesUtil.copyProperties(awardYouth, commonDTO);
				if(isDeskOfficer) {
					commonDTO.setDeskDecision(ParamUtil.getBoolean(request, "deskDecision"));
					commonDTO.setDeskRemarks(ParamUtil.getString(request, "deskRemarks"));
					commonDTO.setStatus(1);
				}else if(isAssistantDirector) {
					commonDTO.setAsstDirDecision(ParamUtil.getBoolean(request, "asstDirDecision"));
					commonDTO.setAsstDirRemarks(ParamUtil.getString(request, "asstDirRemarks"));
					commonDTO.setStatus(2);
				}else if(isHOAdmin) { 
					
					commonDTO.setDdHoDecision(ParamUtil.getBoolean(request, "ddHoDecision"));
					commonDTO.setDdHoRemarks(ParamUtil.getString(request, "ddHoRemarks"));
				}
			}
			else {
			
			commonDTO.setAwardYouthId(ParamUtil.getLong(request, "awardYouthId"));

			// Personal Details
			commonDTO.setFirstName(ParamUtil.getString(request, "firstName"));
			commonDTO.setMiddleName(ParamUtil.getString(request, "middleName"));
			commonDTO.setLastName(ParamUtil.getString(request, "lastName"));
			commonDTO.setGender(ParamUtil.getString(request, "gender"));
			commonDTO.setAddress(ParamUtil.getString(request, "address"));
			commonDTO.setContactNo(ParamUtil.getLong(request, "contactNo"));
			commonDTO.setEmailId(ParamUtil.getString(request, "emailId"));
			
			commonDTO.setDeskDecision(ParamUtil.getBoolean(request, "deskDecision"));
			commonDTO.setAsstDirDecision(ParamUtil.getBoolean(request, "asstDirDecision"));
			commonDTO.setDdHoDecision(ParamUtil.getBoolean(request, "ddHoDecision"));

			commonDTO.setDeskRemarks(ParamUtil.getString(request, "deskRemarks"));
			commonDTO.setAsstDirRemarks(ParamUtil.getString(request, "asstDirRemarks"));
			commonDTO.setDdHoRemarks(ParamUtil.getString(request, "ddHoRemarks"));
			

			commonDTO
			.setDob(convertStringToDateFormat(ParamUtil.getString(request, "dob")));
			
			LOGGER.info("commonDTO.getDob()::::::::::::::::"+commonDTO.getDob());
			

			// Education
			commonDTO.setEducationDetails(ParamUtil.getString(request, "educationDetails"));

			// Work Details
			commonDTO.setBusinessDetails(ParamUtil.getString(request, "businessDetails"));
			commonDTO.setWorkArea(ParamUtil.getString(request, "workArea"));
			commonDTO.setWorkDetailsPast3Years(ParamUtil.getString(request, "workDetailsPast3Years"));
//			commonDTO.setNewsPaperArticle(ParamUtil.getString(request, "newsPaperArticle"));

			// Social Responsibility
			commonDTO.setSocialResponsibility(ParamUtil.getLong(request, "socialResponsibility"));
			commonDTO.setAreaWorked(ParamUtil.getString(request, "areaWorked"));
			commonDTO.setAreaWorkedInfo(ParamUtil.getString(request, "areaWorkedInfo"));

			// Recognition
			commonDTO.setRecognizedByGovt(ParamUtil.getString(request, "recognizedByGovt"));
			commonDTO.setRecognizedWorkInfo(ParamUtil.getString(request, "recognizedWorkInfo"));

			// Staff
			LOGGER.info("ParamUtil.getString(request, staffOf)::::::::::" + ParamUtil.getString(request, "staffOf"));
			commonDTO.setStaffOf(ParamUtil.getString(request, "staffOf"));
			commonDTO.setStaffAffiliationInfo(ParamUtil.getString(request, "staffAffiliationInfo"));

			// Criminal Case
			LOGGER.info(
					"ParamUtil.getString(request, convicted)::::::::::" + ParamUtil.getString(request, "convicted"));
			commonDTO.setConvicted(ParamUtil.getString(request, "convicted"));
			commonDTO.setConvictedInfo(ParamUtil.getString(request, "convictedInfo"));

			// Future Plans
			commonDTO.setFuturePlans(ParamUtil.getString(request, "futurePlans"));
			commonDTO.setFormStatus(ParamUtil.getString(request, "formStatus"));
			commonDTO.setStatus(ParamUtil.getInteger(request, "status"));
			
			
			
			
			

			// Undertaking
			commonDTO.setUndertakingAccepted(ParamUtil.getBoolean(request, "undertakingAccepted", false));

			// Audit
			commonDTO.setUserId(themeDisplay.getUserId());
			commonDTO.setCreateDate(new Date());
			commonDTO.setModifiedDate(new Date());

			// File Upload (newsPaperArticle)
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
           
			
			// Multiple File upload 
			
			List<Long> newsPaperArticleIds = new ArrayList<>();
			
			String[] existingNewsPaperArticle = ParamUtil.getParameterValues(request, "existingNewsPaperArticle");
			String[] existingNewsPaperArticleIds = ParamUtil.getParameterValues(request,
					"existingNewsPaperArticleIds");
			
			if (existingNewsPaperArticle != null && existingNewsPaperArticle.length > 0) {
				if (existingNewsPaperArticle.length == existingNewsPaperArticleIds.length) {

					for (int i = 0; i < existingNewsPaperArticle.length; i++) {
						String fileIdStr = existingNewsPaperArticleIds[i];
						LOGGER.info("Existing File - Name: " + ", ID: " + fileIdStr);

						try {
							long fileEntryId = Long.parseLong(fileIdStr);
							DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

							newsPaperArticleIds.add(fileEntry.getFileEntryId());

						} catch (Exception e) {
							LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
						}
					}

					Method setNewsPaperArticle = commonDTO.getClass().getMethod("setNewsPaperArticle",
							String.class);
					setNewsPaperArticle.invoke(commonDTO, newsPaperArticleIds.toString());
				}
			}

			// Check if files exist (safer than checking getFile() which might throw NPE)
			if (uploadPortletRequest.getFile("newsPaperArticle") != null) {
				File[] newsPaperArticleFiles = uploadPortletRequest.getFiles("newsPaperArticle");
				String[] newsPaperArticleNames = ParamUtil.getParameterValues(request, "newsPaperArticleNames");
				LOGGER.info("Files :" + newsPaperArticleFiles);
				LOGGER.info("Files Name :" + newsPaperArticleNames);

				for (int i = 0; i < newsPaperArticleFiles.length; i++) {
					File file = newsPaperArticleFiles[i];
					LOGGER.info("=============================File :" + file);
					String fileName = newsPaperArticleNames.length > i ? newsPaperArticleNames[i] : "unknown";
					LOGGER.info("Files Name :=============================================================" + fileName);
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "newsPaperArticle",
							CommonUtilityConstant.YOUTH_FOLDER, serviceContext, fileName, file);
					newsPaperArticleIds.add(fileEntryId);
				}

				LOGGER.info("newsPaperArticleIds: " + newsPaperArticleIds.toString());
				Method setNewsPaperArticle = commonDTO.getClass().getMethod("setNewsPaperArticle", String.class);
				setNewsPaperArticle.invoke(commonDTO, newsPaperArticleIds.toString());
			}
			
			
			// Single File upload 

			long birthCertificateFileEntryId = ParamUtil.getLong(request, "birthCertificateFileEntryId");
			LOGGER.info("birthCertificateFileEntryId:::::::::::::"+ birthCertificateFileEntryId);
			if (birthCertificateFileEntryId > 0) {
				
				commonDTO.setBirthCertificate(birthCertificateFileEntryId);

			} else {
				if (uploadPortletRequest != null && uploadPortletRequest.getFile("birthCertificateUpload") != null) {

					long birthCertificate = fileUploadUtil.uploadFile(uploadPortletRequest, "birthCertificateUpload",
							CommonUtilityConstant.YOUTH_FOLDER, serviceContext);
					LOGGER.info("birthCertificateUpload file: " + birthCertificate);
					commonDTO.setBirthCertificate(birthCertificate);
				}
			}
				
				long socialResponsibilityEntryId = ParamUtil.getLong(request, "socialResponsibilityEntryId");
				if (socialResponsibilityEntryId > 0) {
					commonDTO.setSocialResponsibility(socialResponsibilityEntryId);

				}else {
				if (uploadPortletRequest != null
						&& uploadPortletRequest.getFile("socialResponsibilityUpload") != null) {

					long socialResponsibility = fileUploadUtil.uploadFile(uploadPortletRequest,
							"socialResponsibilityUpload", CommonUtilityConstant.YOUTH_FOLDER, serviceContext);
					LOGGER.info("socialResponsibilityUpload file: " + socialResponsibility);
					commonDTO.setSocialResponsibility(socialResponsibility);
				}
				}
			
			}
		} catch (Exception e) {
			LOGGER.error("Error in setAwardYouthCommonDTO: " + e.getMessage(), e);
		}
		return commonDTO;
	}

	public AwardYouthCommonDTO setAwardYouthCommonDTO(AwardYouth awardYouth, ThemeDisplay themeDisplay) {
		AwardYouthCommonDTO commonDTO = new AwardYouthCommonDTO();
		try {
			BeanPropertiesUtil.copyProperties(awardYouth, commonDTO);

			if (Validator.isNotNull(awardYouth.getDob())) {
				
				commonDTO.setDobStr(
						(Validator.isNotNull(awardYouth.getDob())) ? formatter.format(awardYouth.getDob()) : "");
				LOGGER.info("Date Of Birth    :::::   "+commonDTO.getDobStr());
			}

			
			
			if (Validator.isNotNull(awardYouth.getNewsPaperArticle())) {
				String newsPaperArticleStr = awardYouth.getNewsPaperArticle();
				newsPaperArticleStr = newsPaperArticleStr.replaceAll("[\\[\\] ]", "");
				String[] newsPaperArticleIds = newsPaperArticleStr.split(",");

				List<String> newsPaperArticleURLs = new ArrayList<>();
				List<String> newsPaperArticleNames = new ArrayList<>();
				List<String> newsPaperArticleEntryIds = new ArrayList<>(); // List for file entry IDs as Strings

				for (String newsPaperArticleIdStr : newsPaperArticleIds) {
					try {
						long newsPaperArticleId = Long.parseLong(newsPaperArticleIdStr.trim());
						LOGGER.info("actualPhoto Id ::: " + newsPaperArticleId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(newsPaperArticleId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							newsPaperArticleNames.add(fileName);
							newsPaperArticleURLs.add(fileUploadUtil.getPreviewURL(newsPaperArticleId, themeDisplay));
							newsPaperArticleEntryIds.add(String.valueOf(newsPaperArticleId)); // Add ID as String
							LOGGER.info("URL : " + newsPaperArticleURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing Certificate ID: " + newsPaperArticleIdStr, e);
					}
				}
				commonDTO.setNewsPaperArticleURLs(newsPaperArticleURLs);
				commonDTO.setNewsPaperArticleNames(newsPaperArticleNames);
				commonDTO.setNewsPaperArticleEntryIds(newsPaperArticleEntryIds); // Set the IDs in DTO
			}

			if (commonDTO.getBirthCertificate() > 0) {
				commonDTO.setBirthCertificate(awardYouth.getBirthCertificate());
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(commonDTO.getBirthCertificate());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						commonDTO.setBirthCertificateName(trimmedFilename);
					} else {
						commonDTO.setBirthCertificateName(fileName);
					}
					commonDTO.setBirthCertificateURL(
							fileUploadUtil.getPreviewURL(commonDTO.getBirthCertificate(), themeDisplay));
				}
			}

			if (commonDTO.getSocialResponsibility() > 0) {
				commonDTO.setSocialResponsibility(awardYouth.getSocialResponsibility());
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(commonDTO.getSocialResponsibility());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						commonDTO.setSocialResponsibilityName(trimmedFilename);
					} else {
						commonDTO.setSocialResponsibilityName(fileName);
					}
					commonDTO.setSocialResponsibilityURL(
							fileUploadUtil.getPreviewURL(commonDTO.getBirthCertificate(), themeDisplay));
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error in setAwardYouthCommonDTO from entity: " + e.getMessage(), e);
		}
		return commonDTO;
	}

	public AwardYouthOrgCommonDTO setAwardYouthOrgCommonDTO(ResourceRequest request, ThemeDisplay themeDisplay) {
		AwardYouthOrgCommonDTO commonDTO = new AwardYouthOrgCommonDTO();

		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean  isDeskOfficer= RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean  isAssistantDirector= RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
		    boolean  isHOAdmin= RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			//boolean  isDeputyDirector= RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			if(isDeskOfficer || isAssistantDirector  || isHOAdmin) {
				AwardYouthOrg awardYouthOrg=AwardYouthOrgLocalServiceUtil.getAwardYouthOrg(ParamUtil.getLong(request, "awardYouthOrgId"));
				BeanPropertiesUtil.copyProperties(awardYouthOrg, commonDTO);
				if(isDeskOfficer) {
					commonDTO.setDeskDecision(ParamUtil.getBoolean(request, "deskDecision"));
					commonDTO.setDeskRemarks(ParamUtil.getString(request, "deskRemarks"));
					commonDTO.setStatus(1);
				}else if(isAssistantDirector) {
					commonDTO.setAsstDirDecision(ParamUtil.getBoolean(request, "asstDirDecision"));
					commonDTO.setAsstDirRemarks(ParamUtil.getString(request, "asstDirRemarks"));
					commonDTO.setStatus(2);
				}else if(isHOAdmin) {
					commonDTO.setDdHoDecision(ParamUtil.getBoolean(request, "ddHoDecision"));
					commonDTO.setDdHoRemarks(ParamUtil.getString(request, "ddHoRemarks"));
				}
			}
			else {
			
			
			commonDTO.setAwardYouthOrgId(ParamUtil.getLong(request, "awardYouthOrgId"));

			// Personal Details
			commonDTO.setFirstName(ParamUtil.getString(request, "firstName"));
			commonDTO.setMiddleName(ParamUtil.getString(request, "middleName"));
			commonDTO.setLastName(ParamUtil.getString(request, "lastName"));
			commonDTO.setGender(ParamUtil.getString(request, "gender"));
			commonDTO.setAddress(ParamUtil.getString(request, "address"));
			commonDTO.setContactNo(ParamUtil.getLong(request, "contactNo"));
			commonDTO.setEmailId(ParamUtil.getString(request, "emailId"));
			commonDTO.setDeskDecision(ParamUtil.getBoolean(request, "deskDecision"));
			commonDTO.setAsstDirDecision(ParamUtil.getBoolean(request, "asstDirDecision"));
			commonDTO.setDdHoDecision(ParamUtil.getBoolean(request, "ddHoDecision"));

			commonDTO.setDeskRemarks(ParamUtil.getString(request, "deskRemarks"));
			commonDTO.setAsstDirRemarks(ParamUtil.getString(request, "asstDirRemarks"));
			commonDTO.setDdHoRemarks(ParamUtil.getString(request, "ddHoRemarks"));


			// Organisation Details
//			commonDTO.setAttestedCopy(ParamUtil.getLong(request, "attestedCopy"));
//			commonDTO.setConstitution(ParamUtil.getLong(request, "constitution"));
//			commonDTO.setOfficersList(ParamUtil.getLong(request, "officersList"));

			// Work Details
			commonDTO.setWorkArea(ParamUtil.getString(request, "workArea"));
			commonDTO.setWorkDetailsPast3Years(ParamUtil.getString(request, "workDetailsPast3Years"));

			commonDTO.setGovtFinancialAssistance(ParamUtil.getString(request, "govtFinancialAssistance"));
			commonDTO.setAreaWorked(ParamUtil.getString(request, "areaWorked"));
			commonDTO.setAreaWorkedInfo(ParamUtil.getString(request, "areaWorkedInfo"));
			commonDTO.setRecognizedByGovt(ParamUtil.getString(request, "recognizedByGovt"));
			commonDTO.setRecognizedWorkInfo(ParamUtil.getString(request, "recognizedWorkInfo"));
			commonDTO.setConvicted(ParamUtil.getString(request, "convicted"));
			commonDTO.setConvictedInfo(ParamUtil.getString(request, "convictedInfo"));
			commonDTO.setFuturePlans(ParamUtil.getString(request, "futurePlans"));
			commonDTO.setFormStatus(ParamUtil.getString(request, "formStatus"));
			commonDTO.setStatus(ParamUtil.getInteger(request, "status"));

			// Undertaking
			commonDTO.setUndertakingAccepted(ParamUtil.getBoolean(request, "undertakingAccepted", false));

			// Audit
			commonDTO.setUserId(themeDisplay.getUserId());
			commonDTO.setCreateDate(new Date());
			commonDTO.setModifiedDate(new Date());

			// File Upload (newsPaperArticle)
			// File Upload (newsPaperArticle)
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);

			List<Long> newsPaperArticleIds = new ArrayList<>();
			
			
			String[] existingNewsPaperArticle = ParamUtil.getParameterValues(request, "existingNewsPaperArticle");
			String[] existingNewsPaperArticleIds = ParamUtil.getParameterValues(request,
					"existingNewsPaperArticleIds");
			
			if (existingNewsPaperArticle != null && existingNewsPaperArticle.length > 0) {
				if (existingNewsPaperArticle.length == existingNewsPaperArticleIds.length) {

					for (int i = 0; i < existingNewsPaperArticle.length; i++) {
						String fileIdStr = existingNewsPaperArticleIds[i];
						LOGGER.info("Existing File - Name: " + ", ID: " + fileIdStr);

						try {
							long fileEntryId = Long.parseLong(fileIdStr);
							DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

							newsPaperArticleIds.add(fileEntry.getFileEntryId());

						} catch (Exception e) {
							LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
						}
					}

					Method setNewsPaperArticle = commonDTO.getClass().getMethod("setNewsPaperArticle",
							String.class);
					setNewsPaperArticle.invoke(commonDTO, newsPaperArticleIds.toString());
				}
			}


			// Check if files exist (safer than checking getFile() which might throw NPE)
			if (uploadPortletRequest.getFile("newsPaperArticle") != null) {
				File[] newsPaperArticleFiles = uploadPortletRequest.getFiles("newsPaperArticle");
				String[] newsPaperArticleNames = ParamUtil.getParameterValues(request, "newsPaperArticleNames");
				LOGGER.info("Files :" + newsPaperArticleFiles);
				LOGGER.info("Files Name :" + newsPaperArticleNames);

				for (int i = 0; i < newsPaperArticleFiles.length; i++) {
					File file = newsPaperArticleFiles[i];
					LOGGER.info("=============================File :" + file);
					String fileName = newsPaperArticleNames.length > i ? newsPaperArticleNames[i] : "unknown";
					LOGGER.info("Files Name :=============================================================" + fileName);
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "newsPaperArticle",
							CommonUtilityConstant.YOUTH_FOLDER, serviceContext, fileName, file);
					newsPaperArticleIds.add(fileEntryId);
				}

				LOGGER.info("newsPaperArticleIds: " + newsPaperArticleIds.toString());
				Method setNewsPaperArticle = commonDTO.getClass().getMethod("setNewsPaperArticle", String.class);
				setNewsPaperArticle.invoke(commonDTO, newsPaperArticleIds.toString());
			}

			long attestedCopyFileEntryId = ParamUtil.getLong(request, "attestedCopyFileEntryId");
			if (attestedCopyFileEntryId > 0) {
				commonDTO.setAttestedCopy(attestedCopyFileEntryId);

			}else {
			if (uploadPortletRequest != null && uploadPortletRequest.getFile("attestedCopyUpload") != null) {

				long attestedcopy = fileUploadUtil.uploadFile(uploadPortletRequest, "attestedCopyUpload",
						CommonUtilityConstant.YOUTH_FOLDER, serviceContext);
				LOGGER.info("AttestedcopyUpload file: " + attestedcopy);
				commonDTO.setAttestedCopy(attestedcopy);
			}
			}

			long constitutionEntryId = ParamUtil.getLong(request, "constitutionEntryId");
			if (constitutionEntryId > 0) {
				commonDTO.setConstitution(constitutionEntryId);

			}else {
			if (uploadPortletRequest != null && uploadPortletRequest.getFile("constitutionUpload") != null) {

				long constitution = fileUploadUtil.uploadFile(uploadPortletRequest, "constitutionUpload",
						CommonUtilityConstant.YOUTH_FOLDER, serviceContext);
				LOGGER.info("ConstitutionUpload file: " + constitution);
				commonDTO.setConstitution(constitution);
			}
			}
			
			long officersListEntryId = ParamUtil.getLong(request, "officersListEntryId");
			if (officersListEntryId > 0) {
				
				commonDTO.setOfficersList(officersListEntryId);

			}else {
			if (uploadPortletRequest != null && uploadPortletRequest.getFile("officersListUpload") != null) {

				long officersList = fileUploadUtil.uploadFile(uploadPortletRequest, "officersListUpload",
						CommonUtilityConstant.YOUTH_FOLDER, serviceContext);
				LOGGER.info("OfficersListUpload file: " + officersList);
				commonDTO.setOfficersList(officersList);
			}
			}
			}

		} catch (Exception e) {
			LOGGER.error("Error in setAwardYouthOrgCommonDTO: " + e.getMessage(), e);
		}

		return commonDTO;
	}

	public AwardYouthOrgCommonDTO setAwardYouthOrgCommonDTO(AwardYouthOrg awardYouthOrg, ThemeDisplay themeDisplay) {
		AwardYouthOrgCommonDTO commonDTO = new AwardYouthOrgCommonDTO();
		try {
			BeanPropertiesUtil.copyProperties(awardYouthOrg, commonDTO);

			if (Validator.isNotNull(awardYouthOrg.getNewsPaperArticle())) {
				String newsPaperArticleStr = awardYouthOrg.getNewsPaperArticle();
				newsPaperArticleStr = newsPaperArticleStr.replaceAll("[\\[\\] ]", "");
				String[] newsPaperArticleIds = newsPaperArticleStr.split(",");

				List<String> newsPaperArticleURLs = new ArrayList<>();
				List<String> newsPaperArticleNames = new ArrayList<>();
				List<String> newsPaperArticleEntryIds = new ArrayList<>(); // List for file entry IDs as Strings

				for (String newsPaperArticleIdStr : newsPaperArticleIds) {
					try {
						long newsPaperArticleId = Long.parseLong(newsPaperArticleIdStr.trim());
						LOGGER.info("actualPhoto Id ::: " + newsPaperArticleId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(newsPaperArticleId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							newsPaperArticleNames.add(fileName);
							newsPaperArticleURLs.add(fileUploadUtil.getPreviewURL(newsPaperArticleId, themeDisplay));
							newsPaperArticleEntryIds.add(String.valueOf(newsPaperArticleId)); // Add ID as String
							LOGGER.info("URL : " + newsPaperArticleURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing Certificate ID: " + newsPaperArticleIdStr, e);
					}
				}
				commonDTO.setNewsPaperArticleURLs(newsPaperArticleURLs);
				commonDTO.setNewsPaperArticleNames(newsPaperArticleNames);
				commonDTO.setNewsPaperArticleEntryIds(newsPaperArticleEntryIds); // Set the IDs in DTO
			}

			// Attested Copy
			if (commonDTO.getAttestedCopy() > 0) {
				DLFileEntry attestedFile = DLFileEntryLocalServiceUtil.getFileEntry(commonDTO.getAttestedCopy());
				if (Validator.isNotNull(attestedFile)) {
					String fileName = attestedFile.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						commonDTO.setAttestedCopyName(trimmedFilename);
					} else {
						commonDTO.setAttestedCopyName(fileName);
					}
					commonDTO.setAttestedCopyURL(
							fileUploadUtil.getPreviewURL(commonDTO.getAttestedCopy(), themeDisplay));
				}
			}

			// Constitution
			if (commonDTO.getConstitution() > 0) {
				DLFileEntry constitutionFile = DLFileEntryLocalServiceUtil.getFileEntry(commonDTO.getConstitution());
				if (Validator.isNotNull(constitutionFile)) {
					String fileName = constitutionFile.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						commonDTO.setConstitutionName(trimmedFilename);
					} else {
						commonDTO.setConstitutionName(fileName);
					}
					commonDTO.setConstitutionURL(
							fileUploadUtil.getPreviewURL(commonDTO.getConstitution(), themeDisplay));
				}
			}

			// Officers List
			if (commonDTO.getOfficersList() > 0) {
				DLFileEntry officersFile = DLFileEntryLocalServiceUtil.getFileEntry(commonDTO.getOfficersList());
				if (Validator.isNotNull(officersFile)) {
					String fileName = officersFile.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						commonDTO.setOfficersListName(trimmedFilename);
					} else {
						commonDTO.setOfficersListName(fileName);
					}
					commonDTO.setOfficersListURL(
							fileUploadUtil.getPreviewURL(commonDTO.getOfficersList(), themeDisplay));
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error in setAwardYouthOrgCommonDTO from entity: " + e.getMessage(), e);
		}
		return commonDTO;
	}

	// Date util

		public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

		public static Date convertStringToDateFormat(String date) {
			try {
				if (Validator.isNotNull(date)) {
					LOGGER.debug("Date string :: " + date);
					return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).parse(date);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			return null;
		}
		
		
	

}
