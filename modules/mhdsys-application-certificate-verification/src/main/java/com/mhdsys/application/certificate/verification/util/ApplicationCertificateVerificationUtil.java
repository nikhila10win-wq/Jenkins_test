package com.mhdsys.application.certificate.verification.util;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.schema.model.CertificateVerification;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.CertificateVerificationCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, service = ApplicationCertificateVerificationUtil.class)

public class ApplicationCertificateVerificationUtil {
	
	private Log LOGGER=LogFactoryUtil.getLog(ApplicationCertificateVerificationUtil.class);
	@Reference
	FileUploadUtil fileUploadUtil;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public CertificateVerificationCommonDTO setCertificateVerificationCommonDTO(ResourceRequest resourceRequest,ThemeDisplay themeDisplay) {
		
		CertificateVerificationCommonDTO commonDTO=new CertificateVerificationCommonDTO();
		try {
		commonDTO.setCertificateVerificationId(ParamUtil.getLong(resourceRequest, "certificateVerificationId"));
		
		commonDTO.setFirstName(ParamUtil.getString(resourceRequest, "firstName"));
		commonDTO.setLastName(ParamUtil.getString(resourceRequest, "lastName"));
		commonDTO.setMothersName(ParamUtil.getString(resourceRequest, "motherName"));
		commonDTO.setFathersName(ParamUtil.getString(resourceRequest, "fatherName"));
		commonDTO.setGender(ParamUtil.getInteger(resourceRequest, "gender"));
		commonDTO.setDateOfBirth(convertStringToDateFormat(ParamUtil.getString(resourceRequest, "dob")));

		commonDTO.setAadharNumber(ParamUtil.getString(resourceRequest, "aadhaarNumber"));
		commonDTO.setCertificate(ParamUtil.getString(resourceRequest, "certificate"));
		commonDTO.setApproveReject(ParamUtil.getBoolean(resourceRequest, "approveReject",false));
		commonDTO.setRemarks(ParamUtil.getString(resourceRequest, "remarks"));

		// Audit fields
		commonDTO.setUserId(themeDisplay.getUserId()); // or ParamUtil.getLong(...) if from form
		commonDTO.setCreateDate(new Date());
		commonDTO.setModifiedDate(new Date());
		
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadPortletRequest);

		List<Long> certificateIds = new ArrayList<>();

		// Check if files exist (safer than checking getFile() which might throw NPE)
		if (uploadPortletRequest.getFile("certificate") != null) {
			File[] actualStorageFiles = uploadPortletRequest.getFiles("certificate");
			String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
					"certificateNames");
			LOGGER.info("Files :"+actualStorageFiles);
			LOGGER.info("Files Name :"+actualStorageFileNames);
			

			for (int i = 0; i < actualStorageFiles.length; i++) {
				File file = actualStorageFiles[i];
				LOGGER.info("=============================File :"+file);
				String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
				LOGGER.info("Files Name :============================================================="+fileName);
				long fileEntryId = fileUploadUtil.multipleFileUpload(uploadPortletRequest, "certificate",
						CommonUtilityConstant.APPLICATION_CERTIFICATE_VERIFICATION_FOLDER, serviceContext, fileName, file);
				certificateIds.add(fileEntryId);
			}
            
			
			LOGGER.info("certificateIds: " + certificateIds.toString());
			Method setCertificate = commonDTO.getClass().getMethod("setCertificate",
					String.class);
			setCertificate.invoke(commonDTO, certificateIds.toString());
		}
		
		}
		catch (Exception e) {
			LOGGER.info("Error Comming in ApplicationCertificateVerificationUtil class"+ e.getMessage());
		}

		return commonDTO;
		}
	
	
	public CertificateVerificationCommonDTO setCertificateVerificationCommonDTO(
	        CertificateVerification certificateVerification, ThemeDisplay themeDisplay) {

	    CertificateVerificationCommonDTO commonDTO = new CertificateVerificationCommonDTO();

	    try {
	    	BeanPropertiesUtil.copyProperties(certificateVerification, commonDTO);
           
				commonDTO.setDateOfBirthStr(
						(Validator.isNotNull(certificateVerification.getDateOfBirth())) ? formatter.format(certificateVerification.getDateOfBirth()) : "");
				LOGGER.info("Date Of Birth    :::::   "+certificateVerification.getDateOfBirth());
			

	    	
	        if (Validator.isNotNull(certificateVerification.getCertificate())) {
				String certificateStr = certificateVerification.getCertificate();
				certificateStr = certificateStr.replaceAll("[\\[\\] ]", "");
				String[] certificateIds = certificateStr.split(",");

				List<String> certificateURLs = new ArrayList<>();
				List<String> certificateNames = new ArrayList<>();
				List<String> certificateEntryIds = new ArrayList<>(); // List for file entry IDs as Strings

				for (String certificateIdStr : certificateIds) {
					try {
						long certificateId = Long.parseLong(certificateIdStr.trim());
						LOGGER.info("actualPhoto Id ::: " + certificateId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(certificateId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							certificateNames.add(fileName);
							certificateURLs.add(fileUploadUtil.getPreviewURL(certificateId, themeDisplay));
							certificateEntryIds.add(String.valueOf(certificateId)); // Add ID as String
							LOGGER.info("URL : " + certificateURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing Certificate ID: " + certificateIdStr, e);
					}
				}
				commonDTO.setCertificateURLs(certificateURLs);
				commonDTO.setCertificateNames(certificateNames);
				commonDTO.setCertificateEntryIds(certificateEntryIds); // Set the IDs in DTO
			}
	        
	        
	        
	    } catch (Exception e) {
	        LOGGER.info("Error coming in ApplicationCertificateVerificationUtil class: " + e.getMessage());
	    }

	    return commonDTO;
	}
	
	public final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public Date convertStringToDateFormat(String date) {
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
