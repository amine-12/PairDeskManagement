package com.project.pairdesksystem.buinesslayer.Task;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task createTask(Task task);
    List<Task> getTasksByFeatureId(int featureId) throws NotFoundException;
    List<TaskDTO> getTaskDTOByFeatureId(int featureId) throws NotFoundException;

    List<TaskDTO> getAllTasksDTO();
    TaskDTO createTaskDTO(TaskDTO taskDTO);
}
