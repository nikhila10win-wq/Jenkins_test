package com.mhdsys.common.api.mobile;

import com.liferay.portal.kernel.json.JSONObject;

public interface UserSessionAPI {
  
	JSONObject createUserSessionId(long userId);
    boolean deleteSessionId(long userId, long sessionId);
    boolean validateSessionId(long userId, long sessionId);
}