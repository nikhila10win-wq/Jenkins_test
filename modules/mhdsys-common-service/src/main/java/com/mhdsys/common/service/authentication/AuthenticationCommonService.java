package com.mhdsys.common.service.authentication;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.authentication.AuthenticationCommonApi;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;

/**
 * @author adhik
 */
@Component(immediate = true, service = AuthenticationCommonApi.class)
public class AuthenticationCommonService implements AuthenticationCommonApi{
    private static Log LOGGER = LogFactoryUtil.getLog(AuthenticationCommonService.class);    	
	@Override
	public JSONObject authenticateWithPassword(String userId, String password, String source) {
		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();
		userJSONObject.put("dataStatus", true);
		User user = null;
		int result = 0;
		try {
			user = UserLocalServiceUtil.getUserByScreenName(PortalUtil.getDefaultCompanyId(), userId);
			if(Validator.isNotNull(user)) {
				result	= UserLocalServiceUtil.authenticateByScreenName(PortalUtil.getDefaultCompanyId(), userId, password,
						new HashMap<>(), new HashMap<>(), new HashMap<>());
			}else {
				userJSONObject.put("dataStatus", false);
				userJSONObject.put("dataErrorMsg","User doesn't exist.");
			}
		if(result == 1) {
			userJSONObject.put("userDetails", JSONFactoryUtil.createJSONObject(user.toString()));
		}	
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			userJSONObject.put("dataStatus", false);
			userJSONObject.put("dataErrorMsg","Error in authenticate user- " + e.getMessage());
		}
		
		return userJSONObject;
	}
	
}