package com.project.pairdesksystem.datalayer.Task;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByFeatureId(int featureId);
}
