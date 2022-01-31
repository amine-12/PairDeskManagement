package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Task.Task;
import javassist.NotFoundException;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public interface FeatureService {
    List<Feature> getAllFeatures();
    Feature createFeature(Feature feature);
    Feature getFeatureByFeatureId(int featureId) throws NotFoundException;
    FeatureDTO getFeatureDTOByFeatureId(int featureId) throws NotFoundException;
    List<Feature> getFeaturesByUserId(int userId) throws NotFoundException;
    List<FeatureDTO> getFeatureDTOByUserId(int userId) throws NotFoundException;

    List<FeatureDTO> getAllFeaturesDTO();
    FeatureDTO createFeatureDTO(FeatureDTO featureDTO);

    Feature updateFeature(Feature feature, Feature updateFeature);
    FeatureDTO updateFeatureWithDTO(int featureId, FeatureDTO featureDTO) throws NotFoundException;

    void deleteFeature(int featureId);

    double getFeatureProgress(int featureId);

    List<FeatureDTO> getAllFeaturesDTOCompleted();

    List<FeatureDTO> getAllFeaturesDTOCompletedByUserId(int userId);

    List<Feature> getLateFeatures();

    String getUserNameByUserId(int userId);

}
