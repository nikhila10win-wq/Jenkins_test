package com.mhdsys.administartive.util;

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
import com.mhdsys.common.pojo.EventCertificateCommonDTO;
import com.mhdsys.common.pojo.NCCGrantCommonDTO;
import com.mhdsys.common.pojo.NCCGrantRequestCommonDTO;
import com.mhdsys.common.pojo.ScoutAndGuideRegistrationCommonDTO;
import com.mhdsys.common.pojo.StudentRegistrationCommonDTO;
import com.mhdsys.common.pojo.UnitRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.NCCGrant;
import com.mhdsys.schema.model.NccGrantRequest;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.service.NccGrantRequestLocalServiceUtil;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = AdministrativeUtil.class)

public class AdministrativeUtil {
	@Reference
	FileUploadUtil fileUploadUtil;
	@Reference
	DateConversionUtil dateConversionUtil;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private Log LOGGER = LogFactoryUtil.getLog(AdministrativeUtil.class);

	public UnitRegistrationCommonDTO setUnitRegistrationData(UnitRegistrationCommonDTO dto) {
		UnitRegistrationCommonDTO unit = new UnitRegistrationCommonDTO();

		unit.setUnitType(dto.getUnitType());
		unit.setUnitName(dto.getUnitName());
		unit.setUnitCharterNumber(dto.getUnitCharterNumber());
		unit.setYear(dto.getYear());
		unit.setUdis(dto.getUdis());
		unit.setSchoolName(dto.getSchoolName());
		unit.setAddress(dto.getAddress());
		unit.setPincode(dto.getPincode());
		unit.setSchoolEmail(dto.getSchoolEmail());
		unit.setSchoolContact(dto.getSchoolContact());
		unit.setPrincipalMobile(dto.getPrincipalMobile());
		unit.setSelfDeclaration(dto.isSelfDeclaration());

		return unit;
	}

