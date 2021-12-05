package com.project.pairdesksystem.presentationlayer;

import com.project.pairdesksystem.buinesslayer.FeatureService;
import com.project.pairdesksystem.datalayer.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/features")
@RestController
public class FeatureResource {
    @Autowired
    private final FeatureService featureService;
    private static final Logger LOG = LoggerFactory.getLogger(FeatureResource.class);

    public FeatureResource(FeatureService featureService) {
        this.featureService = featureService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/all")
    public Iterable<Feature> showResourcesVetList() {
        Iterable<Feature> featureList = featureService.getAllFeatures();
        return featureList;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/api/add"
    ) // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public Feature addNewFeature (@RequestBody Feature feature) {
        return featureService.createFeature(feature);
    }
}
