package io.turq.turq.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class errorResponse {
    String error;
    String detail;
}
