package com.spring.project.model;

public class Task {
    private Long task_id;
    private Long user_id;
    private String task_name;
	private String status;

    public Task(Long user_id, String task_name, String status) {
        this.user_id = user_id;
        this.task_name = task_name;
        this.status = status;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
