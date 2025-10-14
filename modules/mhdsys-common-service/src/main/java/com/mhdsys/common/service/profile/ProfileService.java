package com.mhdsys.common.service.profile;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.profile.ProfileAPI;
import com.mhdsys.common.api.profile.UserIdentifiable;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.service.ProfileLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ProfileAPI.class)
public class ProfileService implements ProfileAPI{
	private static Log LOGGER = LogFactoryUtil.getLog(ProfileService.class);

	@Reference private ProfileLocalService profileLocalService;
	
	private Profile getOrCreateProfileInstance(long userId) {
		return profileLocalService.getByUserId(userId).orElse(profileLocalService.createProfile(CounterLocalServiceUtil.increment(Profile.class.getName())));
	}
	
	@Override
	public <T extends UserIdentifiable> T updateProfileInformation(T dto, long userId) {
	    if (Validator.isNull(dto) || userId == 0) {
	        return null;
	    }

	    Profile profile = getOrCreateProfileInstance(userId);
	    BeanPropertiesUtil.copyProperties(dto, profile);
	    profile = profileLocalService.updateProfile(profile);
	    BeanPropertiesUtil.copyProperties(profile, dto);

	    return dto;
	}

	@Override
	public <T extends UserIdentifiable> T getByUserId(T dto, long userId) {
		LOGGER.info("profileLocalService.getByUserId(userId): "+profileLocalService.getByUserId(userId));
	    return profileLocalService.getByUserId(userId)
	            .map(profile -> {
	                BeanPropertiesUtil.copyProperties(profile, dto);
	                return dto;
	            })
	            .orElse(null);
		}

}
