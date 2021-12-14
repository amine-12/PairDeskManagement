package com.project.pairdesksystem.buinesslayer;

import com.project.pairdesksystem.datalayer.Feature;
import com.project.pairdesksystem.datalayer.FeatureDTO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface FeatureService {
    List<Feature> getAllFeatures();
    Feature createFeature(Feature feature);
    Feature getFeatureByFeatureId(int featureId) throws NotFoundException;
    FeatureDTO getFeatureDTOByFeatureId(int featureId) throws NotFoundException;

    List<FeatureDTO> getAllFeaturesDTO();
    FeatureDTO createFeatureDTO(FeatureDTO featureDTO);
}
