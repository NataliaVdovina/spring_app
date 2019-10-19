package com.spring.project.dao;

import com.spring.project.model.Task;
import com.spring.project.model.User;
import com.spring.project.service.task.TaskDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TaskDaoImpl implements TaskDao {
    private Set<Task> tasks = new HashSet<>();

    @PostConstruct
    public void init(){
        User user = new User(1L, "testEmail1", "testPassword1", "testFirstName1",
                "testLastName1");
        Task task1 = new Task(1L, user, "testTaskName1", "testStatus1");
        Task task2 = new Task(2L, user, "testTaskName2", "testStatus2");
        tasks.add(task1);
        tasks.add(task2);
    }
}
