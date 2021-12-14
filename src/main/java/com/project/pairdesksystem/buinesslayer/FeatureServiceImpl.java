package com.project.pairdesksystem.buinesslayer;

import com.project.pairdesksystem.datalayer.Feature;
import com.project.pairdesksystem.datalayer.FeatureDTO;
import com.project.pairdesksystem.datalayer.FeaturesRepository;
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