	public UserInformationModel setUserInformationModule(ResourceRequest resourceRequest, String regType) {

		UserInformationModel userInformationModel = new UserInformationModel();
		try {
			LOGGER.info("reg type: " + regType);
			if (regType.equalsIgnoreCase(RoleConstant.SCOUT_MASTER)) {
				regType = "ScoutMaster";
			} else if (regType.equalsIgnoreCase(RoleConstant.GUIDE_MASTER)) {
				regType = "GuideMaster";
			}
			userInformationModel.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			userInformationModel.setLastName(".");
			userInformationModel.setEmail(ParamUtil.getString(resourceRequest, "emailId"));
			userInformationModel.setScreenName(regType);

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
			userInformationModel.setEmail(ParamUtil.getString(resourceRequest, "emailId"));
			userInformationModel.setScreenName(regType);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return userInformationModel;
	}

	public ScoutAndGuideRegistrationCommonDTO setScoutAndGuideRegistration(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {

		ScoutAndGuideRegistrationCommonDTO scoutDTO = new ScoutAndGuideRegistrationCommonDTO();

		try {
			LOGGER.info("ScoutAndGuideRegistration Util ::: ");

			// Basic details
			scoutDTO.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			scoutDTO.setLastName(ParamUtil.getString(resourceRequest, "lastName"));
			scoutDTO.setMotherName(ParamUtil.getString(resourceRequest, "motherName"));
			scoutDTO.setFatherName(ParamUtil.getString(resourceRequest, "fatherName"));
			scoutDTO.setGender(ParamUtil.getString(resourceRequest, "gender"));

			// Contact and verification
			scoutDTO.setEmailId(ParamUtil.getString(resourceRequest, "emailId"));
			scoutDTO.setMobileNumber(ParamUtil.getString(resourceRequest, "mobileNumber"));
			scoutDTO.setAadharNumber(ParamUtil.getString(resourceRequest, "aadharNumber"));

			// Employment
			scoutDTO.setDesignation(ParamUtil.getString(resourceRequest, "designation"));
			scoutDTO.setSchoolName(ParamUtil.getString(resourceRequest, "schoolName"));

			// Audit fields
			scoutDTO.setCreateDate(new Date());
			scoutDTO.setModifiedDate(new Date());
			scoutDTO.setUserId(themeDisplay.getUserId());

			// File Upload
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);

			long aadharFileEntryId = fileUploadUtil.uploadFile(uploadPortletRequest, "aadharCard", "Administrative",
					serviceContext);
			LOGGER.info("aadharCardFileEntryId: " + aadharFileEntryId);
			scoutDTO.setAadharCardFileEntryId(aadharFileEntryId);

			LOGGER.info("scoutDTO: " + scoutDTO);

		} catch (Exception e) {
			LOGGER.error("Error while setting ScoutAndGuideRegistration data: ", e);
		}

		return scoutDTO;
	}

	public StudentRegistrationCommonDTO setStudentRegistration(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long studentRegistrationId) {

		StudentRegistrationCommonDTO studentDTO = new StudentRegistrationCommonDTO();

		try {
			LOGGER.info("Setting StudentRegistrationCommonDTO from ResourceRequest");
			studentDTO.setStudentRegistrationId(studentRegistrationId);
			studentDTO.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
			studentDTO.setLastName(ParamUtil.getString(resourceRequest, "lastName"));
			studentDTO.setFathersName(ParamUtil.getString(resourceRequest, "fatherName"));
			studentDTO.setMothersName(ParamUtil.getString(resourceRequest, "motherName"));
			studentDTO.setGender(ParamUtil.getString(resourceRequest, "gender"));
			studentDTO.setAadharNumber(ParamUtil.getString(resourceRequest, "aadharNumber"));
			studentDTO.setStandard(ParamUtil.getString(resourceRequest, "standard"));
			studentDTO.setSchoolCollegeName(ParamUtil.getString(resourceRequest, "schoolCollegeName"));
			// Set audit details
			studentDTO.setUserId(themeDisplay.getUserId());
			studentDTO.setCreatedDate(new Date());
			studentDTO.setModifiedDate(new Date());

		} catch (Exception e) {
			LOGGER.error("Error while setting StudentRegistrationCommonDTO", e);
		}

		return studentDTO;
	}

	public StudentRegistrationCommonDTO setStudentRegister(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long studentRegistrationId, boolean isCommandingOfficer) {

		StudentRegistrationCommonDTO studentDTO = new StudentRegistrationCommonDTO();

		try {
			LOGGER.info("Setting StudentRegistrationCommonDTO from ResourceRequest");
			StudentRegistration studentRegistration = StudentRegistrationLocalServiceUtil
					.getStudentRegistration(studentRegistrationId);
			BeanPropertiesUtil.copyProperties(studentRegistration, studentDTO);
			studentDTO.setGender(studentRegistration.getGender());
			if (isCommandingOfficer) {
				studentDTO.setRegisterTo("NCC");
			} else {
				if (studentDTO.getGender().equalsIgnoreCase("Male")) {
					studentDTO.setRegisterTo("Scout");
				} else if (studentDTO.getGender().equalsIgnoreCase("Female")) {
					studentDTO.setRegisterTo("Guide");
				}
			}
			studentDTO.setTransferTo("");
			studentDTO.setTransfer(false);
			// Set audit details
			studentDTO.setModifiedDate(new Date());

		} catch (Exception e) {
			LOGGER.error("Error while setting StudentRegistrationCommonDTO", e);
		}

		return studentDTO;
	}

	public StudentRegistrationCommonDTO setStudentTransfer(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long studentRegistrationId, boolean isCommandingOfficer) {

		StudentRegistrationCommonDTO studentDTO = new StudentRegistrationCommonDTO();

		try {
			LOGGER.info("Setting StudentRegistrationCommonDTO from ResourceRequest");
			StudentRegistration studentRegistration = StudentRegistrationLocalServiceUtil
					.getStudentRegistration(studentRegistrationId);
			BeanPropertiesUtil.copyProperties(studentRegistration, studentDTO);
			if (isCommandingOfficer && studentDTO.getGender().equalsIgnoreCase("Male")) {
				studentDTO.setTransferTo("Scout");

			} else if (isCommandingOfficer && studentDTO.getGender().equalsIgnoreCase("Female")) {
				studentDTO.setTransferTo("Guide");
			} else {
				studentDTO.setTransferTo("Commanding Officer");
			}
			studentDTO.setTransfer(true);
			studentDTO.setRegisterTo("");
			// Set audit details
			studentDTO.setModifiedDate(new Date());

		} catch (Exception e) {
			LOGGER.error("Error while setting StudentRegistrationCommonDTO", e);
		}

		return studentDTO;
	}

	public StudentRegistrationCommonDTO setStudentUnitType(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long studentRegistrationId) {

		StudentRegistrationCommonDTO studentDTO = new StudentRegistrationCommonDTO();

		try {
			LOGGER.info("Setting StudentRegistrationCommonDTO from ResourceRequest");
			StudentRegistration studentRegistration = StudentRegistrationLocalServiceUtil
					.getStudentRegistration(studentRegistrationId);
			BeanPropertiesUtil.copyProperties(studentRegistration, studentDTO);
			studentDTO.setUnitType(ParamUtil.getString(resourceRequest, "unitType"));
			// Set audit details
			studentDTO.setModifiedDate(new Date());

		} catch (Exception e) {
			LOGGER.error("Error while setting StudentRegistrationCommonDTO", e);
		}

		return studentDTO;
	}

	public UnitRegistrationCommonDTO setUnitRegistration(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {

		UnitRegistrationCommonDTO unitDTO = new UnitRegistrationCommonDTO();

		try {
			LOGGER.info("Setting UnitRegistrationCommonDTO from ResourceRequest");

			// Basic Unit Info
			unitDTO.setUnitType(ParamUtil.getString(resourceRequest, "unitType"));
			unitDTO.setUnitName(ParamUtil.getString(resourceRequest, "unitName"));
			unitDTO.setUnitCharterNumber(ParamUtil.getInteger(resourceRequest, "unitCharterNumber"));
			unitDTO.setYear(ParamUtil.getString(resourceRequest, "year"));
			unitDTO.setUdis(ParamUtil.getString(resourceRequest, "udis"));

			// School Info
			unitDTO.setSchoolName(ParamUtil.getString(resourceRequest, "schoolName"));
			unitDTO.setAddress(ParamUtil.getString(resourceRequest, "address"));
			unitDTO.setPincode(ParamUtil.getString(resourceRequest, "pincode"));
			unitDTO.setSchoolEmail(ParamUtil.getString(resourceRequest, "schoolEmail"));
			unitDTO.setSchoolContact(ParamUtil.getString(resourceRequest, "schoolContact"));
			unitDTO.setPrincipalMobile(ParamUtil.getString(resourceRequest, "principalMobile"));

			// Declaration
			unitDTO.setSelfDeclaration(ParamUtil.getBoolean(resourceRequest, "selfDeclaration"));

			// Audit Fields
			unitDTO.setUserId(themeDisplay.getUserId());
			unitDTO.setCreateDate(new Date());
			unitDTO.setModifiedDate(new Date());

			LOGGER.info("unitDTO: " + unitDTO);

		} catch (Exception e) {
			LOGGER.error("Error while setting UnitRegistrationCommonDTO: ", e);
		}

		return unitDTO;
	}

	public EventCertificateCommonDTO setEventCertificate(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {

		EventCertificateCommonDTO eventDTO = new EventCertificateCommonDTO();

		try {
			LOGGER.info("Setting EventCertificateCommonDTO from ResourceRequest");

			// Basic Event Info
			eventDTO.setYear(ParamUtil.getString(resourceRequest, "year"));
			eventDTO.setEventName(ParamUtil.getString(resourceRequest, "eventName"));
			eventDTO.setAddress(ParamUtil.getString(resourceRequest, "address"));
			String eventDateStr = ParamUtil.getString(resourceRequest, "eventDate");
			if (Validator.isNotNull(eventDateStr)) {
				eventDTO.setEventDate(dateConversionUtil.convertStringToDateFormat(eventDateStr));
			}
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);

			if (uploadPortletRequest.getFile("locationPhotos") != null) {
				File[] uploadedFiles = uploadPortletRequest.getFiles("certificate");
				String[] uploadedFileNames = ParamUtil.getParameterValues(resourceRequest, "certificateNames");

				List<Long> certificateFileIds = new ArrayList<>();

				for (int i = 0; i < uploadedFiles.length; i++) {
					File file = uploadedFiles[i];
					String fileName = uploadedFileNames.length > i ? uploadedFileNames[i] : "unknown";

					long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "certificate",
							"Administrative", // Folder or category name
							serviceContext, fileName, file);
					LOGGER.info("fileEntryId: " + fileEntryId);

					certificateFileIds.add(fileEntryId);
				}

				LOGGER.info("Uploaded Certificate File IDs: " + certificateFileIds);

				// Dynamically set the field using reflection
				Method setCertificateFiles = eventDTO.getClass().getMethod("setCertificateFiles", String.class);
				setCertificateFiles.invoke(eventDTO, certificateFileIds.toString());
			}

			// File Uploads (certificates)
			String[] certificateNames = ParamUtil.getParameterValues(resourceRequest, "certificateNames");
			if (Validator.isNotNull(certificateNames)) {
//	            eventDTO.setCertificateFiles(Arrays.asList(certificateNames));
			}

			// Audit Fields
			eventDTO.setUserId(themeDisplay.getUserId());
			eventDTO.setCreateDate(new Date());
			eventDTO.setModifiedDate(new Date());

			LOGGER.info("eventDTO: " + eventDTO);

		} catch (Exception e) {
			LOGGER.error("Error while setting EventCertificateCommonDTO: ", e);
		}

		return eventDTO;
	}

	public EventCertificateCommonDTO setEventCertificateCommonDTO(EventCertificate eventCertificate,
			ThemeDisplay themeDisplay) {

		EventCertificateCommonDTO commonDTO = new EventCertificateCommonDTO();

		try {
			commonDTO.setEventCertificateId(eventCertificate.getEventCertificateId());
			commonDTO.setYear(eventCertificate.getYear());
			commonDTO.setEventName(eventCertificate.getEventName());
			commonDTO.setAddress(eventCertificate.getAddress());
			commonDTO.setEventDate(eventCertificate.getEventDate());
			commonDTO.setUserId(eventCertificate.getUserId());
			commonDTO.setCreateDate(eventCertificate.getCreateDate());
			commonDTO.setModifiedDate(new Date());
			commonDTO.setCertificateFiles(eventCertificate.getCertificateFiles());

			// Convert eventDate to string
			Date eventDate = eventCertificate.getEventDate();
			if (Validator.isNotNull(eventDate)) {
				commonDTO.setEventDateStr(formatter.format(eventDate));
			} else {
				commonDTO.setEventDateStr(null);
			}

			// Process certificate files
			if (Validator.isNotNull(eventCertificate.getCertificateFiles())) {
				String certificateStr = eventCertificate.getCertificateFiles();
				certificateStr = certificateStr.replaceAll("[\\[\\] ]", "");
				String[] certificateIds = certificateStr.split(",");
				LOGGER.info("certificateIds: " + certificateIds);
				List<String> certificateURLs = new ArrayList<>();
				List<String> certificateFileNames = new ArrayList<>();
				if (Validator.isNotNull(certificateIds)) {
					for (String certificateIdStr : certificateIds) {
						try {
							long certificateId = Long.parseLong(certificateIdStr.trim());
							DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(certificateId);

							if (Validator.isNotNull(fileEntry)) {
								String fileName = fileEntry.getFileName();

								if (fileName.contains("_")) {
									int underscoreIndex = fileName.indexOf('_');
									fileName = fileName.substring(underscoreIndex + 1);
								}

								certificateFileNames.add(fileName);
								certificateURLs.add(fileUploadUtil.getPreviewURL(certificateId, themeDisplay));
							}
						} catch (Exception e) {
							LOGGER.error("Error processing certificate ID: " + certificateIdStr, e);
						}
					}
				}
				commonDTO.setCertificateFileNames(certificateFileNames);
				commonDTO.setCertificateURLs(certificateURLs);
			}

		} catch (Exception e) {
			LOGGER.error("Error in setEventCertificateCommonDTO: ", e);
		}

		return commonDTO;
	}

	public NCCGrantCommonDTO setNCCGrant(ResourceRequest resourceRequest, ThemeDisplay themeDisplay, long grantId) {

		NCCGrantCommonDTO dto = new NCCGrantCommonDTO();

		try {
			LOGGER.info("Setting NCCGrant DTO...");

			// === Unit Details ===
			dto.setNccGrantId(grantId);
			dto.setFinancialYear(ParamUtil.getString(resourceRequest, "financialYear"));
			dto.setDepartment(ParamUtil.getString(resourceRequest, "department"));
			dto.setGroupName(ParamUtil.getString(resourceRequest, "groupName"));
			dto.setUnitType(ParamUtil.getString(resourceRequest, "unitType"));
			dto.setCommandingOfficer(ParamUtil.getString(resourceRequest, "commandingOfficer"));
			dto.setDemandedNumber(ParamUtil.getInteger(resourceRequest, "demandedNumber"));
			dto.setSchemeNumber(ParamUtil.getInteger(resourceRequest, "schemeNumber"));
			dto.setDetailHead(ParamUtil.getString(resourceRequest, "detailHead"));

			// === File Upload ===
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					uploadPortletRequest);

			long utilizationCertificateId = fileUploadUtil.uploadFile(uploadPortletRequest, "utilizationCertificate",
					"Administrative", serviceContext);
			dto.setUtilizationCertificate(utilizationCertificateId);

			// === Grants Details ===
			dto.setGrantsReceived(ParamUtil.getInteger(resourceRequest, "grantsReceived"));
			dto.setGrantSurrenderReapp(ParamUtil.getInteger(resourceRequest, "grantSurrenderReapp"));
			dto.setGrantsWithdrawn(ParamUtil.getString(resourceRequest, "grantsWithdrawn"));
			dto.setGrantsWithdrawnBy(ParamUtil.getString(resourceRequest, "grantsWithdrawnBy"));
			dto.setGrantsSurrender(ParamUtil.getString(resourceRequest, "grantsSurrender"));
			dto.setGrantsSurrenderTo(ParamUtil.getString(resourceRequest, "grantsSurrenderTo"));
			dto.setGrantsAllowed(ParamUtil.getInteger(resourceRequest, "grantsAllowed"));
			dto.setGrantRecievedReapp(ParamUtil.getInteger(resourceRequest, "grantRecievedReapp"));
			dto.setBalance(ParamUtil.getDouble(resourceRequest, "balance"));
			dto.setExpenses(ParamUtil.getDouble(resourceRequest, "expenses"));
			dto.setActualExpense(ParamUtil.getDouble(resourceRequest, "actualExpense"));
			dto.setBalanceWithDdo(ParamUtil.getDouble(resourceRequest, "balanceWithDdo"));
			dto.setStatus(ParamUtil.getString(resourceRequest, "status"));
			// === Remark Details ===
			dto.setRemarks(ParamUtil.getString(resourceRequest, "remarks"));

			long otherUploadId = fileUploadUtil.uploadFile(uploadPortletRequest, "otherUpload", "Administrative",
					serviceContext);
			dto.setOtherUpload(otherUploadId);

			// === Dates ===
			dto.setCreateDate(new Date());
			dto.setModifiedDated(new Date());

			LOGGER.info("NCCGrant DTO created: " + dto);

		} catch (Exception e) {
			LOGGER.error("Error while setting NCCGrant data: ", e);
		}

		return dto;
	}

