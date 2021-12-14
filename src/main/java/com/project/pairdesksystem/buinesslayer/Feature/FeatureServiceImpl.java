package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
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

    @Override
    public List<Feature> getAllFeatures() {
       return featuresRepository.findAll();
    }

    @Override
    public Feature createFeature(Feature feature) {
        return featuresRepository.save(feature);
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
}
