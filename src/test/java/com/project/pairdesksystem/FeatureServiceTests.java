package com.project.pairdesksystem;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureService;
import com.project.pairdesksystem.buinesslayer.Task.TaskService;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)


public class FeatureServiceTests {
    @Autowired
    private FeatureService fservice;
    @Autowired
    private FeaturesRepository featureRepo;


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
        f1.setUser_id(1);
        f1.setProgress(2);



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
}