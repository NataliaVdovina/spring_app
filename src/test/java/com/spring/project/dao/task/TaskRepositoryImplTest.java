package com.spring.project.dao.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.spring.project.dao.task.TaskRepositoryImpl.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryImplTest {

    private final static Long USER_ID = 1L;
    private final static Long TASK_ID = 1L;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TaskRepositoryImpl taskRepositoryImpl;

    @Test
    void createTaskTest() {
        //prepare data
        String taskName = "testTaskName";

        //call tested method
        taskRepositoryImpl.createTask(USER_ID, taskName);

        //check/equals
        verify(jdbcTemplate).update(INSERT, USER_ID, taskName, TaskStatus.OPEN, TaskPriority.LOW);
    }

    @Test
    void deleteTask() {
        taskRepositoryImpl.deleteTask(TASK_ID);
        verify(jdbcTemplate).update(DELETE_BY_ID, TASK_ID);
    }

    @Test
    void findAllTasksByUserTest() {
        //prepare data
        List<Task> expectedTasks = MockData.tasks();
        when(jdbcTemplate.query(eq(FIND_ALL_TASKS_BY_USER), any(TaskRowMapper.class), eq(USER_ID)))
                .thenReturn(expectedTasks);

        //call tested method
        List<Task> actualTasks = taskRepositoryImpl.findAllTasksByUser(USER_ID);

        //check/equals
        verify(jdbcTemplate).query(eq(FIND_ALL_TASKS_BY_USER), any(TaskRowMapper.class), eq(USER_ID));
        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void setStatus() {
        TaskStatus taskStatus = TaskStatus.CLOSED;
        taskRepositoryImpl.setStatus(TASK_ID,taskStatus);
        verify(jdbcTemplate).update(SET_STATUS, taskStatus, TASK_ID);
    }

    @Test
    void setTaskPriorityByTaskId() {
        TaskPriority taskPriority = TaskPriority.LOW;
        taskRepositoryImpl.setTaskPriorityByTaskId(TASK_ID, taskPriority);
        jdbcTemplate.update(SET_TASK_PRIORITY, taskPriority, TASK_ID);
    }

    @Test
    void getTaskCountByUserId() {
        Long userId = 1L;
        List<Task> expectedTasks = MockData.tasks();
        Long expectedCountTasks = (long) expectedTasks.size();
        when(jdbcTemplate.query(eq(GET_TASK_COUNT_BY_USER_ID), any(TaskRowMapper.class), eq(userId)))
                .thenReturn(expectedTasks);
        Long actualCountTasks = taskRepositoryImpl.getTaskCountByUserId(userId);
        verify(jdbcTemplate).query(eq(GET_TASK_COUNT_BY_USER_ID), any(TaskRowMapper.class), eq(userId));
        assertEquals(expectedCountTasks, actualCountTasks);
    }

    private static class MockData {

        private static List<Task> tasks() {
            List<Task> tasks = new ArrayList<>();
            tasks.add(task(1L));
            tasks.add(task(2L));
            return tasks;
        }

        private static Task task(Long id) {
            return Task.builder()
                    .taskId(id)
                    .userId(id)
                    .taskName("taskName")
                    .taskStatus(TaskStatus.CLOSED)
                    .taskPriority(TaskPriority.LOW)
                    .build();
        }

        private static User user() {
            return User.builder()
                    .userId(1L)
                    .email("email")
                    .password("password")
                    .firstName("firstName")
                    .lastName("lastName")
                    .subscription("subscription")
                    .userRole(UserRole.ADMIN)
                    .build();
        }
    }
}