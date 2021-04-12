package io.turq.turq.service.interfaces;

import io.turq.turq.entities.UserEntity;

public interface IUserService {
    UserEntity findByEmail(String email);
    UserEntity save(String firstName, String lastName, String email, String password);
}
