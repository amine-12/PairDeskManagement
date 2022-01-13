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
import java.util.ListIterator;

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

    @Override
    public TaskDTO getTaskDTOByTaskId(int taskId) throws NotFoundException {
        Task task = getTaskByTaskId(taskId);
        TaskDTO taskDTO = taskMapper.taskToTaskDTO(task);
        return taskDTO;
    }

    @Override
    public void deleteTask(int taskId) {
        log.debug("task object is deleted with this id: " + taskId);
        Task task = taskRepository.findByTaskId(taskId).orElse(new Task());
        if(task.getTaskId() != null)
            taskRepository.delete(task);

        log.debug("task deleted");
    }

    @Override
    public Task updateTask(Task task, Task updateTask) {
        if (updateTask.getTaskName() != null && !updateTask.getTaskName().isEmpty()) {
            task.setTaskName(updateTask.getTaskName());
        }
        if (updateTask.getDescription() != null && !updateTask.getDescription().isEmpty()) {
            task.setDescription(updateTask.getDescription());
        }
        if (updateTask.getPriority() != null && !updateTask.getPriority().isEmpty()) {
            task.setPriority(updateTask.getPriority());
        }
        if (updateTask.getStatus() != null) {
            task.setStatus(updateTask.getStatus());
        }

        return taskRepository.save(task);
    }

    @Override
    public TaskDTO updateTaskWithDTO(int taskId, TaskDTO taskDTO) throws NotFoundException {
        taskDTO.setTaskId(taskId);
        Task task = getTaskByTaskId(taskId);
        Task taskUpdate = taskMapper.taskDTOToTask(taskDTO);
        updateTask(task, taskUpdate);
        return taskMapper.taskToTaskDTO(updateTask(task, taskUpdate));
    }

    @Override
    public Task getTaskByTaskId(int taskId) throws NotFoundException {
        return taskRepository.findByTaskId(taskId).orElseThrow(() -> new NotFoundException("No task found for taskId: " + taskId));
    }
}
