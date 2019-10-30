package com.spring.project.model.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Task {
    private Long taskId;
    private Long userId;
    private String taskName;
    private TaskStatus taskStatus;
    private TaskPriority taskPriority;

    public Task(Long userId, String taskName, TaskStatus taskStatus, TaskPriority taskPriority) {
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
