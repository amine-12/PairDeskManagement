package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.User.repository.UserRepository;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeatureServiceImpl implements FeatureService{
    private final FeaturesRepository featuresRepository;
    private final FeatureMapper featureMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

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
        List<Feature> featureList = getAllFeatures();
        return featureMapper.featureListToFeatureDTOList(featureList);
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
        if(totalTaskSize ==0)
            return 0;
        return (totalCompleteTasks/totalTaskSize)*100;
    }

    @Override
    public List<FeatureDTO> getAllFeaturesDTOCompleted() {
        List<FeatureDTO> allFeatures = getAllFeaturesDTO();
        List<FeatureDTO> allFeaturesCompleted = new ArrayList<>();
        for (FeatureDTO feature : allFeatures){
            if(getFeatureProgress(feature.getFeatureId()) == 100){
                allFeaturesCompleted.add(feature);
            }
        }
        return allFeaturesCompleted;
    }

    @Override
    public List<FeatureDTO> getAllFeaturesDTOCompletedByUserId(int userId) {
        List<FeatureDTO> allFeaturesCompleted = getAllFeaturesDTOCompleted();
        List<FeatureDTO> allFeaturesCompletedByUserId = new ArrayList<>();
        for (FeatureDTO feature : allFeaturesCompleted){
            if(feature.getUserId() == userId){
                allFeaturesCompletedByUserId.add(feature);
            }
        }
        return allFeaturesCompletedByUserId;
    }

    @Override
    public List<Feature> getLateFeatures() {
        List<Feature> allFeatures =featuresRepository.findAll();

        Calendar cal_obj1 = Calendar.getInstance();
        Calendar cal_obj2 = Calendar.getInstance();


        for (int i = 0; i < allFeatures.size(); i++) {
            String featureDeadline= allFeatures.get(i).getDeadline().toString();


            int year=Integer.parseInt(featureDeadline.substring(0, 4));;
            int day=Integer.parseInt(featureDeadline.substring(6, 7));
            int month=Integer.parseInt(featureDeadline.substring(9, 10));

           double progress= getFeatureProgress(allFeatures.get(i).getFeatureId());
            cal_obj2.set(year,month,day);
            if(cal_obj2.after(cal_obj1) || progress==100){
                allFeatures.remove(i);
            }
        }

        return allFeatures;

    }

    @Override
    public String getUserNameByUserId(int userId) {
      String name=  userRepository.findByUserId(userId).get().getUsername();

      return name;

    }

}
