package com.project.pairdesksystem.datalayer.Task;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements(groups = Task.class)
    private Integer taskId;

    private Integer featureId;
    private String taskName;
    private String priority;
    private String status;
    private String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        Random rand = new Random();
        this.taskId = rand.nextInt(99999);;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
