package com.mhdsys.establishment.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.EstablishmentCommonDTO;
import com.mhdsys.common.pojo.GPFDetailsCommonDTO;
import com.mhdsys.common.pojo.NPSDetailsCommonDTO;
import com.mhdsys.common.pojo.PersonalDetailsCommonDTO;
import com.mhdsys.common.pojo.PostingStatusCommonDTO;
import com.mhdsys.common.pojo.RoasterStatusCommonDTO;
import com.mhdsys.common.pojo.ServiceDetailsCommonDTO;
import com.mhdsys.common.pojo.TrainingDetailsCommonDTO;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.schema.model.EstablishmentDetails;
import com.mhdsys.schema.model.GPFDetails;
import com.mhdsys.schema.model.NPSDetails;
import com.mhdsys.schema.model.PersonalDetails;
import com.mhdsys.schema.model.ServiceDetails;
import com.mhdsys.schema.model.TrainingDetails;
import com.mhdsys.schema.service.EstablishmentDetailsLocalServiceUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = EstablishmentUtil.class)

public class EstablishmentUtil {
	private static Log LOGGER = LogFactoryUtil.getLog(EstablishmentUtil.class);
	@Reference
	DateConversionUtil dateConversionUtil;

	@Reference
	FileUploadUtil fileUploadUtil;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public PersonalDetailsCommonDTO setPersonalDetailsDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long personalDetailsId, long establishementId) {

		PersonalDetailsCommonDTO personalDetails = new PersonalDetailsCommonDTO();

		try {
			personalDetails.setPersonalDetailsId(personalDetailsId);
			personalDetails.setEstablishmentDetailId(establishementId);
			personalDetails.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
			personalDetails.setEmployeeName(ParamUtil.getString(resourceRequest, "employeeName"));
			personalDetails.setCategory(ParamUtil.getString(resourceRequest, "category"));
			personalDetails.setSevarthID(ParamUtil.getString(resourceRequest, "sevarthID"));

			String dobStr = ParamUtil.getString(resourceRequest, "dob");
			personalDetails.setDob(dateConversionUtil.convertStringToDateFormat(dobStr));

			personalDetails.setHeight(ParamUtil.getDouble(resourceRequest, "height"));
			personalDetails.setBodyMark(ParamUtil.getString(resourceRequest, "bodyMark"));
			personalDetails.setEducation(ParamUtil.getString(resourceRequest, "education"));
			personalDetails.setContactDetails(ParamUtil.getLong(resourceRequest, "contactDetails"));
			personalDetails.setMscIT(ParamUtil.getString(resourceRequest, "mscIT"));
			personalDetails.setPostalAddress(ParamUtil.getString(resourceRequest, "postalAddress"));
			personalDetails.setHometown(ParamUtil.getString(resourceRequest, "hometown"));
			personalDetails.setPermanentAddress(ParamUtil.getString(resourceRequest, "permanentAddress"));
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long castCertificate = fileUploadUtil.uploadFile(uploadPortletRequest, "castCertificate",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			personalDetails.setCastCertificate(castCertificate);

			long castValidity = fileUploadUtil.uploadFile(uploadPortletRequest, "castValidity",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			personalDetails.setCastValidity(castValidity);

			long specialAchievements = fileUploadUtil.uploadFile(uploadPortletRequest, "specialAchievements",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			personalDetails.setSpecialAchievements(specialAchievements);

			long sportsAchievements = fileUploadUtil.uploadFile(uploadPortletRequest, "sportsAchievements",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			personalDetails.setSportsAchievements(sportsAchievements);
			personalDetails.setUserId(themeDisplay.getUserId());

			// Optional logging
			LOGGER.info("PersonalDetails object set: " + personalDetails);

		} catch (Exception e) {
			LOGGER.error("Error setting PersonalDetails: " + e.getMessage(), e);
		}

		return personalDetails;
	}

	public ServiceDetailsCommonDTO setServiceDetailsDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long serviceDetailsId, long establishementId) {

		ServiceDetailsCommonDTO serviceDetails = new ServiceDetailsCommonDTO();

		try {
			serviceDetails.setServiceDetailsId(serviceDetailsId);
			serviceDetails.setEstablishmentDetailId(establishementId);
			serviceDetails.setNominations(ParamUtil.getString(resourceRequest, "nominations"));
			serviceDetails.setPromotionExam(ParamUtil.getString(resourceRequest, "promotionExam"));
			serviceDetails.setUserId(themeDisplay.getUserId());

			String appointmentDateStr = ParamUtil.getString(resourceRequest, "dateOfAppointment");
			if (Validator.isNotNull(appointmentDateStr)) {
				serviceDetails.setDateOfAppointment(dateConversionUtil.convertStringToDateFormat(appointmentDateStr));
			}

			String retirementDateStr = ParamUtil.getString(resourceRequest, "dateOfRetirement");
			if (Validator.isNotNull(retirementDateStr)) {
				serviceDetails.setDateOfRetirement(dateConversionUtil.convertStringToDateFormat(retirementDateStr));
			}

			serviceDetails.setProbationPeriod(ParamUtil.getString(resourceRequest, "probationPeriod"));
			serviceDetails
					.setEntryLevelDepartmentExam(ParamUtil.getString(resourceRequest, "entryLevelDepartmentExam"));
			serviceDetails.setRegularPromotionDetails(ParamUtil.getString(resourceRequest, "regularPromotionDetails"));
			serviceDetails.setPromotionTimeScale(ParamUtil.getString(resourceRequest, "promotionTimeScale"));
			serviceDetails.setRegularPayFixation(ParamUtil.getString(resourceRequest, "regularPayFixation"));
			serviceDetails.setRetirementType(ParamUtil.getString(resourceRequest, "retirementType"));
			String medicalCertificateDateStr = ParamUtil.getString(resourceRequest, "medicalCertificateDate");
			if (Validator.isNotNull(medicalCertificateDateStr)) {
				serviceDetails.setMedicalCertificateDate(
						dateConversionUtil.convertStringToDateFormat(medicalCertificateDateStr));
			}
			String policeVerificationDateStr = ParamUtil.getString(resourceRequest, "policeVerificationDate");
			if (Validator.isNotNull(policeVerificationDateStr)) {
				serviceDetails.setPoliceVerificationDate(
						dateConversionUtil.convertStringToDateFormat(policeVerificationDateStr));
			}
			// Suspension Details
			String suspensionDateStr = ParamUtil.getString(resourceRequest, "dateOfSuspension");
			if (Validator.isNotNull(suspensionDateStr)) {
				serviceDetails.setDateOfSuspension(dateConversionUtil.convertStringToDateFormat(suspensionDateStr));
			}
			serviceDetails.setReasonOfSuspension(ParamUtil.getString(resourceRequest, "reasonOfSuspension"));

			String reinstateDateStr = ParamUtil.getString(resourceRequest, "dateOfReinstate");
			if (Validator.isNotNull(reinstateDateStr)) {
				serviceDetails.setDateOfReinstate(dateConversionUtil.convertStringToDateFormat(reinstateDateStr));
			}

			serviceDetails.setPunishment(ParamUtil.getString(resourceRequest, "punishment"));

			// Seniority
			serviceDetails.setSeniorityList(ParamUtil.getLong(resourceRequest, "seniorityList"));

			// Leave Details
			serviceDetails.setLeaveType(ParamUtil.getString(resourceRequest, "leaveType"));

			String leaveFromDateStr = ParamUtil.getString(resourceRequest, "leaveFromDate");
			if (Validator.isNotNull(leaveFromDateStr)) {
				serviceDetails.setLeaveFromDate(dateConversionUtil.convertStringToDateFormat(leaveFromDateStr));
			}

			String leaveToDateStr = ParamUtil.getString(resourceRequest, "leaveToDate");
			if (Validator.isNotNull(leaveToDateStr)) {
				serviceDetails.setLeaveToDate(dateConversionUtil.convertStringToDateFormat(leaveToDateStr));
			}
			
		//	serviceDetails.setMattaAndDayitwaReceived(ParamUtil.getString(resourceRequest, "mattaAndDayitwaReceived"));

			// File Uploads
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);

			long departmentalEnquiryReport = fileUploadUtil.uploadFile(uploadPortletRequest,
					"departmentalEnquiryReport", CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			serviceDetails.setDepartmentalEnquiryReport(departmentalEnquiryReport);

			long leaveDocument = fileUploadUtil.uploadFile(uploadPortletRequest, "leaveDocument",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			serviceDetails.setLeaveDocument(leaveDocument);

			long medicalCertificate = fileUploadUtil.uploadFile(uploadPortletRequest, "medicalCertificate",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			serviceDetails.setMedicalCertificate(medicalCertificate);

			long policeVerification = fileUploadUtil.uploadFile(uploadPortletRequest, "policeVerification",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			serviceDetails.setPoliceVerification(policeVerification);
			
			long seniorityList = fileUploadUtil.uploadFile(uploadPortletRequest, "seniorityList",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			serviceDetails.setSeniorityList(seniorityList);


			List<Long> permanencyCertificateFileIds = new ArrayList<>();
			List<Long> noEnquiryCertificateFileIds = new ArrayList<>();
			List<Long> noDuesCertificateFileIds = new ArrayList<>();
			List<Long> complaintDetailsFileIds = new ArrayList<>();
			List<Long> confidentialReportsFileIds = new ArrayList<>();
			List<Long> awardCertificationOfHonorFileIds = new ArrayList<>();
			List<Long> languageExamCertificateFileIds = new ArrayList<>();
			List<Long> treasuryVerificationFileIds = new ArrayList<>();
			
			// Process existing files (kept in the UI)
			String[] existingTreasuryVerification = ParamUtil.getParameterValues(resourceRequest, "existingTreasuryVerification");
			String[] existingTreasuryVerificationIds = ParamUtil.getParameterValues(resourceRequest, "existingTreasuryVerificationIds");

			if (existingTreasuryVerification != null && existingTreasuryVerification.length > 0) {
			    if (existingTreasuryVerification.length == existingTreasuryVerificationIds.length) {
			        
			        for (int i = 0; i < existingTreasuryVerification.length; i++) {
			            String fileIdStr = existingTreasuryVerificationIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                treasuryVerificationFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setTreasuryVerification = serviceDetails.getClass().getMethod("setTreasuryVerification",
							String.class);
			        setTreasuryVerification.invoke(serviceDetails, treasuryVerificationFileIds.toString());
			    }
			}

			if (uploadPortletRequest.getFile("treasuryVerification") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("treasuryVerification");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"treasuryVerificationNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "treasuryVerification",
							"Establishment", serviceContext, fileName, file);
					treasuryVerificationFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + treasuryVerificationFileIds.toString());
				Method setTreasuryVerification = serviceDetails.getClass().getMethod("setTreasuryVerification",
						String.class);
				setTreasuryVerification.invoke(serviceDetails, treasuryVerificationFileIds.toString());
			}
			
			// Process existing files (kept in the UI)
			String[] existingPermanencyCertificate = ParamUtil.getParameterValues(resourceRequest, "existingPermanencyCertificate");
			String[] existingPermanencyCertificateIds = ParamUtil.getParameterValues(resourceRequest, "existingPermanencyCertificateIds");

			if (existingPermanencyCertificate != null && existingPermanencyCertificate.length > 0) {
			    if (existingPermanencyCertificate.length == existingPermanencyCertificateIds.length) {
			        
			        for (int i = 0; i < existingPermanencyCertificate.length; i++) {
			            String fileIdStr = existingPermanencyCertificateIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                permanencyCertificateFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setPermanencyCertificate = serviceDetails.getClass().getMethod("setPermanencyCertificate",
							String.class);
			        setPermanencyCertificate.invoke(serviceDetails, permanencyCertificateFileIds.toString());
			    }
			}
			
			if (uploadPortletRequest.getFile("permanencyCertificate") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("permanencyCertificate");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"permanencyCertificateNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "permanencyCertificate",
							"Establishment", serviceContext, fileName, file);
					permanencyCertificateFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + permanencyCertificateFileIds.toString());
				Method setPermanencyCertificate = serviceDetails.getClass().getMethod("setPermanencyCertificate",
						String.class);
				setPermanencyCertificate.invoke(serviceDetails, permanencyCertificateFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingLanguageExamCertificate = ParamUtil.getParameterValues(resourceRequest, "existingLanguageExamCertificate");
			String[] existingLanguageExamCertificateIds = ParamUtil.getParameterValues(resourceRequest, "existingLanguageExamCertificateIds");

			if (existingLanguageExamCertificate != null && existingLanguageExamCertificate.length > 0) {
			    if (existingLanguageExamCertificate.length == existingLanguageExamCertificateIds.length) {
			        
			        for (int i = 0; i < existingLanguageExamCertificate.length; i++) {
			            String fileIdStr = existingLanguageExamCertificateIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                languageExamCertificateFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setLanguageExamCertificate = serviceDetails.getClass().getMethod("setLanguageExamCertificate",
							String.class);
			        setLanguageExamCertificate.invoke(serviceDetails, languageExamCertificateFileIds.toString());
			    }
			}
			

			if (uploadPortletRequest.getFile("languageExamCertificate") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("languageExamCertificate");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"languageExamCertificateNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest,
							"languageExamCertificate", "Establishment", serviceContext, fileName, file);
					languageExamCertificateFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + languageExamCertificateFileIds.toString());
				Method setLanguageExamCertificate = serviceDetails.getClass().getMethod("setLanguageExamCertificate",
						String.class);
				setLanguageExamCertificate.invoke(serviceDetails, languageExamCertificateFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingAwardCertificationOfHonor = ParamUtil.getParameterValues(resourceRequest, "existingAwardCertificationOfHonor");
			String[] existingAwardCertificationOfHonorIds = ParamUtil.getParameterValues(resourceRequest, "existingAwardCertificationOfHonorIds");

			if (existingAwardCertificationOfHonor != null && existingAwardCertificationOfHonor.length > 0) {
			    if (existingAwardCertificationOfHonor.length == existingAwardCertificationOfHonorIds.length) {
			        
			        for (int i = 0; i < existingAwardCertificationOfHonor.length; i++) {
			            String fileIdStr = existingAwardCertificationOfHonorIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                awardCertificationOfHonorFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setAwardCertificationOfHonor = serviceDetails.getClass().getMethod("setAwardCertificationOfHonor",
							String.class);
			        setAwardCertificationOfHonor.invoke(serviceDetails, awardCertificationOfHonorFileIds.toString());
			    }
			}
			

			if (uploadPortletRequest.getFile("awardCertificationOfHonor") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("awardCertificationOfHonor");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"awardCertificationOfHonorNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest,
							"awardCertificationOfHonor", "Establishment", serviceContext, fileName, file);
					awardCertificationOfHonorFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + awardCertificationOfHonorFileIds.toString());
				Method setAwardCertificationOfHonor = serviceDetails.getClass()
						.getMethod("setAwardCertificationOfHonor", String.class);
				setAwardCertificationOfHonor.invoke(serviceDetails, awardCertificationOfHonorFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingConfidentialReports = ParamUtil.getParameterValues(resourceRequest, "existingConfidentialReports");
			String[] existingConfidentialReportsIds = ParamUtil.getParameterValues(resourceRequest, "existingConfidentialReportsIds");

			if (existingConfidentialReports != null && existingConfidentialReports.length > 0) {
			    if (existingConfidentialReports.length == existingConfidentialReportsIds.length) {
			        
			        for (int i = 0; i < existingConfidentialReports.length; i++) {
			            String fileIdStr = existingConfidentialReportsIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                confidentialReportsFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setConfidentialReports = serviceDetails.getClass().getMethod("setConfidentialReports",
							String.class);
			        setConfidentialReports.invoke(serviceDetails, confidentialReportsFileIds.toString());
			    }
			}

			if (uploadPortletRequest.getFile("confidentialReports") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("confidentialReports");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"confidentialReportsNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "confidentialReports",
							"Establishment", serviceContext, fileName, file);
					confidentialReportsFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + confidentialReportsFileIds.toString());
				Method setConfidentialReports = serviceDetails.getClass().getMethod("setConfidentialReports",
						String.class);
				setConfidentialReports.invoke(serviceDetails, confidentialReportsFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingComplaintDetails = ParamUtil.getParameterValues(resourceRequest, "existingComplaintDetails");
			String[] existingComplaintDetailsIds = ParamUtil.getParameterValues(resourceRequest, "existingComplaintDetailsIds");

			if (existingComplaintDetails != null && existingComplaintDetails.length > 0) {
			    if (existingComplaintDetails.length == existingComplaintDetailsIds.length) {
			        
			        for (int i = 0; i < existingComplaintDetails.length; i++) {
			            String fileIdStr = existingComplaintDetailsIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                complaintDetailsFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setComplaintDetails = serviceDetails.getClass().getMethod("setComplaintDetails",
							String.class);
			        setComplaintDetails.invoke(serviceDetails, complaintDetailsFileIds.toString());
			    }
			}

			if (uploadPortletRequest.getFile("complaintDetails") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("complaintDetails");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"complaintDetailsNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "complaintDetails",
							"Establishment", serviceContext, fileName, file);
					complaintDetailsFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + complaintDetailsFileIds.toString());
				Method setComplaintDetails = serviceDetails.getClass().getMethod("setComplaintDetails", String.class);
				setComplaintDetails.invoke(serviceDetails, complaintDetailsFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingNoDuesCertificate = ParamUtil.getParameterValues(resourceRequest, "existingNoDuesCertificate");
			String[] existingNoDuesCertificateIds = ParamUtil.getParameterValues(resourceRequest, "existingNoDuesCertificateIds");

			if (existingNoDuesCertificate != null && existingNoDuesCertificate.length > 0) {
			    if (existingNoDuesCertificate.length == existingNoDuesCertificateIds.length) {
			        
			        for (int i = 0; i < existingNoDuesCertificate.length; i++) {
			            String fileIdStr = existingNoDuesCertificateIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                noDuesCertificateFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setNoDuesCertificate = serviceDetails.getClass().getMethod("setNoDuesCertificate",
							String.class);
			        setNoDuesCertificate.invoke(serviceDetails, noDuesCertificateFileIds.toString());
			    }
			}

			if (uploadPortletRequest.getFile("noDuesCertificate") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("noDuesCertificate");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"noDuesCertificateNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "noDuesCertificate",
							"Establishment", serviceContext, fileName, file);
					noDuesCertificateFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + noDuesCertificateFileIds.toString());
				Method setNoDuesCertificate = serviceDetails.getClass().getMethod("setNoDuesCertificate", String.class);
				setNoDuesCertificate.invoke(serviceDetails, noDuesCertificateFileIds.toString());
			}
			
			
			// Process existing files (kept in the UI)
			String[] existingNoEnquiryCertificate = ParamUtil.getParameterValues(resourceRequest, "existingNoEnquiryCertificate");
			String[] existingNoEnquiryCertificateIds = ParamUtil.getParameterValues(resourceRequest, "existingNoEnquiryCertificateIds");

			if (existingNoEnquiryCertificate != null && existingNoEnquiryCertificate.length > 0) {
			    if (existingNoEnquiryCertificate.length == existingNoEnquiryCertificateIds.length) {
			        
			        for (int i = 0; i < existingNoEnquiryCertificate.length; i++) {
			            String fileIdStr = existingNoEnquiryCertificateIds[i];
			            
			            LOGGER.info("Existing File - Name: " +  ", ID: " + fileIdStr);
			            
			            try {
			                long fileEntryId = Long.parseLong(fileIdStr);
			                DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			                
			                noEnquiryCertificateFileIds.add(fileEntry.getFileEntryId());
			                
			            } catch (Exception e) {
			                LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
			            }
			        }
			        
			        Method setNoEnquiryCertificate = serviceDetails.getClass().getMethod("setNoEnquiryCertificate",
							String.class);
			        setNoEnquiryCertificate.invoke(serviceDetails, noEnquiryCertificateFileIds.toString());
			    }
			}

			if (uploadPortletRequest.getFile("noEnquiryCertificate") != null) {
				File[] actualStorageFiles = uploadPortletRequest.getFiles("noEnquiryCertificate");
				String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
						"noEnquiryCertificateNames");

				for (int i = 0; i < actualStorageFiles.length; i++) {
					File file = actualStorageFiles[i];
					String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "noEnquiryCertificate",
							"Establishment", serviceContext, fileName, file);
					noEnquiryCertificateFileIds.add(fileEntryId);
				}

				LOGGER.info(": " + noEnquiryCertificateFileIds.toString());
				Method setNoEnquiryCertificate = serviceDetails.getClass().getMethod("setNoEnquiryCertificate",
						String.class);
				setNoEnquiryCertificate.invoke(serviceDetails, noEnquiryCertificateFileIds.toString());
			}

			serviceDetails.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));

		} catch (Exception e) {
			LOGGER.error("Error in setting ServiceDetailsCommonDTO", e);
		}

		return serviceDetails;
	}

	public GPFDetailsCommonDTO setGPFDetailsDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long gpfDetailsId, long establishementId) {

		GPFDetailsCommonDTO gpfDetails = new GPFDetailsCommonDTO();

		try {
			gpfDetails.setgPFDetailsId(gpfDetailsId);
			gpfDetails.setEstablishmentDetailId(establishementId);
			gpfDetails.setGpfNumber(ParamUtil.getString(resourceRequest, "gpfNumber"));
			gpfDetails.setRefundableAmount(ParamUtil.getString(resourceRequest, "refundableAmount"));

			String refundableDateStr = ParamUtil.getString(resourceRequest, "refundableAmountDate");
			if (Validator.isNotNull(refundableDateStr)) {
				gpfDetails.setRefundableAmountDate(dateConversionUtil.convertStringToDateFormat(refundableDateStr));
			}

			gpfDetails.setNonRefundableAmount(ParamUtil.getString(resourceRequest, "nonRefundableAmount"));

			String nonRefundableDateStr = ParamUtil.getString(resourceRequest, "nonRefundableAmountDate");
			if (Validator.isNotNull(nonRefundableDateStr)) {
				gpfDetails
						.setNonRefundableAmountDate(dateConversionUtil.convertStringToDateFormat(nonRefundableDateStr));
			}

			gpfDetails.setMonthlyDeductionAmount(ParamUtil.getString(resourceRequest, "monthlyDeductionAmount"));
			gpfDetails.setMonthlyInstallmentAmount(ParamUtil.getString(resourceRequest, "monthlyInstallmentAmount"));
			gpfDetails
					.setNumberOfMonthlyInstallment(ParamUtil.getString(resourceRequest, "numberOfMonthlyInstallment"));
			gpfDetails.setBalanceAmount(ParamUtil.getString(resourceRequest, "balanceAmount"));
			gpfDetails.setUserId(themeDisplay.getUserId());
			gpfDetails.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
			Date now = new Date();
			gpfDetails.setCreateDate(now);
			gpfDetails.setModifiedDate(now);

		} catch (Exception e) {
			LOGGER.error("Error in setting GPFDetailsCommonDTO", e);
		}

		return gpfDetails;
	}

	public NPSDetailsCommonDTO setNPSDetailsDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long npsDetailsId, long establishementId) {

		NPSDetailsCommonDTO npsDetails = new NPSDetailsCommonDTO();

		try {
			npsDetails.setnPSDetailsId(npsDetailsId);
			npsDetails.setEstablishmentDetailId(establishementId);
			npsDetails.setNpsId(ParamUtil.getString(resourceRequest, "npsId"));
			npsDetails.setNpsRefundableAmount(ParamUtil.getString(resourceRequest, "npsRefundableAmount"));

			String refundableDateStr = ParamUtil.getString(resourceRequest, "npsRefundableAmountDate");
			if (Validator.isNotNull(refundableDateStr)) {
				npsDetails.setNpsRefundableAmountDate(dateConversionUtil.convertStringToDateFormat(refundableDateStr));
			}

			npsDetails.setNpsNonRefundableAmount(ParamUtil.getString(resourceRequest, "npsNonRefundableAmount"));

			String nonRefundableDateStr = ParamUtil.getString(resourceRequest, "npsNonRefundableAmountDate");
			if (Validator.isNotNull(nonRefundableDateStr)) {
				npsDetails.setNpsNonRefundableAmountDate(
						dateConversionUtil.convertStringToDateFormat(nonRefundableDateStr));
			}

			npsDetails.setNpsMonthlyDeductionAmount(ParamUtil.getString(resourceRequest, "npsMonthlyDeductionAmount"));
			npsDetails.setNpsMonthlyInstallmentAmount(
					ParamUtil.getString(resourceRequest, "npsMonthlyInstallmentAmount"));
			npsDetails.setNpsNumberOfMonthlyInstallment(
					ParamUtil.getString(resourceRequest, "npsNumberOfMonthlyInstallment"));
			npsDetails.setNpsBalanceAmount(ParamUtil.getString(resourceRequest, "npsBalanceAmount"));
			npsDetails.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
			// Set userId and timestamps
			npsDetails.setUserId(themeDisplay.getUserId());
			Date now = new Date();
			npsDetails.setCreateDate(now);
			npsDetails.setModifiedDate(now);

		} catch (Exception e) {
			LOGGER.error("Error while setting NPSDetailsCommonDTO", e);
		}

		return npsDetails;
	}

	public TrainingDetailsCommonDTO setTrainingDetailsDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long trainingDetailsId, long establishementId) {

		TrainingDetailsCommonDTO trainingDetails = new TrainingDetailsCommonDTO();

		try {
			trainingDetails.setTrainingDetailsId(trainingDetailsId);
			trainingDetails.setEstablishmentDetailId(establishementId);
			trainingDetails.setNumberOfTrainings(ParamUtil.getString(resourceRequest, "numberOfTrainings"));
			trainingDetails.setTrainingName(ParamUtil.getString(resourceRequest, "trainingName"));

			String startDateStr = ParamUtil.getString(resourceRequest, "trainingStartDate");
			if (Validator.isNotNull(startDateStr)) {
				trainingDetails.setTrainingStartDate(dateConversionUtil.convertStringToDateFormat(startDateStr));
			}

			String endDateStr = ParamUtil.getString(resourceRequest, "trainingEndDate");
			if (Validator.isNotNull(endDateStr)) {
				trainingDetails.setTrainingEndDate(dateConversionUtil.convertStringToDateFormat(endDateStr));
			}
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);
			long trainingCertificate = fileUploadUtil.uploadFile(uploadPortletRequest, "trainingCertificate",
					CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
			trainingDetails.setTrainingCertificate(trainingCertificate);

			trainingDetails.setUserId(themeDisplay.getUserId());
			trainingDetails.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
			Date now = new Date();
			trainingDetails.setCreateDate(now);
			trainingDetails.setModifiedDate(now);

		} catch (Exception e) {
			LOGGER.error("Error while setting TrainingDetailsCommonDTO", e);
		}

		return trainingDetails;
	}

	public PostingStatusCommonDTO setPostingStatusDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long postingStatusId, long establishementId) {

		PostingStatusCommonDTO postingStatus = new PostingStatusCommonDTO();

		try {
			postingStatus.setPostingStatusId(postingStatusId);
			postingStatus.setEstablishmentDetailId(establishementId);
			postingStatus.setPostingStatus(ParamUtil.getString(resourceRequest, "postingStatus"));

			postingStatus.setUserId(themeDisplay.getUserId());

			Date now = new Date();
			postingStatus.setCreateDate(now);
			postingStatus.setModifiedDate(now);
			postingStatus.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
		} catch (Exception e) {
			LOGGER.error("Error while setting PostingStatusCommonDTO", e);
		}

		return postingStatus;
	}

	public RoasterStatusCommonDTO setRoasterStatusDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long roasterStatusId, long establishementId) {

		RoasterStatusCommonDTO roasterStatus = new RoasterStatusCommonDTO();

		try {
			roasterStatus.setRoasterStatusId(roasterStatusId);
			roasterStatus.setEstablishmentDetailId(establishementId);
			roasterStatus.setNominationCodreWisePostWise(
					ParamUtil.getString(resourceRequest, "nominationCodreWisePostWise"));
			roasterStatus
					.setPromotionCodreWisePostWise(ParamUtil.getString(resourceRequest, "promotionCodreWisePostWise"));

			roasterStatus.setUserId(themeDisplay.getUserId());
			roasterStatus.setTypeOfRecord(ParamUtil.getString(resourceRequest, "typeOfRecord"));
			Date now = new Date();
			roasterStatus.setCreateDate(now);
			roasterStatus.setModifiedDate(now);

		} catch (Exception e) {
			LOGGER.error("Error while setting RoasterStatusCommonDTO", e);
		}

		return roasterStatus;
	}

	public List<Long> getFileEntryIdsFromString(String fileListStr, UploadPortletRequest uploadPortletRequest,
			ServiceContext serviceContext) {
		List<Long> fileEntryIds = new ArrayList<>();

		if (Validator.isNotNull(fileListStr)) {
			// Clean brackets and whitespace
			fileListStr = fileListStr.replace("[", "").replace("]", "").trim();

			String[] fileNames = fileListStr.split(",");

			for (String fileName : fileNames) {
				fileName = fileName.trim();
				if (Validator.isNotNull(fileName)) {
					try {

						long fileEntryId = fileUploadUtil.uploadFile(uploadPortletRequest, fileName,
								CommonUtilityConstant.ESTABLISHMENT_FOLDER, serviceContext);
						fileEntryIds.add(fileEntryId);
					} catch (Exception e) {
						LOGGER.error("Failed to process file: " + fileName, e);
					}
				}
			}
		}

		return fileEntryIds;
	}

	private void setFileField(ResourceRequest request, UploadPortletRequest uploadRequest,
			ServiceContext serviceContext, String paramName, Consumer<String> setter) {
		LOGGER.info("param name: " + ParamUtil.getString(request, paramName));
		String fileListStr = ParamUtil.getString(request, paramName);
		LOGGER.info("fileListStr: " + fileListStr);
		List<Long> fileEntryIds = getFileEntryIdsFromString(fileListStr, uploadRequest, serviceContext);
		if (!fileEntryIds.isEmpty()) {
			setter.accept(fileEntryIds.toString());
		}
	}

	public EstablishmentDetails findByUserId(long userId) {
		try {
			EstablishmentDetails establishment = EstablishmentDetailsLocalServiceUtil.findByUserId(userId);
			return establishment;
		} catch (Exception e) {
			LOGGER.error("Error checking establishment existence for userId: " + userId, e);
			return null;
		}
	}

//	public EstablishmentCommonDTO setEstablishmentDetails(long establishmentDetailId, String typeOfRecord,
//			ThemeDisplay themeDisplay) {
//		try {
//			LOGGER.info("typeOfRecord: "+typeOfRecord);
//			EstablishmentCommonDTO establishmentCommonDTO = new EstablishmentCommonDTO();
//			switch (typeOfRecord) {
//			case "Personal Details":
//				establishmentCommonDTO.setPersonalDetails(true);
//				break;
//			case "Service Details":
//				establishmentCommonDTO.setServiceDetails(true);
//				break;
//			case "Training Details":
//				establishmentCommonDTO.setTrainingDetails(true);
//				break;
//			case "NPS Details":
//				establishmentCommonDTO.setNpsDetails(true);
//				break;
//			case "GPF Details":
//				establishmentCommonDTO.setGpfDetails(true);
//				break;
//			case "Posting Status":
//				establishmentCommonDTO.setPostingStatus(true);
//				break;
//			case "Roaster Status":
//				establishmentCommonDTO.setRoasterStatus(true);
//				break;
//			default:
//				// Optional: log unknown type
//				break;
//			}
//			establishmentCommonDTO.setEstablishmentDetailId(establishmentDetailId);
//			establishmentCommonDTO.setCreateDate(new Date());
//			establishmentCommonDTO.setUserId(themeDisplay.getUserId());
//			return establishmentCommonDTO;
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//
//		}
//		return null;
//	}
	public EstablishmentCommonDTO setEstablishmentDetails(long establishmentDetailId, String typeOfRecord,
			ThemeDisplay themeDisplay) {

		try {
			LOGGER.info("typeOfRecord: " + typeOfRecord);
			EstablishmentCommonDTO establishmentCommonDTO = new EstablishmentCommonDTO();

			// Fetch existing record if available
			EstablishmentDetails existing = null;
			if (establishmentDetailId > 0) {
				existing = EstablishmentDetailsLocalServiceUtil.getEstablishmentDetails(establishmentDetailId);
			}

			boolean alreadySet = false;

			if (existing != null) {
				// Copy existing flags
				establishmentCommonDTO.setPersonalDetails(existing.getPersonalDetails());
				establishmentCommonDTO.setServiceDetails(existing.getServiceDetails());
				establishmentCommonDTO.setTrainingDetails(existing.getTrainingDetails());
				establishmentCommonDTO.setNpsDetails(existing.getNpsDetails());
				establishmentCommonDTO.setGpfDetails(existing.getGpfDetails());
				establishmentCommonDTO.setPostingStatus(existing.getPostingStatus());
				establishmentCommonDTO.setRoasterStatus(existing.getRoasterStatus());

				// Check if the flag for current type is already true
				switch (typeOfRecord) {
				case "Personal Details":
					alreadySet = existing.getPersonalDetails();
					break;
				case "Service Details":
					alreadySet = existing.getServiceDetails();
					break;
				case "Training Details":
					alreadySet = existing.getTrainingDetails();
					break;
				case "NPS Details":
					alreadySet = existing.getNpsDetails();
					break;
				case "GPF Details":
					alreadySet = existing.getGpfDetails();
					break;
				case "Posting Status":
					alreadySet = existing.getPostingStatus();
					break;
				case "Roaster Status":
					alreadySet = existing.getRoasterStatus();
					break;
				default:
					break;
				}

				if (alreadySet) {
					LOGGER.info(typeOfRecord + " is already marked true. Skipping update.");
					return null;
				}
			}

			// Set the current typeOfRecord flag to true
			switch (typeOfRecord) {
			case "Personal Details":
				establishmentCommonDTO.setPersonalDetails(true);
				break;
			case "Service Details":
				establishmentCommonDTO.setServiceDetails(true);
				break;
			case "Training Details":
				establishmentCommonDTO.setTrainingDetails(true);
				break;
			case "NPS Details":
				establishmentCommonDTO.setNpsDetails(true);
				break;
			case "GPF Details":
				establishmentCommonDTO.setGpfDetails(true);
				break;
			case "Posting Status":
				establishmentCommonDTO.setPostingStatus(true);
				break;
			case "Roaster Status":
				establishmentCommonDTO.setRoasterStatus(true);
				break;
			default:
				LOGGER.warn("Unknown typeOfRecord: " + typeOfRecord);
				break;
			}

			establishmentCommonDTO.setEstablishmentDetailId(establishmentDetailId);
			establishmentCommonDTO.setCreateDate(new Date());
			establishmentCommonDTO.setUserId(themeDisplay.getUserId());

			return establishmentCommonDTO;

		} catch (Exception e) {
			LOGGER.error("Error in setEstablishmentDetails: " + e.getMessage(), e);
		}

		return null;
	}

	public PersonalDetailsCommonDTO setPersonalDetailsDTO(PersonalDetails personalDetails, ThemeDisplay themeDisplay) {
		PersonalDetailsCommonDTO dto = new PersonalDetailsCommonDTO();

		try {
			LOGGER.debug("PersonalDetails model: " + personalDetails);
			BeanPropertiesUtil.copyProperties(personalDetails, dto);

			dto.setPersonalDetailsId(personalDetails.getPersonalDetailsId());
			dto.setEstablishmentDetailId(personalDetails.getEstablishmentDetailId());
			dto.setTypeOfRecord(personalDetails.getTypeOfRecord());
			dto.setEmployeeName(personalDetails.getEmployeeName());
			dto.setCategory(personalDetails.getCategory());
			dto.setSevarthID(personalDetails.getSevarthID());

			// Format and set DOB string
			LOGGER.info("dob: " + personalDetails.getDob());

			if (Validator.isNotNull(personalDetails.getDob())) {
				LOGGER.info("dob: " + formatter.format(personalDetails.getDob()));
				dto.setDobStr(formatter.format(personalDetails.getDob()));
			}

			dto.setHeight(personalDetails.getHeight());
			dto.setBodyMark(personalDetails.getBodyMark());
			dto.setEducation(personalDetails.getEducation());
			dto.setContactDetails(personalDetails.getContactDetails());
			dto.setMscIT(personalDetails.getMscIT());
			dto.setPostalAddress(personalDetails.getPostalAddress());
			dto.setHometown(personalDetails.getHometown());
			dto.setPermanentAddress(personalDetails.getPermanentAddress());
			dto.setUserId(personalDetails.getUserId());

			// Handle file entry names and URLs (if needed)
			// Set castCertificate file details
			if (personalDetails.getCastCertificate() > 0) {
				dto.setCastCertificate(personalDetails.getCastCertificate());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(personalDetails.getCastCertificate());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						dto.setCastCertificateName(trimmedFilename);
					} else {
						dto.setCastCertificateName(fileName);
					}
					dto.setCastCertificateURL(
							fileUploadUtil.getPreviewURL(personalDetails.getCastCertificate(), themeDisplay));
				}
			}

			// Set castValidity file details
			if (personalDetails.getCastValidity() > 0) {
				dto.setCastValidity(personalDetails.getCastValidity());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(personalDetails.getCastValidity());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						dto.setCastValidityName(trimmedFilename);
					} else {
						dto.setCastValidityName(fileName);
					}
					dto.setCastValidityURL(
							fileUploadUtil.getPreviewURL(personalDetails.getCastValidity(), themeDisplay));
				}
			}

			// Set specialAchievements file details
			if (personalDetails.getSpecialAchievements() > 0) {
				dto.setSpecialAchievements(personalDetails.getSpecialAchievements());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(personalDetails.getSpecialAchievements());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						dto.setSpecialAchievementsName(trimmedFilename);
					} else {
						dto.setSpecialAchievementsName(fileName);
					}
					dto.setSpecialAchievementsURL(
							fileUploadUtil.getPreviewURL(personalDetails.getSpecialAchievements(), themeDisplay));
				}
			}

			// Set sportsAchievements file details
			if (personalDetails.getSportsAchievements() > 0) {
				dto.setSportsAchievements(personalDetails.getSportsAchievements());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(personalDetails.getSportsAchievements());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						dto.setSportsAchievementsName(trimmedFilename);
					} else {
						dto.setSportsAchievementsName(fileName);
					}
					dto.setSportsAchievementsURL(
							fileUploadUtil.getPreviewURL(personalDetails.getSportsAchievements(), themeDisplay));
				}
			}

			LOGGER.debug("PersonalDetails DTO mapped: " + dto);

		} catch (Exception e) {
			LOGGER.error("Error setting PersonalDetails DTO: " + e.getMessage(), e);
		}

		return dto;
	}

	public TrainingDetailsCommonDTO setTrainingDetailsDTO(TrainingDetails trainingDetails, ThemeDisplay themeDisplay) {
		TrainingDetailsCommonDTO dto = new TrainingDetailsCommonDTO();

		try {
			LOGGER.debug("TrainingDetails model: " + trainingDetails);
			BeanPropertiesUtil.copyProperties(trainingDetails, dto);

			dto.setTrainingDetailsId(trainingDetails.getTrainingDetailsId());
			dto.setEstablishmentDetailId(trainingDetails.getEstablishmentDetailId());
			dto.setNumberOfTrainings(trainingDetails.getNumberOfTrainings());
			dto.setTrainingName(trainingDetails.getTrainingName());
			dto.setTypeOfRecord(trainingDetails.getTypeOfRecord());
			dto.setUserId(trainingDetails.getUserId());

			// Format date fields
			if (Validator.isNotNull(trainingDetails.getTrainingStartDate())) {
				dto.setTrainingStartDateStr(formatter.format(trainingDetails.getTrainingStartDate()));
			}
			if (Validator.isNotNull(trainingDetails.getTrainingEndDate())) {
				dto.setTrainingEndDateStr(formatter.format(trainingDetails.getTrainingEndDate()));
			}
			if (Validator.isNotNull(trainingDetails.getCreateDate())) {
				dto.setCreateDateStr(formatter.format(trainingDetails.getCreateDate()));
			}
			if (Validator.isNotNull(trainingDetails.getModifiedDate())) {
				dto.setModifiedDateStr(formatter.format(trainingDetails.getModifiedDate()));
			}

			// File Entry: Training Certificate
			if (trainingDetails.getTrainingCertificate() > 0) {
				dto.setTrainingCertificate(trainingDetails.getTrainingCertificate());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(trainingDetails.getTrainingCertificate());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						dto.setTrainingCertificateName(trimmedFilename);
					} else {
						dto.setTrainingCertificateName(fileName);
					}
					dto.setTrainingCertificateURL(
							fileUploadUtil.getPreviewURL(trainingDetails.getTrainingCertificate(), themeDisplay));
				}
			}

			LOGGER.debug("Mapped TrainingDetails DTO: " + dto);

		} catch (Exception e) {
			LOGGER.error("Error in setTrainingDetailsDTOFromModel: " + e.getMessage(), e);
		}

		return dto;
	}

	public GPFDetailsCommonDTO setGPFDetailsDTO(GPFDetails gpfDetails, ThemeDisplay themeDisplay) {
		GPFDetailsCommonDTO dto = new GPFDetailsCommonDTO();

		try {
			LOGGER.debug("Mapping GPFDetails to DTO: " + gpfDetails);

			// Copy direct properties
			BeanPropertiesUtil.copyProperties(gpfDetails, dto);

			// Format Date fields to String

			if (Validator.isNotNull(gpfDetails.getRefundableAmountDate())) {
				dto.setRefundableAmountDateStr(formatter.format(gpfDetails.getRefundableAmountDate()));
			}
			if (Validator.isNotNull(gpfDetails.getNonRefundableAmountDate())) {
				dto.setNonRefundableAmountDateStr(formatter.format(gpfDetails.getNonRefundableAmountDate()));
			}
			if (Validator.isNotNull(gpfDetails.getCreateDate())) {
				dto.setCreateDateStr(formatter.format(gpfDetails.getCreateDate()));
			}
			if (Validator.isNotNull(gpfDetails.getModifiedDate())) {
				dto.setModifiedDateStr(formatter.format(gpfDetails.getModifiedDate()));
			}

		} catch (Exception e) {
			LOGGER.error("Error while setting GPFDetails DTO from model: " + e.getMessage(), e);
		}

		return dto;
	}

	public NPSDetailsCommonDTO setNPSDetailsDTO(NPSDetails npsDetails, ThemeDisplay themeDisplay) {
		NPSDetailsCommonDTO dto = new NPSDetailsCommonDTO();

		try {
			LOGGER.debug("Mapping NPSDetails to DTO: " + npsDetails);

			// Copy direct fields
			BeanPropertiesUtil.copyProperties(npsDetails, dto);

			// Format Date fields to String

			if (Validator.isNotNull(npsDetails.getNpsRefundableAmountDate())) {
				dto.setNpsRefundableAmountDateStr(formatter.format(npsDetails.getNpsRefundableAmountDate()));
			}
			if (Validator.isNotNull(npsDetails.getNpsNonRefundableAmountDate())) {
				dto.setNpsNonRefundableAmountDateStr(formatter.format(npsDetails.getNpsNonRefundableAmountDate()));
			}
			if (Validator.isNotNull(npsDetails.getCreateDate())) {
				dto.setCreateDateStr(formatter.format(npsDetails.getCreateDate()));
			}
			if (Validator.isNotNull(npsDetails.getModifiedDate())) {
				dto.setModifiedDateStr(formatter.format(npsDetails.getModifiedDate()));
			}

		} catch (Exception e) {
			LOGGER.error("Error while setting NPSDetails DTO from model: " + e.getMessage(), e);
		}

		return dto;
	}

	public ServiceDetailsCommonDTO setServiceDetailsDTO(ServiceDetails serviceDetails, ThemeDisplay themeDisplay) {
		ServiceDetailsCommonDTO serviceDetailsDTO = new ServiceDetailsCommonDTO();

		try {
			LOGGER.debug("Service Details DTO : " + serviceDetails);
			BeanPropertiesUtil.copyProperties(serviceDetails, serviceDetailsDTO);

			serviceDetailsDTO.setDateOfSuspensionStr(formatter.format(serviceDetails.getDateOfSuspension()));
			serviceDetailsDTO.setDateOfReinstateStr(formatter.format(serviceDetails.getDateOfReinstate()));
			serviceDetailsDTO.setPoliceVerificationDateStr(formatter.format(serviceDetails.getPoliceVerificationDate()));
			serviceDetailsDTO.setDateOfRetirementStr(formatter.format(serviceDetails.getDateOfRetirement()));
			serviceDetailsDTO.setDateOfAppointmentStr(formatter.format(serviceDetails.getDateOfAppointment()));
			serviceDetailsDTO.setMedicalCertificateDateStr(formatter.format(serviceDetails.getMedicalCertificateDate()));
			serviceDetailsDTO.setLeaveFromDateStr(formatter.format(serviceDetails.getLeaveFromDate()));
			serviceDetailsDTO.setLeaveToDateStr(formatter.format(serviceDetails.getLeaveToDate()));
			
			// Single file upload code
			if (serviceDetailsDTO.getMedicalCertificate() > 0) {
				serviceDetailsDTO.setMedicalCertificate(serviceDetails.getMedicalCertificate());

				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(serviceDetails.getMedicalCertificate());
				if (Validator.isNotNull(fileEntry)) {
					String fileName = fileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						serviceDetailsDTO.setMedicalCertificateName(trimmedFilename);
					} else {
						serviceDetailsDTO.setMedicalCertificateName(fileName);
					}
					serviceDetailsDTO.setMedicalCertificateURL(
							fileUploadUtil.getPreviewURL(serviceDetails.getMedicalCertificate(), themeDisplay));
				}
			}
			
			if (serviceDetailsDTO.getLeaveDocument() > 0) {
			    serviceDetailsDTO.setLeaveDocument(serviceDetails.getLeaveDocument());

			    DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(serviceDetails.getLeaveDocument());
			    if (Validator.isNotNull(fileEntry)) {
			        String fileName = fileEntry.getFileName();
			        if (fileName.contains("_")) {
			            int underscoreIndex = fileName.indexOf('_');
			            String trimmedFilename = fileName.substring(underscoreIndex + 1);
			            serviceDetailsDTO.setLeaveDocumentName(trimmedFilename);
			        } else {
			            serviceDetailsDTO.setLeaveDocumentName(fileName);
			        }
			        serviceDetailsDTO.setLeaveDocumentURL(
			                fileUploadUtil.getPreviewURL(serviceDetails.getLeaveDocument(), themeDisplay));
			    }
			}

			if (serviceDetailsDTO.getSeniorityList() > 0) {
			    serviceDetailsDTO.setSeniorityList(serviceDetails.getSeniorityList());

			    DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(serviceDetails.getSeniorityList());
			    if (Validator.isNotNull(fileEntry)) {
			        String fileName = fileEntry.getFileName();
			        if (fileName.contains("_")) {
			            int underscoreIndex = fileName.indexOf('_');
			            String trimmedFilename = fileName.substring(underscoreIndex + 1);
			            serviceDetailsDTO.setSeniorityListName(trimmedFilename);
			        } else {
			            serviceDetailsDTO.setSeniorityListName(fileName);
			        }
			        serviceDetailsDTO.setSeniorityListURL(
			                fileUploadUtil.getPreviewURL(serviceDetails.getSeniorityList(), themeDisplay));
			    }
			}

			if (serviceDetailsDTO.getDepartmentalEnquiryReport() > 0) {
			    serviceDetailsDTO.setDepartmentalEnquiryReport(serviceDetails.getDepartmentalEnquiryReport());

			    DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(serviceDetails.getDepartmentalEnquiryReport());
			    if (Validator.isNotNull(fileEntry)) {
			        String fileName = fileEntry.getFileName();
			        if (fileName.contains("_")) {
			            int underscoreIndex = fileName.indexOf('_');
			            String trimmedFilename = fileName.substring(underscoreIndex + 1);
			            serviceDetailsDTO.setDepartmentalEnquiryReportName(trimmedFilename);
			        } else {
			            serviceDetailsDTO.setDepartmentalEnquiryReportName(fileName);
			        }
			        serviceDetailsDTO.setDepartmentalEnquiryReportURL(
			                fileUploadUtil.getPreviewURL(serviceDetails.getDepartmentalEnquiryReport(), themeDisplay));
			    }
			}

			if (serviceDetailsDTO.getPoliceVerification() > 0) {
			    serviceDetailsDTO.setPoliceVerification(serviceDetails.getPoliceVerification());

			    DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(serviceDetails.getPoliceVerification());
			    if (Validator.isNotNull(fileEntry)) {
			        String fileName = fileEntry.getFileName();
			        if (fileName.contains("_")) {
			            int underscoreIndex = fileName.indexOf('_');
			            String trimmedFilename = fileName.substring(underscoreIndex + 1);
			            serviceDetailsDTO.setPoliceVerificationName(trimmedFilename);
			        } else {
			            serviceDetailsDTO.setPoliceVerificationName(fileName);
			        }
			        serviceDetailsDTO.setPoliceVerificationURL(
			                fileUploadUtil.getPreviewURL(serviceDetails.getPoliceVerification(), themeDisplay));
			    }
			}

			
			
			
			// Multiple File Upload Code
			if (Validator.isNotNull(serviceDetailsDTO.getPermanencyCertificate())) {
				String permanencyCertificateStr = serviceDetailsDTO.getPermanencyCertificate();
				permanencyCertificateStr = permanencyCertificateStr.replaceAll("[\\[\\] ]", "");
				String[] permanencyCertificateIds = permanencyCertificateStr.split(",");

				List<String> permanencyCertificateURLs = new ArrayList<>();
				List<String> permanencyCertificateNames = new ArrayList<>();
				List<String> permanencyCertificateEntryIds = new ArrayList<>();

				for (String permanencyCertificateIdStr : permanencyCertificateIds) {
					try {
						long permanencyCertificateId = Long.parseLong(permanencyCertificateIdStr.trim());
						LOGGER.info("Permanency Certificate Id ::: " + permanencyCertificateId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(permanencyCertificateId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							permanencyCertificateNames.add(fileName);
							permanencyCertificateURLs.add(fileUploadUtil.getPreviewURL(permanencyCertificateId, themeDisplay));
							permanencyCertificateEntryIds.add(String.valueOf(permanencyCertificateId));
							LOGGER.info("URL : " + permanencyCertificateURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + permanencyCertificateIdStr, e);
					}
				}
				serviceDetailsDTO.setPermanencyCertificateURLs(permanencyCertificateURLs);
				serviceDetailsDTO.setPermanencyCertificateNames(permanencyCertificateNames);
				serviceDetailsDTO.setPermanencyCertificateEntryIds(permanencyCertificateEntryIds);
			}
			
			
			if (Validator.isNotNull(serviceDetailsDTO.getTreasuryVerification())) {
				String treasuryVerificationStr = serviceDetailsDTO.getTreasuryVerification();
				treasuryVerificationStr = treasuryVerificationStr.replaceAll("[\\[\\] ]", "");
				String[] treasuryVerificationIds = treasuryVerificationStr.split(",");

				List<String> treasuryVerificationURLs = new ArrayList<>();
				List<String> treasuryVerificationNames = new ArrayList<>();
				List<String> treasuryVerificationEntryIds = new ArrayList<>();

				for (String treasuryVerificationIdStr : treasuryVerificationIds) {
					try {
						long treasuryVerificationId = Long.parseLong(treasuryVerificationIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + treasuryVerificationId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(treasuryVerificationId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							treasuryVerificationNames.add(fileName);
							treasuryVerificationURLs.add(fileUploadUtil.getPreviewURL(treasuryVerificationId, themeDisplay));
							treasuryVerificationEntryIds.add(String.valueOf(treasuryVerificationId));
							LOGGER.info("URL : " + treasuryVerificationURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + treasuryVerificationIdStr, e);
					}
				}
				serviceDetailsDTO.setTreasuryVerificationURLs(treasuryVerificationURLs);
				serviceDetailsDTO.setTreasuryVerificationNames(treasuryVerificationNames);
				serviceDetailsDTO.setTreasuryVerificationEntryIds(treasuryVerificationEntryIds);
			}
			
			if (Validator.isNotNull(serviceDetailsDTO.getLanguageExamCertificate())) {
				String languageExamCertificateStr = serviceDetailsDTO.getLanguageExamCertificate();
				languageExamCertificateStr = languageExamCertificateStr.replaceAll("[\\[\\] ]", "");
				String[] languageExamCertificateIds = languageExamCertificateStr.split(",");

				List<String> languageExamCertificateURLs = new ArrayList<>();
				List<String> languageExamCertificateNames = new ArrayList<>();
				List<String> languageExamCertificateEntryIds = new ArrayList<>();

				for (String languageExamCertificateIdStr : languageExamCertificateIds) {
					try {
						long languageExamCertificateId = Long.parseLong(languageExamCertificateIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + languageExamCertificateId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(languageExamCertificateId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							languageExamCertificateNames.add(fileName);
							languageExamCertificateURLs.add(fileUploadUtil.getPreviewURL(languageExamCertificateId, themeDisplay));
							languageExamCertificateEntryIds.add(String.valueOf(languageExamCertificateId));
							LOGGER.info("URL : " + languageExamCertificateURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + languageExamCertificateIdStr, e);
					}
				}
				serviceDetailsDTO.setLanguageExamCertificateURLs(languageExamCertificateURLs);
				serviceDetailsDTO.setLanguageExamCertificateNames(languageExamCertificateNames);
				serviceDetailsDTO.setLanguageExamCertificateEntryIds(languageExamCertificateEntryIds);
			}
			
			
			if (Validator.isNotNull(serviceDetailsDTO.getAwardCertificationOfHonor())) {
				String awardCertificationOfHonorStr = serviceDetailsDTO.getAwardCertificationOfHonor();
				awardCertificationOfHonorStr = awardCertificationOfHonorStr.replaceAll("[\\[\\] ]", "");
				String[] awardCertificationOfHonorIds = awardCertificationOfHonorStr.split(",");

				List<String> awardCertificationOfHonorURLs = new ArrayList<>();
				List<String> awardCertificationOfHonorNames = new ArrayList<>();
				List<String> awardCertificationOfHonorEntryIds = new ArrayList<>();

				for (String awardCertificationOfHonorIdStr : awardCertificationOfHonorIds) {
					try {
						long awardCertificationOfHonorId = Long.parseLong(awardCertificationOfHonorIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + awardCertificationOfHonorId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(awardCertificationOfHonorId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							awardCertificationOfHonorNames.add(fileName);
							awardCertificationOfHonorURLs.add(fileUploadUtil.getPreviewURL(awardCertificationOfHonorId, themeDisplay));
							awardCertificationOfHonorEntryIds.add(String.valueOf(awardCertificationOfHonorId));
							LOGGER.info("URL : " + awardCertificationOfHonorURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + awardCertificationOfHonorIdStr, e);
					}
				}
				serviceDetailsDTO.setAwardCertificationOfHonorURLs(awardCertificationOfHonorURLs);
				serviceDetailsDTO.setAwardCertificationOfHonorNames(awardCertificationOfHonorNames);
				serviceDetailsDTO.setAwardCertificationOfHonorEntryIds(awardCertificationOfHonorEntryIds);
			}
			
			if (Validator.isNotNull(serviceDetailsDTO.getConfidentialReports())) {
				String confidentialReportsStr = serviceDetailsDTO.getConfidentialReports();
				confidentialReportsStr = confidentialReportsStr.replaceAll("[\\[\\] ]", "");
				String[] confidentialReportsIds = confidentialReportsStr.split(",");

				List<String> confidentialReportsURLs = new ArrayList<>();
				List<String> confidentialReportsNames = new ArrayList<>();
				List<String> confidentialReportsEntryIds = new ArrayList<>();

				for (String confidentialReportsIdStr : confidentialReportsIds) {
					try {
						long confidentialReportsId = Long.parseLong(confidentialReportsIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + confidentialReportsId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(confidentialReportsId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							confidentialReportsNames.add(fileName);
							confidentialReportsURLs.add(fileUploadUtil.getPreviewURL(confidentialReportsId, themeDisplay));
							confidentialReportsEntryIds.add(String.valueOf(confidentialReportsId));
							LOGGER.info("URL : " + confidentialReportsURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + confidentialReportsIdStr, e);
					}
				}
				serviceDetailsDTO.setConfidentialReportsURLs(confidentialReportsURLs);
				serviceDetailsDTO.setConfidentialReportsNames(confidentialReportsNames);
				serviceDetailsDTO.setConfidentialReportsEntryIds(confidentialReportsEntryIds);
			}
			
			
			
			if (Validator.isNotNull(serviceDetailsDTO.getComplaintDetails())) {
				String complaintDetailsStr = serviceDetailsDTO.getComplaintDetails();
				complaintDetailsStr = complaintDetailsStr.replaceAll("[\\[\\] ]", "");
				String[] complaintDetailsIds = complaintDetailsStr.split(",");

				List<String> complaintDetailsURLs = new ArrayList<>();
				List<String> complaintDetailsNames = new ArrayList<>();
				List<String> complaintDetailsEntryIds = new ArrayList<>();

				for (String complaintDetailsIdStr : complaintDetailsIds) {
					try {
						long complaintDetailsId = Long.parseLong(complaintDetailsIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + complaintDetailsId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(complaintDetailsId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							complaintDetailsNames.add(fileName);
							complaintDetailsURLs.add(fileUploadUtil.getPreviewURL(complaintDetailsId, themeDisplay));
							complaintDetailsEntryIds.add(String.valueOf(complaintDetailsId));
							LOGGER.info("URL : " + complaintDetailsURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + complaintDetailsIdStr, e);
					}
				}
				serviceDetailsDTO.setComplaintDetailsURLs(complaintDetailsURLs);
				serviceDetailsDTO.setComplaintDetailsNames(complaintDetailsNames);
				serviceDetailsDTO.setComplaintDetailsEntryIds(complaintDetailsEntryIds);
			}
			
			
			if (Validator.isNotNull(serviceDetailsDTO.getNoDuesCertificate())) {
				String noDuesCertificateStr = serviceDetailsDTO.getNoDuesCertificate();
				noDuesCertificateStr = noDuesCertificateStr.replaceAll("[\\[\\] ]", "");
				String[] noDuesCertificateIds = noDuesCertificateStr.split(",");

				List<String> noDuesCertificateURLs = new ArrayList<>();
				List<String> noDuesCertificateNames = new ArrayList<>();
				List<String> noDuesCertificateEntryIds = new ArrayList<>();

				for (String noDuesCertificateIdStr : noDuesCertificateIds) {
					try {
						long noDuesCertificateId = Long.parseLong(noDuesCertificateIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + noDuesCertificateId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(noDuesCertificateId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							noDuesCertificateNames.add(fileName);
							noDuesCertificateURLs.add(fileUploadUtil.getPreviewURL(noDuesCertificateId, themeDisplay));
							noDuesCertificateEntryIds.add(String.valueOf(noDuesCertificateId));
							LOGGER.info("URL : " + noDuesCertificateURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + noDuesCertificateIdStr, e);
					}
				}
				serviceDetailsDTO.setNoDuesCertificateURLs(noDuesCertificateURLs);
				serviceDetailsDTO.setNoDuesCertificateNames(noDuesCertificateNames);
				serviceDetailsDTO.setNoDuesCertificateEntryIds(noDuesCertificateEntryIds);
			}
			
			if (Validator.isNotNull(serviceDetailsDTO.getNoEnquiryCertificate())) {
				String noEnquiryCertificateStr = serviceDetailsDTO.getNoEnquiryCertificate();
				noEnquiryCertificateStr = noEnquiryCertificateStr.replaceAll("[\\[\\] ]", "");
				String[] noEnquiryCertificateIds = noEnquiryCertificateStr.split(",");

				List<String> noEnquiryCertificateURLs = new ArrayList<>();
				List<String> noEnquiryCertificateNames = new ArrayList<>();
				List<String> noEnquiryCertificateEntryIds = new ArrayList<>();

				for (String noEnquiryCertificateIdStr : noEnquiryCertificateIds) {
					try {
						long noEnquiryCertificateId = Long.parseLong(noEnquiryCertificateIdStr.trim());
						LOGGER.info("Treasury Verification Id ::: " + noEnquiryCertificateId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(noEnquiryCertificateId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							noEnquiryCertificateNames.add(fileName);
							noEnquiryCertificateURLs.add(fileUploadUtil.getPreviewURL(noEnquiryCertificateId, themeDisplay));
							noEnquiryCertificateEntryIds.add(String.valueOf(noEnquiryCertificateId));
							LOGGER.info("URL : " + noEnquiryCertificateURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing File ID: " + noEnquiryCertificateIdStr, e);
					}
				}
				serviceDetailsDTO.setNoEnquiryCertificateURLs(noEnquiryCertificateURLs);
				serviceDetailsDTO.setNoEnquiryCertificateNames(noEnquiryCertificateNames);
				serviceDetailsDTO.setNoEnquiryCertificateEntryIds(noEnquiryCertificateEntryIds);
			}
			
			

			LOGGER.debug("Service Details DTO : " + serviceDetailsDTO);

		} catch (Exception e) {
			LOGGER.error("Error setting Service Details DTO: " + e.getMessage(), e);
		}

		return serviceDetailsDTO;
	}

}
