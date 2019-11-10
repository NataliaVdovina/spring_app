package com.spring.project.model.task;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    @Column
    private Long userId;
    @Column
    private String taskName;
    @Column
    private TaskStatus taskStatus;
    @Column
    private TaskPriority taskPriority;
    @Column
    private Blob file;

    public Task(Long userId, String taskName, TaskStatus taskStatus, TaskPriority taskPriority) {
        this.userId = userId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }
}
