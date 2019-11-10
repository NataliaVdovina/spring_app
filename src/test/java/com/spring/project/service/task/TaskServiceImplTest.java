package com.spring.project.service.task;

import com.spring.project.dao.TaskRepository;
import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import com.spring.project.model.user.User;
import com.spring.project.security.SecurityLibrary;
import com.spring.project.security.UserRole;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private UserService userService;
    @Mock
    private SecurityLibrary securityLibrary;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    private final static Long USER_ID = 1L;
    private final static Long TASK_ID = 1L;

    @Test
    void createTask() {
        String taskName = "testTaskName";

        taskServiceImpl.createTask(USER_ID, taskName);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskArgumentCaptor.capture());
        Task task = taskArgumentCaptor.getValue();
        assertEquals(USER_ID, task.getUserId());
        assertEquals(taskName, task.getTaskName());
        assertEquals(TaskStatus.OPEN, task.getTaskStatus());
        assertEquals(TaskPriority.LOW, task.getTaskPriority());
    }

    @Test
    void deleteTask_admin() {
        when(authenticationService.getUserId())
                .thenReturn(USER_ID);
        User user = MockData.user();
        when(userService.getUserById(USER_ID))
                .thenReturn(user);
        when(securityLibrary.isAdmin(user.getUserRole()))
                .thenReturn(true);

        taskServiceImpl.deleteTask(TASK_ID);

        verify(taskRepository).deleteById(TASK_ID);
    }

    @Test
    void deleteTask_notAdmin() {
        when(authenticationService.getUserId())
                .thenReturn(USER_ID);
        User user = MockData.user();
        when(userService.getUserById(USER_ID))
                .thenReturn(user);
        when(securityLibrary.isAdmin(user.getUserRole()))
                .thenReturn(false);

        assertThrows(NotAllowedException.class, () -> taskServiceImpl.deleteTask(TASK_ID));
        verify(taskRepository, never()).deleteById(TASK_ID);
    }

    @Test
    void findAllTasksByUser() {
        List<Task> expectedTasks = MockData.tasks();
        when(taskRepository.findByUserId(USER_ID))
                .thenReturn(expectedTasks);

        List<Task> actualTasks = taskServiceImpl.findAllTasksByUser(USER_ID);
        verify(taskRepository).findByUserId(USER_ID);
        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void closeTask() {
        Task task = MockData.task(TASK_ID);
        when(taskRepository.findById(TASK_ID))
                .thenReturn(Optional.of(task));

        taskServiceImpl.closeTask(TASK_ID);

        verify(taskRepository).save(task);
        assertEquals(TaskStatus.CLOSED, task.getTaskStatus());
    }

    @Test
    void openTask() {
        Task task = MockData.task(TASK_ID);
        when(taskRepository.findById(TASK_ID))
                .thenReturn(Optional.of(task));

        taskServiceImpl.closeTask(TASK_ID);

        verify(taskRepository).save(task);
        assertEquals(TaskStatus.CLOSED, task.getTaskStatus());
    }

    @Test
    void setTaskPriorityByTaskId() {
        Task task = MockData.task(TASK_ID);
        when(taskRepository.findById(TASK_ID))
                .thenReturn(Optional.of(task));

        taskServiceImpl.setTaskPriorityByTaskId(TASK_ID, TaskPriority.IMPORTANT);

        verify(taskRepository).save(task);
        assertEquals(TaskPriority.IMPORTANT, task.getTaskPriority());
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