package io.turq.turq.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserBadRequestException extends RuntimeException {
    private String msg;
}
