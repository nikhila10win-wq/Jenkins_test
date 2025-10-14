package com.mhdsys.common.api.masters;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author adhik
 */
public interface AllMasterCommonApi {
   JSONObject getAllMasterData(String userId,String source);
}
