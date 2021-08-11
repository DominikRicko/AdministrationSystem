package com.samuraiDigital.adminsystem.api.profile;

import com.samuraiDigital.adminsystem.data.model.UserSecurityDetails;

public interface ApiProfileService {

	ApiProfileResource convertToResource(UserSecurityDetails user);

	ApiProfileResource updateUserProfile(ApiProfileResource user);

}
