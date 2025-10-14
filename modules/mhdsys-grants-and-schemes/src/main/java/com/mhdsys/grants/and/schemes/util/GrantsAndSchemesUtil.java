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
import com.mhdsys.common.pojo.DistrictGrantSchemeCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.DistrictGrantScheme;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = GrantsAndSchemesUtil.class)

public class GrantsAndSchemesUtil {

	private static Log LOGGER = LogFactoryUtil.getLog(GrantsAndSchemesUtil.class);

	@Reference
	private static FileUploadUtil fileUploadUtil;

	public static DistrictGrantSchemeCommonDTO setDistrictGrantSchemeCommonDTO(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long districtGrantSchemeId) {
		LOGGER.info("Grants and Schemes Util ::::  " + districtGrantSchemeId);
		DistrictGrantSchemeCommonDTO grantSchemeCommonDTO = new DistrictGrantSchemeCommonDTO();
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

			if (isDeskOfficer || isDeputyDirector || isHOAdmin) {

				DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil
						.getDistrictGrantScheme(districtGrantSchemeId);

				grantSchemeCommonDTO.setDistrictGrantSchemeId(districtGrantSchemeId);
				grantSchemeCommonDTO.setCategory(districtGrantScheme.getCategory());
				grantSchemeCommonDTO.setSubCategory(districtGrantScheme.getSubCategory());
				grantSchemeCommonDTO.setProject(districtGrantScheme.getProject());
				grantSchemeCommonDTO.setType(districtGrantScheme.getType());
				grantSchemeCommonDTO.setDetails(districtGrantScheme.getDetails());
				grantSchemeCommonDTO.setLatitude(districtGrantScheme.getLatitude());
				grantSchemeCommonDTO.setLongitude(districtGrantScheme.getLongitude());
				grantSchemeCommonDTO.setGeoTagPhotos(districtGrantScheme.getGeoTagPhotos());
				grantSchemeCommonDTO.setDprDocument(districtGrantScheme.getDprDocument());
				grantSchemeCommonDTO.setSupportedDocuments(districtGrantScheme.getSupportedDocuments());
				grantSchemeCommonDTO.setUserId(districtGrantScheme.getUserId());
				grantSchemeCommonDTO.setStatus(districtGrantScheme.getStatus());
				grantSchemeCommonDTO.setCreateDate(districtGrantScheme.getCreateDate());
				grantSchemeCommonDTO.setModifiedDated(districtGrantScheme.getModifiedDated());

				if (isDeskOfficer || isHOAdmin) {
					grantSchemeCommonDTO.setHoRemarks(ParamUtil.getString(resourceRequest, "hoRemarks"));
					grantSchemeCommonDTO.setHoVerification(ParamUtil.getString(resourceRequest, "hoVerification"));
					grantSchemeCommonDTO.setStatus("Forwarded To Deputy Director");

				} else if (isDeputyDirector) {
					grantSchemeCommonDTO.setHoRemarks(districtGrantScheme.getHoRemarks());
					grantSchemeCommonDTO.setHoVerification(districtGrantScheme.getHoVerification());
					grantSchemeCommonDTO.setDdRemarks(ParamUtil.getString(resourceRequest, "ddRemarks"));
					grantSchemeCommonDTO.setDdVerification(ParamUtil.getString(resourceRequest, "ddVerification"));
					grantSchemeCommonDTO.setStatus("Application Reviewed");
				}

			} else {

				grantSchemeCommonDTO.setDistrictGrantSchemeId(districtGrantSchemeId);
				grantSchemeCommonDTO.setCategory(ParamUtil.getString(resourceRequest, "category"));
				grantSchemeCommonDTO.setSubCategory(ParamUtil.getString(resourceRequest, "subcategory"));
				grantSchemeCommonDTO.setProject(ParamUtil.getString(resourceRequest, "project"));
				grantSchemeCommonDTO.setType(ParamUtil.getString(resourceRequest, "type"));
				grantSchemeCommonDTO.setDetails(ParamUtil.getString(resourceRequest, "details"));
				grantSchemeCommonDTO.setLatitude(ParamUtil.getString(resourceRequest, "latitude"));
				grantSchemeCommonDTO.setLongitude(ParamUtil.getString(resourceRequest, "longitude"));
				grantSchemeCommonDTO.setUserId(themeDisplay.getUserId());
				grantSchemeCommonDTO.setStatus("Forwarded To Desk Officer");
				grantSchemeCommonDTO.setCreateDate(new Date());
				grantSchemeCommonDTO.setModifiedDated(new Date());

				// files upload
				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
						uploadPortletRequest);
				
				
				long dprDocumentFileEntryId = ParamUtil.getLong(resourceRequest, "dprDocumentFileEntryId");
				if (dprDocumentFileEntryId > 0) {
					grantSchemeCommonDTO.setDprDocument(dprDocumentFileEntryId);

				}else {
					if (uploadPortletRequest != null && uploadPortletRequest.getFile("dprDocument") != null) {
						long dprDocument = uploadFile(uploadPortletRequest, "dprDocument", "Grants And Schemes",
								serviceContext);
						LOGGER.info("dpr Document file: " + dprDocument);
						grantSchemeCommonDTO.setDprDocument(dprDocument);
					}
				}
				

				List<Long> actualGeoTagPhotosIds = new ArrayList<>();
				List<Long> actualSupportedDocumentsIds = new ArrayList<>();

				// Process existing files (kept in the UI)
				String[] existingGeoTagPhotos = ParamUtil.getParameterValues(resourceRequest, "existingGeoTagPhotos");
				String[] existingGeoTagPhotosIds = ParamUtil.getParameterValues(resourceRequest,
						"existingGeoTagPhotosIds");

				if (existingGeoTagPhotos != null && existingGeoTagPhotos.length > 0) {
					if (existingGeoTagPhotos.length == existingGeoTagPhotosIds.length) {

						for (int i = 0; i < existingGeoTagPhotos.length; i++) {
							String fileIdStr = existingGeoTagPhotosIds[i];
							LOGGER.info("Existing File - Name: " + ", ID: " + fileIdStr);

							try {
								long fileEntryId = Long.parseLong(fileIdStr);
								DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

								actualGeoTagPhotosIds.add(fileEntry.getFileEntryId());

							} catch (Exception e) {
								LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
							}
						}

						Method setGeoTagPhotos = grantSchemeCommonDTO.getClass().getMethod("setGeoTagPhotos",
								String.class);
						setGeoTagPhotos.invoke(grantSchemeCommonDTO, actualGeoTagPhotosIds.toString());
					}
				}

				// Process new actual geo tag photos
				if (uploadPortletRequest.getFile("actualGeoTagPhotos") != null) {
					File[] actualStorageFiles = uploadPortletRequest.getFiles("actualGeoTagPhotos");
					LOGGER.info("SIZE ::::   " + actualStorageFiles.length);
					String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
							"actualGeoTagPhotosNames");

					for (int i = 0; i < actualStorageFiles.length; i++) {
						File file = actualStorageFiles[i];
						String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
						LOGGER.info("File Name ::::  " + fileName);
						long fileEntryId = multipleFileUpload(uploadPortletRequest, "actualGeoTagPhotos",
								"Grants And Schemes", serviceContext, fileName, file);
						actualGeoTagPhotosIds.add(fileEntryId);
					}

					LOGGER.info("Geo Tag Photos photos: " + actualGeoTagPhotosIds.toString());
					Method setGeoTagPhotos = grantSchemeCommonDTO.getClass().getMethod("setGeoTagPhotos", String.class);
					setGeoTagPhotos.invoke(grantSchemeCommonDTO, actualGeoTagPhotosIds.toString());
				}

