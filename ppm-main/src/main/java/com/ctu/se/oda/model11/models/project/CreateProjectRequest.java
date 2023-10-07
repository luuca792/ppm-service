package com.ctu.se.oda.model11.models.project;

import com.ctu.se.oda.model11.models.IModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateProjectRequest implements IModel {
    private String name;
    private Double duration;
    private String creatorId;
}
