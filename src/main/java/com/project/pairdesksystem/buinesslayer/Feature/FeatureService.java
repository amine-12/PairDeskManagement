package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import javassist.NotFoundException;

import java.util.List;

public interface FeatureService {
    List<Feature> getAllFeatures();
    Feature createFeature(Feature feature);
    Feature getFeatureByFeatureId(int featureId) throws NotFoundException;
    FeatureDTO getFeatureDTOByFeatureId(int featureId) throws NotFoundException;

    List<FeatureDTO> getAllFeaturesDTO();
    FeatureDTO createFeatureDTO(FeatureDTO featureDTO);

    Feature updateFeature(Feature feature, Feature updateFeature);
    FeatureDTO updateFeatureWithDTO(int featureId, FeatureDTO featureDTO) throws NotFoundException;

    public void deleteFeature(int featureId);
}
