package io.turq.turq.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LegislationNotFoundException extends RuntimeException {
    private String msg;
}
