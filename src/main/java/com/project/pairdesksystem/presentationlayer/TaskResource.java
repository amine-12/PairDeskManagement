package com.project.pairdesksystem.presentationlayer;

import com.project.pairdesksystem.buinesslayer.Task.TaskService;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Task.TaskDTO;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/features")
@RestController
public class TaskResource {
    private final TaskService taskService;
    private static final Logger LOG = LoggerFactory.getLogger(FeatureResource.class);

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }


    @CrossOrigin
    @GetMapping("/api/tasks")
    public List<TaskDTO> getTaskList() {
        return taskService.getAllTasksDTO();
    }


    @CrossOrigin
    @GetMapping("/api/tasks/{featureId}")
    public List<TaskDTO> getTasksByFeatureId(@PathVariable int featureId) throws NotFoundException {
        List<TaskDTO> taskDTO = taskService.getTaskDTOByFeatureId(featureId);
        return taskDTO;
    }

    @CrossOrigin
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/api/task/add"
    ) // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO addNewTask (@RequestBody TaskDTO task) {
        LOG.debug("THIS IS THE ID: " + task.getTaskId());
        return taskService.createTaskDTO(task);
    }


    @CrossOrigin
    @DeleteMapping("/api/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId) throws NotFoundException {
        //TaskDTO taskDTO = taskService.getTaskDTOByFeatureId(taskId);
        taskService.deleteTask(taskId);
    }
}
