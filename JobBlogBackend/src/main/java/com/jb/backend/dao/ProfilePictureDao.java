package com.jb.backend.dao;

import com.jb.backend.model.ProfilePicture;

public interface ProfilePictureDao {
		void uploadProfilePicture(ProfilePicture profilePicture);
		ProfilePicture getProfilePicture(String mobileNumber);
}
