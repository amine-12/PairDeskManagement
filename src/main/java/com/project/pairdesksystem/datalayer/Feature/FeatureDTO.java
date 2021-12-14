package com.project.pairdesksystem.datalayer.Feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDTO {
    private Integer featureId;
    private Integer task_id;
    private Integer user_id;
    private String featureName;
    private String priority;
    private int progress;
    private String description;
    private Date deadline;
}
