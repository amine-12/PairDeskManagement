package com.project.pairdesksystem.buinesslayer.Task;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureMapper;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskDTO;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByFeatureId(int featureId) throws NotFoundException {
        return taskRepository.findAllByFeatureId(featureId);
    }

    @Override
    public List<TaskDTO> getTaskDTOByFeatureId(int taskId) throws NotFoundException {
        List<Task> task = getTasksByFeatureId(taskId);
        return taskMapper.taskListToTaskDTOList(task);
    }

    @Override
    public List<TaskDTO> getAllTasksDTO() {
        return taskMapper.taskListToTaskDTOList(getAllTasks());
    }

    @Override
    public TaskDTO createTaskDTO(TaskDTO taskDTO) {
        Task task = taskMapper.taskDTOToTask(taskDTO);
        return taskMapper.taskToTaskDTO(createTask(task));
    }
}
