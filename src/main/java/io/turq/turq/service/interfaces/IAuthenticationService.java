package io.turq.turq.service.interfaces;

import io.turq.turq.entities.UserEntity;
import io.turq.turq.model.login.LoginRequest;
import io.turq.turq.model.login.LoginResponse;
import io.turq.turq.model.register.RegisterRequest;
import io.turq.turq.model.register.RegisterResponse;

public interface IAuthenticationService {
    RegisterResponse register(RegisterRequest req);
    LoginResponse login(LoginRequest req);
}