				// Process existing files (kept in the UI)
				String[] existingSupportedDocuments = ParamUtil.getParameterValues(resourceRequest,
						"existingSupportedDocuments");
				String[] existingSupportedDocumentsIds = ParamUtil.getParameterValues(resourceRequest,
						"existingSupportedDocumentsIds");

				if (existingSupportedDocuments != null && existingSupportedDocuments.length > 0) {
					if (existingSupportedDocuments.length == existingSupportedDocumentsIds.length) {

						for (int i = 0; i < existingSupportedDocuments.length; i++) {
							String fileIdStr = existingSupportedDocumentsIds[i];

							LOGGER.info("Existing File - Name: " + ", ID: " + fileIdStr);

							try {
								long fileEntryId = Long.parseLong(fileIdStr);
								DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

								actualSupportedDocumentsIds.add(fileEntry.getFileEntryId());

							} catch (Exception e) {
								LOGGER.error("Error processing existing file with ID: " + fileIdStr, e);
							}
						}

						Method setSupportedDocuments = grantSchemeCommonDTO.getClass()
								.getMethod("setSupportedDocuments", String.class);
						setSupportedDocuments.invoke(grantSchemeCommonDTO, actualSupportedDocumentsIds.toString());
					}
				}

				// Process new actual supported documents
				if (uploadPortletRequest.getFile("actualSupportedDocuments") != null) {
					File[] actualStorageFiles = uploadPortletRequest.getFiles("actualSupportedDocuments");
					String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest,
							"actualSupportedDocumentsNames");

					for (int i = 0; i < actualStorageFiles.length; i++) {
						File file = actualStorageFiles[i];
						String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
						LOGGER.info("File Name ::::  " + fileName);
						long fileEntryId = multipleFileUpload(uploadPortletRequest, "actualSupportedDocuments",
								"Grants And Schemes", serviceContext, fileName, file);
						actualSupportedDocumentsIds.add(fileEntryId);
					}

