package io.turq.turq.model.contest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContestUpdateRequest {
    private long id;
    private String title;
    private Date endDate;
    private int prize;
    private String rules;
    private String criteria;
    private String description;
}
