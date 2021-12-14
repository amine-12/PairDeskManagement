package com.project.pairdesksystem.buinesslayer.Task;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mappings(
            {
                    @Mapping(target = "id", ignore = true),
            }
    )
    Task taskDTOToTask(TaskDTO taskDTO);
    TaskDTO taskToTaskDTO(Task task);

    List<Task> taskDTOListToTaskList(List<TaskDTO> taskDTOList);

    List<TaskDTO> taskListToTaskDTOList(List<Task> taskList);
}