					LOGGER.info("Geo Tag Photos photos: " + actualSupportedDocumentsIds.toString());
					Method setSupportedDocuments = grantSchemeCommonDTO.getClass().getMethod("setSupportedDocuments",
							String.class);
					setSupportedDocuments.invoke(grantSchemeCommonDTO, actualSupportedDocumentsIds.toString());
				}
			}

		} catch (Exception e) {

		}
		return grantSchemeCommonDTO;

	}

	public DistrictGrantSchemeCommonDTO setDistrictGrantSchemeCommonDTO(DistrictGrantScheme districtGrantScheme,
			ThemeDisplay themeDisplay) {
		DistrictGrantSchemeCommonDTO districtGrantSchemeCommonDTO = new DistrictGrantSchemeCommonDTO();
		try {
			LOGGER.info("District Grant And Scheme obj: " + districtGrantScheme);
			BeanPropertiesUtil.copyProperties(districtGrantScheme, districtGrantSchemeCommonDTO);

			// dpr document
			if (districtGrantSchemeCommonDTO.getDprDocument() > 0) {
				DLFileEntry recieptFileEntry = DLFileEntryLocalServiceUtil
						.getFileEntry(districtGrantSchemeCommonDTO.getDprDocument());
				if (Validator.isNotNull(recieptFileEntry)) {
					String fileName = recieptFileEntry.getFileName();
					if (fileName.contains("_")) {
						int underscoreIndex = fileName.indexOf('_');
						String trimmedFilename = fileName.substring(underscoreIndex + 1);
						districtGrantSchemeCommonDTO.setDprDocumentName(trimmedFilename);
					}
					districtGrantSchemeCommonDTO.setDprDocumentURL(
							getPreviewURL(districtGrantSchemeCommonDTO.getDprDocument(), themeDisplay));
				}
			}

			// geo tag photos
			if (Validator.isNotNull(districtGrantSchemeCommonDTO.getGeoTagPhotos())) {
				String getGeoTagPhotosStr = districtGrantSchemeCommonDTO.getGeoTagPhotos();
				getGeoTagPhotosStr = getGeoTagPhotosStr.replaceAll("[\\[\\] ]", "");
				String[] getGeoTagPhotosIds = getGeoTagPhotosStr.split(",");

				List<String> geoTagPhotosURLs = new ArrayList<>();
				List<String> geoTagPhotosNames = new ArrayList<>();
				List<String> geoTagPhotosEntryIds = new ArrayList<>(); // List for file entry IDs as Strings

				for (String getGeoTagPhotosIdStr : getGeoTagPhotosIds) {
					try {
						long getGeoTagPhotoId = Long.parseLong(getGeoTagPhotosIdStr.trim());
						LOGGER.info("Geo Tag Photo Id ::: " + getGeoTagPhotoId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(getGeoTagPhotoId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							geoTagPhotosNames.add(fileName);
							geoTagPhotosURLs.add(getPreviewURL(getGeoTagPhotoId, themeDisplay));
							geoTagPhotosEntryIds.add(String.valueOf(getGeoTagPhotoId)); // Add ID as String
							LOGGER.info("URL : " + geoTagPhotosURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing Photo ID: " + getGeoTagPhotosIdStr, e);
					}
				}
				districtGrantSchemeCommonDTO.setGeoTagPhotosNames(geoTagPhotosNames);
				districtGrantSchemeCommonDTO.setGeoTagPhotosURLs(geoTagPhotosURLs);
				districtGrantSchemeCommonDTO.setGeoTagPhotosIds(geoTagPhotosEntryIds);
			}

			// supported documents
			if (Validator.isNotNull(districtGrantSchemeCommonDTO.getSupportedDocuments())) {
				String supportedDocumentStr = districtGrantSchemeCommonDTO.getSupportedDocuments();
				supportedDocumentStr = supportedDocumentStr.replaceAll("[\\[\\] ]", "");
				String[] supportedDocumentIds = supportedDocumentStr.split(",");

				List<String> supportedDocumentURLs = new ArrayList<>();
				List<String> supportedDocumentNames = new ArrayList<>();
				List<String> supportedDocumentEntryIds = new ArrayList<>(); // List for file entry IDs as Strings

				for (String supportedDocumentIdStr : supportedDocumentIds) {
					try {
						long supportedDocumentId = Long.parseLong(supportedDocumentIdStr.trim());
						LOGGER.info("Supported Document Id ::: " + supportedDocumentId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(supportedDocumentId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							LOGGER.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							supportedDocumentNames.add(fileName);
							supportedDocumentURLs.add(getPreviewURL(supportedDocumentId, themeDisplay));
							supportedDocumentEntryIds.add(String.valueOf(supportedDocumentId)); // Add ID as String
							LOGGER.info("URL : " + supportedDocumentURLs.size());
						}
					} catch (PortalException e) {
						LOGGER.error("Error processing Photo ID: " + supportedDocumentIdStr, e);
					}
				}
				districtGrantSchemeCommonDTO.setSupportedDocumentsNames(supportedDocumentNames);
				districtGrantSchemeCommonDTO.setSupportedDocumentsIds(supportedDocumentEntryIds);
				districtGrantSchemeCommonDTO.setSupportedDocumentsURLs(supportedDocumentURLs);
			}

			LOGGER.info("District Grant Scheme Common DTO : " + districtGrantSchemeCommonDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return districtGrantSchemeCommonDTO;
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

	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private UserLocalService userLocalService;
	@Reference
	private GroupLocalService groupLocalService;
	@Reference
	private RoleLocalService roleLocalService;
}
