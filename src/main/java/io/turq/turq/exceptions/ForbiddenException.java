package io.turq.turq.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForbiddenException extends RuntimeException {
    private String msg;
}
