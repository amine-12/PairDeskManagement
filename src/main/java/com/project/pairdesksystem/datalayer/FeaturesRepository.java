package com.project.pairdesksystem.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaRepository<Feature, Integer> {

}
