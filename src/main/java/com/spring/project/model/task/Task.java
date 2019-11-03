package com.spring.project.model.task;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Task {

    private Long taskId;
    @NonNull
    private Long userId;
    @NonNull
    private String taskName;
    @NonNull
    private TaskStatus taskStatus;
    @NonNull
    private TaskPriority taskPriority;

    public Task(Long userId, String taskName, TaskStatus taskStatus, TaskPriority taskPriority) {
        this.userId = userId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }

    public Task(Long taskId, Long userId, String taskName, TaskStatus taskStatus, TaskPriority taskPriority) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(userId, task.userId) &&
                Objects.equals(taskName, task.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskName);
    }
}
