package com.mhdsys.common.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = FileUploadUtil.class)
public class FileUploadUtil {
	private Log LOGGER = LogFactoryUtil.getLog(FileUploadUtil.class);
	@Reference
	CompanyUtil companyUtil;

	public long multipleFileUpload(UploadPortletRequest uploadPortletRequest, String fileParamName, String folderName,
			ServiceContext serviceContext, String fileName, File file) {
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
					long repositoryId = companyUtil.getDefaultSiteGroupId();
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

	public long uploadFile(UploadPortletRequest uploadPortletRequest, String fileParamName, String folderName,
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
				long repositoryId = companyUtil.getDefaultSiteGroupId();
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

	public boolean validatePDFFileType(String fileName, String mimeType) {
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
	public boolean validateImageFileType(String fileName, String mimeType) {
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
				long repositoryId = companyUtil.getDefaultSiteGroupId();
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

}
