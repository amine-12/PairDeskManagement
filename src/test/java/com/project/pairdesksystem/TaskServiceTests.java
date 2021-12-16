package com.project.pairdesksystem;

import com.project.pairdesksystem.buinesslayer.Task.TaskService;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)


public class TaskServiceTests {
    @Autowired
    private TaskService tservice;
    @Autowired
    private TaskRepository taskRepo;


    @Test
    void delete_Task_with_Service() throws Exception {

        int index = 0;
        boolean foundAndDeleted = false;

        taskRepo.deleteAll();

        Task t = new Task(12, 12345, 9836, "testTask", "HIGH", "TODO", "description");
        tservice.createTask(t);
        List x = tservice.getAllTasks();


        tservice.deleteTask(t.getTaskId());
        List v = tservice.getAllTasks();

        //the size of the new list should be smaller than the list with the stored task inside
        if (v.size() < x.size()) {
            foundAndDeleted = true;
        }
        assertTrue(foundAndDeleted);


    }

}
