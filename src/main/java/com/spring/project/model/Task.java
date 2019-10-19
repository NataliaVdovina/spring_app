package com.spring.project.model;

import java.util.Objects;

public class Task {
    private Long taskId;
    private User user;
    private String taskName;
    private String status;

    public Task(Long taskId, User user, String taskName, String status) {
        this.taskId = taskId;
        this.user = user;
        this.taskName = taskName;
        this.status = status;
    }

    public Long getTask_id() {
        return taskId;
    }

    public void setTask_id(Long taskId) {
        this.taskId = taskId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) &&
                Objects.equals(user, task.user) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, user, taskName, status);
    }
}
