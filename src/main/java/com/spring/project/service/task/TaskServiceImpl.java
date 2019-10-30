package com.spring.project.service.task;

import com.spring.project.dao.TaskRepository;
import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import com.spring.project.model.user.User;
import com.spring.project.security.SecurityLibrary;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final SecurityLibrary securityLibrary;

    @Override
    public void createTask(Long userId, String taskName) {
        taskRepository.createTask(userId,taskName);
    }

    @Override
    public void deleteTask(Long taskId) {
        Long userId = authenticationService.getUserId();
        User user = userService.getUserById(userId);
        boolean isAdmin = securityLibrary.isAdmin(user.getUserRole());
        if (!isAdmin){
            throw new NotAllowedException();
        }
        taskRepository.deleteTask(taskId);
    }

    @Override
    public Set<Task> findAllTasksByUser(Long userId) {
        return taskRepository.findAllTasksByUser(userId);
    }

    @Override
    public void closeTask(Long taskId) {
        taskRepository.setStatus(taskId, TaskStatus.CLOSED);
    }

    @Override
    public void openTask(Long taskId) {
        taskRepository.setStatus(taskId, TaskStatus.OPEN);
    }

    @Override
    public void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority) {
        taskRepository.setTaskPriorityByTaskId(taskId, taskPriority);
    }
}
