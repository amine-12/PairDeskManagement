package com.project.pairdesksystem.datalayer.Task;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
