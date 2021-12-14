package com.project.pairdesksystem.buinesslayer;

import com.project.pairdesksystem.datalayer.Feature;
import com.project.pairdesksystem.datalayer.FeatureDTO;

import java.util.List;

public interface FeatureService {
    List<Feature> getAllFeatures();
    Feature createFeature(Feature feature);

    List<FeatureDTO> getAllFeaturesDTO();
    FeatureDTO createFeatureDTO(FeatureDTO featureDTO);
}
