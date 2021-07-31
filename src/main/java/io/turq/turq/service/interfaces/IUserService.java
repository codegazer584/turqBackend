package io.turq.turq.service.interfaces;

import io.turq.turq.entities.UserEntity;

import io.turq.turq.model.user.*;

public interface IUserService {
    UserEntity findByEmail(String email);
    UserEntity save(String firstName, String lastName, String email, String password, Boolean admin);
    UserProfileResponse getName(String email);
    UserUpdateResponse updateName(UserUpdateRequest req, String email, String token);
    UserEntity getActivity(String email);
   // UserEntity save(String firstName, String lastName, String email, String password, Boolean admin);
}
