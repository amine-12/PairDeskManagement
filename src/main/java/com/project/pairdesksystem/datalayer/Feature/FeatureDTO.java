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
    private Integer userId;
    private String featureName;
    private String priority;
    private String description;
    private Date deadline;
}
