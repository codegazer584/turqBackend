package io.turq.turq.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private String msg;
}
