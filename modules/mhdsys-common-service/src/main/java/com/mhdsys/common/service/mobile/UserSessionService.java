package com.mhdsys.common.service.mobile;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.mobile.EncryptAPI;
import com.mhdsys.common.api.mobile.UserSessionAPI;
import com.mhdsys.schema.model.MobileSession;
import com.mhdsys.schema.service.MobileSessionLocalServiceUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {},
		service = UserSessionAPI.class
		)

public class UserSessionService implements UserSessionAPI{
    private static Log LOGGER = LogFactoryUtil.getLog(UserSessionService.class);
	@Override
	public JSONObject createUserSessionId(long userId) {
		LOGGER.info("createUserSessionId");
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		User user = null;
		MobileSession session = null;
		try {
			try {
				user = UserLocalServiceUtil.getUser(userId);
			} catch (Exception e) {
				LOGGER.error("Exception in getting User::" + e.getMessage());
			}
			if(Validator.isNotNull(user)) {
				session = MobileSessionLocalServiceUtil.createMobileSession(CounterLocalServiceUtil.increment(MobileSession.class.getName()));
				session.setCreateDate(new Date());
				session.setUserId(userId);
				session.setStatus(1);
				session = MobileSessionLocalServiceUtil.updateMobileSession(session);
				jsonObject.put("userId", encryptApi.encryptToString(String.valueOf(userId)));
				jsonObject.put("sid", encryptApi.encryptToString(String.valueOf(session.getSid())));
				jsonObject.put("status", true);
				jsonObject.put("msg", "Success");
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "User doesn't exist");
			}
		} catch (Exception e) {
			LOGGER.error("Exception in create user session ID:" + e.getMessage());
		}
		return jsonObject;
	}

	@Override
	public boolean deleteSessionId(long userId, long sessionId) {
		boolean sessionDeleted = false;
		try {
			MobileSessionLocalServiceUtil.deleteMobileSession(sessionId);
			sessionDeleted = true;
		} catch (Exception e) {
			LOGGER.error("Exception in deleting sessionId::" + e.getMessage());
			sessionDeleted = false;
		}
		return sessionDeleted;
	}

	@Override
	public boolean validateSessionId(long userId, long sessionId) {
		MobileSession mobileSession = null;
		boolean isValidSession = false;
		try {
			mobileSession = MobileSessionLocalServiceUtil.getBySidAndUserId(sessionId, userId);
		} catch (Exception e) {
			LOGGER.error("Exception in validateSessionId::" + e.getMessage());
			isValidSession = false;
		}
		if(Validator.isNotNull(mobileSession)) {
			isValidSession = true;
		}
		return isValidSession;
	}
	
	@Reference private EncryptAPI encryptApi;
	
}