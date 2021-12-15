package com.project.pairdesksystem;

import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class TaskAPITests {
    @Autowired
    private TaskRepository taskRepo;


    @BeforeEach
    public void setUpDb(){

//        Feature f1 = new Feature();
//        f1.setId(12345);
//        f1.setFeatureName("my feature");

        Task t1 = new Task();
        t1.setId(1);
        t1.setTaskName("MyTask");
        t1.setDescription("Some task here");
        t1.setPriority("MEDIUM");
        t1.setFeatureId(1);
        taskRepo.save(t1);
        Task t2 = new Task();


        t2.setId(2);
        t2.setTaskName("MyTask2");
        t2.setDescription("Some task here2");
        t2.setPriority("LOW");
        t2.setFeatureId(1);
        taskRepo.save(t2);
    }

    @Test
    void saveTask() {
        assertEquals( taskRepo.count(), 2);
    }

    @Test
    void get_All_Tasks(){
        taskRepo.findAll();
        assertEquals(taskRepo.findAll().size(), 2);
    }

    @Test
    void get_Task_By_Id(){
        Task t = new Task(1,12345,9836,"taskName","HIGH","TODO","description");
        taskRepo.save(t);
        assertEquals(t.getTaskId(), 12345);

    }
}