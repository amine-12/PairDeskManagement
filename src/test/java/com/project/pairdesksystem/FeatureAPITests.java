package com.project.pairdesksystem;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FeatureAPITests {
    @Autowired
    private FeaturesRepository featRep;


    @BeforeEach
    public void setUpDb(){

        Feature f1 = new Feature();
        Date date = new Date();


        f1.setId(1);
        f1.setFeatureName("MyFeature");
        f1.setDeadline(date);
        f1.setDescription("Some Feature here");
        f1.setPriority("MEDIUM");
        f1.setUserId(1);
        f1.setProgress(2);
        featRep.save(f1);
        Feature f2 = new Feature();

        f2.setId(2);
        f2.setFeatureName("MyFeature2");
        f2.setDeadline(date);
        f2.setDescription("Some Feature here");
        f2.setPriority("MEDIUM");
        f2.setUserId(1);
        f2.setProgress(2);
        featRep.save(f2);
    }

    @Test
    void saveFeatures() {
        assertEquals( featRep.count(), 2);
    }

    @Test
    void get_All_Features(){
        featRep.findAll();
        assertEquals(featRep.findAll().size(), 2);
    }

    @Test
    void get_All_Features_By_User_Id(){
        featRep.findAllByUserId(99404);
        assertEquals(featRep.findAll().size(), 2);
    }

    @Test
    void get_Features_By_Id(){
        Feature feat = featRep.findById(1).get();
        assertEquals(feat.getFeatureName(), "MyFeature");
    }
    @Test
    void get_Feature_Progress(){
        featRep.findAll();
        Feature feat = featRep.findById(1).get();
        assertEquals(feat.getFeatureName(), "MyFeature");

        assertEquals(feat.getProgress(), 2);

    }

}
