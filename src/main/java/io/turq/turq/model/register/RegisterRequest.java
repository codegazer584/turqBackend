package io.turq.turq.model.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

