package com.project.pairdesksystem.datalayer.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer taskId;
    private Integer featureId;
    private String taskName;
    private String priority;
    private String status;
    private String description;
}
