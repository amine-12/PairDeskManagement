package com.project.pairdesksystem.datalayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Features")
@Builder(builderMethodName = "feature")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_id")
    private Integer task_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "featureName")
    private String featureName;

    @Column(name = "priority")
    private String priority;

    @Column(name = "progress")
    private int progress;

    @Column(name = "description")
    private String description;

    //@Builder.Default
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

}
