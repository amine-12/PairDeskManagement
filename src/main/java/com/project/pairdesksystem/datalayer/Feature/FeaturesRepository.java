package com.project.pairdesksystem.datalayer.Feature;

import com.project.pairdesksystem.datalayer.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeaturesRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findByFeatureId(int featureId);
    List<Feature> findAllByUserId(int userId);
}
