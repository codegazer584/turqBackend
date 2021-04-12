package io.turq.turq.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponse {
    private final String jwttoken;
    private final String email;
}

