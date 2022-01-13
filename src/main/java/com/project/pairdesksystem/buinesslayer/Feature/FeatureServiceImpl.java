package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class FeatureServiceImpl implements FeatureService{
    private final FeaturesRepository featuresRepository;
    private final FeatureMapper featureMapper;
    private final TaskRepository taskRepository;

    @Override
    public List<Feature> getAllFeatures() {
       return featuresRepository.findAll();
    }

    @Override
    public Feature createFeature(Feature feature) {
        return featuresRepository.save(feature);
    }

    @Override
    public List<Feature> getFeaturesByUserId(int userId) throws NotFoundException {
        return featuresRepository.findAllByUserId(userId);
    }

    @Override
    public List<FeatureDTO> getFeatureDTOByUserId(int userId) throws NotFoundException {
        List<Feature> feature = getFeaturesByUserId(userId);
        return featureMapper.featureListToFeatureDTOList(feature);
    }


    @Override
    public Feature getFeatureByFeatureId(int featureId) throws NotFoundException {
        return featuresRepository.findByFeatureId(featureId).orElseThrow(() -> new NotFoundException("No feature found for featureId: " + featureId));
    }

    @Override
    public FeatureDTO getFeatureDTOByFeatureId(int featureId) throws NotFoundException {
        Feature feature = getFeatureByFeatureId(featureId);
        FeatureDTO featureDTO = featureMapper.featureToFeatureDTO(feature);
        return featureDTO;
    }

    @Override
    public List<FeatureDTO> getAllFeaturesDTO() {
        List<Feature> vetList = getAllFeatures();
        return featureMapper.featureListToFeatureDTOList(vetList);
    }

    @Override
    public FeatureDTO createFeatureDTO(FeatureDTO featureDTO) {
        Feature feature = featureMapper.featureDTOToFeature(featureDTO);
        return featureMapper.featureToFeatureDTO(createFeature(feature));
    }

    @Override
    public Feature updateFeature(Feature feature, Feature updateFeature) {
        if (updateFeature.getFeatureName() != null && !updateFeature.getFeatureName().isEmpty()) {
            feature.setFeatureName(updateFeature.getFeatureName());
        }
        if (updateFeature.getDescription() != null && !updateFeature.getDescription().isEmpty()) {
            feature.setDescription(updateFeature.getDescription());
        }
        if (updateFeature.getPriority() != null && !updateFeature.getPriority().isEmpty()) {
            feature.setPriority(updateFeature.getPriority());
        }
        if (updateFeature.getDeadline() != null) {
            feature.setDeadline(updateFeature.getDeadline());
        }
        if (updateFeature.getUserId() != null) {
            feature.setUserId(updateFeature.getUserId());
        }

        return featuresRepository.save(feature);
    }

    @Override
    public FeatureDTO updateFeatureWithDTO(int featureId, FeatureDTO featureDTO) throws NotFoundException {
        featureDTO.setFeatureId(featureId);
        Feature feature = getFeatureByFeatureId(featureId);
        Feature featureUpdate = featureMapper.featureDTOToFeature(featureDTO);
        updateFeature(feature, featureUpdate);
        return featureMapper.featureToFeatureDTO(updateFeature(feature, featureUpdate));
    }

    @Override
    public void deleteFeature(int featureId) {
        log.debug("Feature object is deleted with this id: " + featureId);
        Feature feature = featuresRepository.findByFeatureId(featureId).orElse(new Feature());
        if(feature.getFeatureId() != null)
            featuresRepository.delete(feature);

        log.debug("feature deleted");
    }

    @Override
    public double getFeatureProgress(int featureId) {

        List<Task> taskList=taskRepository.findAllByFeatureId(featureId);
        double totalTaskSize=taskList.size();
        int iterator=0;
        double totalCompleteTasks=0;

        while(iterator<totalTaskSize){
            if(taskList.get(iterator).getStatus().equals("DONE")){
                totalCompleteTasks++;
            }
            iterator++;
        }
        //System.out.println(completedTask + " / " + totalTasks);
        if(totalTaskSize ==0)
            return 0;
        return (totalCompleteTasks/totalTaskSize)*100;
    }


}
