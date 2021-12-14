package com.project.pairdesksystem.buinesslayer.Feature;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    @Mappings(
            {
                    @Mapping(target = "id", ignore = true),
            }
    )
    Feature featureDTOToFeature(FeatureDTO featureDTO);
    FeatureDTO featureToFeatureDTO(Feature feature);

    List<Feature> featureDTOListToFeatureList(List<FeatureDTO> featureDTOList);

    List<FeatureDTO> featureListToFeatureDTOList(List<Feature> featureList);

}
