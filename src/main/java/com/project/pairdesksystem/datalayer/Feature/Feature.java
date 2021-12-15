package com.project.pairdesksystem.datalayer.Feature;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import java.util.Random;
import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements(groups = Feature.class)
    private Integer featureId;

    private Integer user_id;

    private String featureName;

    private String priority;

    private int progress;

    private String description;

    private Date deadline;

    public Integer getId() {
        return id;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        Random rand = new Random();
        this.featureId = rand.nextInt(99999);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
