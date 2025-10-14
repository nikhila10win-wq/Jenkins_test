package com.mhdsys.common.service.mobile;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.mobile.EncryptAPI;
import com.mhdsys.common.api.mobile.FileAPI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {},
		service = FileAPI.class
		)
public class FileService implements FileAPI{

	private static final Log LOGGER = LogFactoryUtil.getLog(FileService.class);
	@Override
	public JSONObject getFileEncryptedData(String fileName,long folderId, long groupId) {
		LOGGER.info("fileName:::" + fileName);
		if(Validator.isNotNull(fileName)) {
			JSONObject documentNameAndInputStream = getDocumentNameAndInputStream(fileName,folderId,groupId);
			if(Validator.isNotNull(documentNameAndInputStream.getString("name", StringPool.BLANK))) {
				return documentNameAndInputStream;
			}
		}
		return null;
	}

	private JSONObject getDocumentNameAndInputStream(String fileName,long folderId,long groupId) {
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("inputStream", StringPool.BLANK);
		responseJson.put("name", StringPool.BLANK);
		FileEntry fileEntry = null;
		try {
			if (Validator.isNotNull(fileName)) {
				//fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
				fileEntry = DLAppServiceUtil.getFileEntryByFileName(groupId, folderId, fileName);
				if(Validator.isNotNull(fileEntry)) {
					FileVersion fileVersion = fileEntry.getFileVersion();
					InputStream inputStream = fileVersion.getContentStream(false);
					try {
						byte[] byteArray = FileUtil.getBytes(inputStream);
						byte[] bytes = Base64.getEncoder().encode(byteArray);
						responseJson.put("inputStream",encryptApi.encryptToString(new String(bytes)));
						responseJson.put("name", encryptApi.encryptToString(fileEntry.getFileName()));
						responseJson.put("extension", encryptApi.encryptToString(fileEntry.getExtension()));
					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return responseJson;
	}
	
	@Reference private EncryptAPI encryptApi;
}
