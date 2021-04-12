package io.turq.turq.model.legislation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LegislationRequest {
    private String title;
    private String chapter;
    private String section;
    private String accomplishes;
    private String terms;
    private String purpose;
    private String provisions;
    private String exceptions;
    private String other;
    private long contestId;
}
