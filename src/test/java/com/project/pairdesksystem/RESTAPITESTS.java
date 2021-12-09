package com.project.pairdesksystem;
import static org.assertj.core.api.Assertions.assertThat;
import com.project.pairdesksystem.presentationlayer.FeatureResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RESTAPITESTS {

    @Autowired
    private FeatureResource featureController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(featureController).isNotNull();
    }
}