package io.turq.turq.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private String msg;
}
