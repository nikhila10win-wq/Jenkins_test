package com.mhdsys.common.api.mobile;

import com.liferay.portal.kernel.json.JSONObject;

public interface FileAPI {
	
	public JSONObject getFileEncryptedData(String fileName,long folderId, long groupId);

}
