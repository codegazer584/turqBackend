package io.turq.turq.model.register;

import io.turq.turq.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class RegisterResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final UserEntity user;
}

