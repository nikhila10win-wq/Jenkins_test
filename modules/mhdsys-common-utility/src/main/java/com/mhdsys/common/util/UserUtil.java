package com.mhdsys.common.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.SecureRandom;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = UserUtil.class)
public class UserUtil {
	private Log LOGGER = LogFactoryUtil.getLog(UserUtil.class);
	
	public String setUserPassword(long userId) {
		String password = "Mhdsy@1234";
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
			if(Validator.isNotNull(user)) {
				password = generateRandomPassword();
				user.setPassword(password);
				user.setAgreedToTermsOfUse(true);
				user.setPasswordReset(true);
				UserLocalServiceUtil.updateUser(user);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return password;
	}
	
	
	public String generateRandomPassword() {
	      // Character pools
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String allChars = upperCaseLetters + lowerCaseLetters + digits;

        // SecureRandom for strong randomness
        SecureRandom random = new SecureRandom();

        // First character: Always uppercase letter
        StringBuilder password = new StringBuilder();
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));

        // Remaining characters: Mix of upper, lower, and digits
        for (int i = 1; i < 8; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Convert to string and return
        return password.toString();
		
	}
}