	public NCCGrantCommonDTO setNCCGrantCommonDTO(NCCGrant nccGrant, ThemeDisplay themeDisplay) {

		NCCGrantCommonDTO commonDTO = new NCCGrantCommonDTO();

		try {
			// Copy all matching simple properties
			BeanPropertiesUtil.copyProperties(nccGrant, commonDTO);

			// Manually set modified date to current time
			commonDTO.setModifiedDated(new Date());

			// Handle utilization certificate file separately
			if (Validator.isNotNull(nccGrant.getUtilizationCertificate())) {
				try {
					DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
							.getFileEntry(nccGrant.getUtilizationCertificate());
					if (Validator.isNotNull(fileEntry)) {
						String fileName = fileEntry.getFileName();
						if (fileName.contains("_")) {
							int underscoreIndex = fileName.indexOf('_');
							fileName = fileName.substring(underscoreIndex + 1);
						}
						commonDTO.setUtilizationCertificateName(fileName); // add getter/setter in DTO
						commonDTO.setUtilizationCertificateURL(
								fileUploadUtil.getPreviewURL(nccGrant.getUtilizationCertificate(), themeDisplay)); // add
																													// getter/setter
																													// in
																													// DTO
					}
				} catch (Exception e) {
					LOGGER.error(
							"Error retrieving utilization certificate file: " + nccGrant.getUtilizationCertificate(),
							e);
				}
			}
			if (Validator.isNotNull(nccGrant.getOtherUpload())) {
				try {
					DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(nccGrant.getOtherUpload());
					if (Validator.isNotNull(fileEntry)) {
						String fileName = fileEntry.getFileName();
						if (fileName.contains("_")) {
							int underscoreIndex = fileName.indexOf('_');
							fileName = fileName.substring(underscoreIndex + 1);
						}
						commonDTO.setOtherUploadName(fileName); // add getter/setter in DTO
						commonDTO.setOtherUploadURL(
								fileUploadUtil.getPreviewURL(nccGrant.getOtherUpload(), themeDisplay)); // add
																										// getter/setter
																										// in DTO
					}
				} catch (Exception e) {
					LOGGER.error("Error retrieving other upload file: " + nccGrant.getOtherUpload(), e);
				}
			}
			// Optional: if you want to format date fields (like createDate) as strings
			// Add logic here if needed

		} catch (Exception e) {
			LOGGER.error("Error in setNCCGrantCommonDTO: ", e);
		}

		return commonDTO;
	}

