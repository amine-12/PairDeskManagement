package com.project.pairdesksystem;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureService;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PairdesksystemApplicationTests {

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
            f1.setUser_id(1);
            f1.setProgress(2);
        featRep.save(f1);
        Feature f2 = new Feature();

            f2.setId(2);
            f2.setFeatureName("MyFeature2");
            f2.setDeadline(date);
            f2.setDescription("Some Feature here");
            f2.setPriority("MEDIUM");
            f2.setUser_id(1);
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
}
