package com.project.pairdesksystem.datalayer.Feature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeaturesRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findByFeatureId(int featureId);
}
