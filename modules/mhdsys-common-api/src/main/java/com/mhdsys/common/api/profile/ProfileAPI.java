package com.mhdsys.common.api.profile;

public interface ProfileAPI {
	<T extends UserIdentifiable> T updateProfileInformation(T dto, long userId);
	
	<T extends UserIdentifiable> T getByUserId(T dto, long userId);
}