	public NCCGrantRequestCommonDTO setNccGrantRequest(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long nccGrantRequestId, String status) {

		NCCGrantRequestCommonDTO grantDTO = new NCCGrantRequestCommonDTO();

		try {
			grantDTO.setNccGrantRequestId(nccGrantRequestId);
			NccGrantRequest existingRequest = Validator.isNotNull(nccGrantRequestId)? NccGrantRequestLocalServiceUtil.getNccGrantRequest(nccGrantRequestId):null;
			
			LOGGER.info("Setting NccGrantCommonDTO from ResourceRequest");
			if (nccGrantRequestId <= 0 || (nccGrantRequestId>0 && Validator.isNotNull(existingRequest) && !existingRequest.getStatus().equalsIgnoreCase("Save"))) {
				// General Info
				grantDTO.setHeadquarterName(ParamUtil.getString(resourceRequest, "headquarterName"));
				grantDTO.setUnitType(ParamUtil.getString(resourceRequest, "unitType"));

				// Salary & Allowance I
				grantDTO.setCaretakerAllowance(ParamUtil.getString(resourceRequest, "caretakerAllowance"));
				grantDTO.setRefreshmentAllowance(ParamUtil.getString(resourceRequest, "refreshmentAllowance"));
				grantDTO.setSalaries(ParamUtil.getString(resourceRequest, "salaries"));
				grantDTO.setOverTimeAllowance(ParamUtil.getString(resourceRequest, "overTimeAllowance"));
				grantDTO.setTelephone(ParamUtil.getString(resourceRequest, "telephone"));
				grantDTO.setElectricity(ParamUtil.getString(resourceRequest, "electricity"));
				grantDTO.setWaterCharges(ParamUtil.getString(resourceRequest, "waterCharges"));
				grantDTO.setContractualServices(ParamUtil.getString(resourceRequest, "contractualServices"));
				grantDTO.setDomesticTravellingExpenses(
						ParamUtil.getString(resourceRequest, "domesticTravellingExpenses"));
				grantDTO.setOfficeExpenses(ParamUtil.getString(resourceRequest, "officeExpenses"));

				// Salary & Allowance II
				grantDTO.setRent(ParamUtil.getString(resourceRequest, "rent"));
				grantDTO.setRates(ParamUtil.getString(resourceRequest, "rates"));
				grantDTO.setTaxes(ParamUtil.getString(resourceRequest, "taxes"));
				grantDTO.setTrainingActivities(ParamUtil.getString(resourceRequest, "trainingActivities"));
				grantDTO.setAtg(ParamUtil.getString(resourceRequest, "atg"));
				grantDTO.setWashingAndPolishingAllowance(
						ParamUtil.getString(resourceRequest, "washingAndPolishingAllowance"));
				grantDTO.setPetrolOilLubricants(ParamUtil.getString(resourceRequest, "petrolOilLubricants"));
				grantDTO.setMinorWork(ParamUtil.getString(resourceRequest, "minorWork"));
				grantDTO.setHonorariumToAno(ParamUtil.getString(resourceRequest, "honorariumToAno"));
				grantDTO.setOutfitMaintenanceAllowance(
						ParamUtil.getString(resourceRequest, "outfitMaintenanceAllowance"));
				grantDTO.setOthersIfAny(ParamUtil.getString(resourceRequest, "othersIfAny"));
				grantDTO.setGrantsInAid(ParamUtil.getString(resourceRequest, "grantsInAid"));
				grantDTO.setScholarshipOrStipend(ParamUtil.getString(resourceRequest, "scholarshipOrStipend"));
				grantDTO.setTotal(ParamUtil.getString(resourceRequest, "total"));
				grantDTO.setStatus(ParamUtil.getString(resourceRequest, "status"));

				// Audit Fields
				grantDTO.setUserId(themeDisplay.getUserId());
				grantDTO.setCreateDate(new Date());
				grantDTO.setModifiedDate(new Date());
			} else if (nccGrantRequestId > 0) {
				// Fetch existing data and only update remarks/status
				if (Validator.isNotNull(existingRequest) && existingRequest.getStatus().equalsIgnoreCase("Save")) {
					BeanPropertiesUtil.copyProperties(existingRequest, grantDTO);

					// Only update status & remarks from request
					grantDTO.setApprovalStatus(ParamUtil.getString(resourceRequest, "approvalStatus"));
					grantDTO.setRemarks(ParamUtil.getString(resourceRequest, "remarks"));
					grantDTO.setActualTotal(ParamUtil.getString(resourceRequest, "actualTotal"));
				}
			}

			LOGGER.info("NccGrant DTO populated: " + grantDTO);
		} catch (Exception e) {
			LOGGER.error("Error while setting NccGrantCommonDTO: ", e);
		}

		return grantDTO;
	}

}
