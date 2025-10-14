package com.mhdsys.common.api.authentication;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author adhik
 */
public interface AuthenticationCommonApi{
	JSONObject authenticateWithPassword(String userId, String password, String source);
}