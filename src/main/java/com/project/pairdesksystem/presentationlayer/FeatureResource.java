package com.project.pairdesksystem.presentationlayer;

import com.project.pairdesksystem.buinesslayer.FeatureService;
import com.project.pairdesksystem.datalayer.Feature;
import com.project.pairdesksystem.datalayer.FeatureDTO;
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

    @CrossOrigin
    @GetMapping("/api/all")
    public List<FeatureDTO> getFeatureList() {
        return featureService.getAllFeaturesDTO();
    }

    @CrossOrigin
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/api/add"
    ) // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public FeatureDTO addNewFeature (@RequestBody FeatureDTO feature) {
        LOG.debug("THIS IS THE ID: " + feature.getFeatureId());
        return featureService.createFeatureDTO(feature);
    }
}
