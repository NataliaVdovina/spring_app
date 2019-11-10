package com.spring.project.service.task;

import com.spring.project.dao.TaskRepository;
import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import com.spring.project.model.user.User;
import com.spring.project.security.SecurityLibrary;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.exception.NotFoundException;
import com.spring.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final SecurityLibrary securityLibrary;

    @Override
    public void createTask(Long userId, String taskName) {
        Task task = new Task(userId, taskName, TaskStatus.OPEN, TaskPriority.LOW);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Long userId = authenticationService.getUserId();
        User user = userService.getUserById(userId);
        boolean isAdmin = securityLibrary.isAdmin(user.getUserRole());
        if (!isAdmin) {
            throw new NotAllowedException();
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAllTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public void closeTask(Long taskId) {
        setTaskStatus(taskId, TaskStatus.CLOSED);
    }

    private void setTaskStatus(Long taskId, TaskStatus taskStatus) {
        Task task = getTaskById(taskId);
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
    }

    private Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                    .orElseThrow(() -> new NotFoundException("Task", taskId));
    }

    @Override
    public void openTask(Long taskId) {
        setTaskStatus(taskId, TaskStatus.OPEN);
    }

    @Override
    public void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority) {
        Task task = getTaskById(taskId);
        task.setTaskPriority(taskPriority);
        taskRepository.save(task);
    }

    @Override
    public void putFile(User user, Blob file, Long taskId) {
//        if (!userService.checkSubscription(user.getUserId())) {
//            throw new SubscriptionException();
//        }
//        taskRepository.putFile(file, taskId);
    }
}
