package com.project.pairdesksystem.presentationlayer;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureService;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import javassist.NotFoundException;
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
    @GetMapping("/api/{featureId}")
    public FeatureDTO findFeature(@PathVariable int featureId) throws NotFoundException {
        return featureService.getFeatureDTOByFeatureId(featureId);
    }

    @CrossOrigin
    @PutMapping( value = "/api/update/{featureId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public FeatureDTO updateFeature(@PathVariable int featureId, @RequestBody FeatureDTO featureRequest) throws NotFoundException {
        return featureService.updateFeatureWithDTO(featureId,featureRequest);
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

    @CrossOrigin
    @DeleteMapping("/api/{featureId}")
    public void deleteFeature(@PathVariable int featureId) throws NotFoundException {
        FeatureDTO featureDTO = featureService.getFeatureDTOByFeatureId(featureId);
        featureService.deleteFeature(featureId);
    }

}
