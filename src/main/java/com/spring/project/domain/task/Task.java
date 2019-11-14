package com.spring.project.domain.task;

import com.spring.project.domain.user.User;
import lombok.*;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    @Column
    private String taskName;
    @Column
    private TaskStatus taskStatus;
    @Column
    private TaskPriority taskPriority;
    @Column
    private String fileName;

    public Task(Long taskId, String taskName, TaskStatus taskStatus, TaskPriority taskPriority) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }
}
