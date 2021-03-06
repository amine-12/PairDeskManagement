package com.project.pairdesksystem;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureService;
import com.project.pairdesksystem.buinesslayer.Task.TaskService;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class FeatureServiceTests {
    @Autowired
    private FeatureService fservice;
    @Autowired
    private FeaturesRepository featureRepo;
    @Autowired
    public TaskService tservice;
    @Autowired
    public TaskRepository taskRepo;

    @Test
    void delete_Feature_with_Service() throws Exception {
        featureRepo.deleteAll();

        Feature f1 = new Feature();
        Date date = new Date();

        f1.setId(10);
        f1.setFeatureId(20);
        f1.setFeatureName("MysFeatureTest");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);

        boolean foundAndDeleted = false;

        fservice.createFeature(f1);
        List x = fservice.getAllFeatures();

        fservice.deleteFeature(f1.getFeatureId());
        List v = fservice.getAllFeatures();

        //the size of the new list should be smaller than the list with the stored feature inside
        if (v.size() < x.size()) {
            foundAndDeleted = true;
        }
        assertTrue(foundAndDeleted);
    }

    @Test
    void create_Feature_With_Service() throws NotFoundException {

        Feature f1 = new Feature();
        Date date = new Date();
        String resultName = "MysFeatureTest";
        int testUserId = 1;

        f1.setId(10);
        f1.setFeatureId(20);
        f1.setFeatureName("MysFeatureTest");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);

        fservice.createFeature(f1);

        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getFeatureName(), resultName);
        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getUserId(), testUserId);
    }

    @Test
    void update_Feature_With_Service() throws NotFoundException {
        Feature f1 = new Feature();
        Date date = new Date();

        int testUserId = 2;
        int id = 1291;

        f1.setId(id);
        f1.setFeatureId(20);
        f1.setFeatureName("MysFeatureTest");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);

        Feature f2 = new Feature();

        f2.setId(id);
        f2.setFeatureId(64);
        f2.setFeatureName("MysFeatureTest2");
        f2.setDeadline(date);
        f2.setDescription("Some Feature here2");
        f2.setPriority("HIGH");
        f2.setUserId(2);

        fservice.updateFeature(f1, f2);

        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getFeatureName(), "MysFeatureTest2");
        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getUserId(), testUserId);
    }

    @Test
    void returnDateListOfLateFeatures() throws NotFoundException{
        Feature f1 = new Feature();
        Date date = new Date();

        int testUserId = 2;
        int id = 1291;

        f1.setId(id);
        f1.setFeatureId(20);
        f1.setFeatureName("MysFeatureTest");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);

        Feature f2 = new Feature();

        f2.setId(id);
        f2.setFeatureId(64);
        f2.setFeatureName("MysFeatureTest2");
        f2.setDeadline(date);
        f2.setDescription("Some Feature here2");
        f2.setPriority("HIGH");
        f2.setUserId(2);

        fservice.updateFeature(f1, f2);

        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getFeatureName(), "MysFeatureTest2");
        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getUserId(), testUserId);

        assertTrue(fservice.getLateFeatures()!=null);


    }
    @Test
    void update_Feature_Progress() throws NotFoundException {

        featureRepo.deleteAll();
        taskRepo.deleteAll();
        // I create a feature then a task for that feature
        //since the feature has only one task with the status "DONE"
        //it should return 100 (100%) (as you can tell by the last line of this test)
        Feature f1 = new Feature();
        Date date = new Date();

        int id = 1111;

        f1.setId(id);
        f1.setFeatureId(20);
        f1.setFeatureName("MysFeatureTest");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);
        featureRepo.save(f1);
        Task t = new Task(12, 12345, f1.getFeatureId(), "test1", "HIGH", "DONE", "desc");


        taskRepo.save(t);

        assertEquals(fservice.getFeatureByFeatureId(f1.getFeatureId()).getFeatureName(), "MysFeatureTest");


        System.out.println(t.getTaskId());


        assertEquals(tservice.getTaskByTaskId(t.getTaskId()).getStatus(), "DONE");
        System.out.println(fservice.getFeatureProgress(f1.getFeatureId()));

        assertEquals(fservice.getFeatureProgress(f1.getFeatureId()), 100);

        featureRepo.deleteAll();
        taskRepo.deleteAll();
    }
}
