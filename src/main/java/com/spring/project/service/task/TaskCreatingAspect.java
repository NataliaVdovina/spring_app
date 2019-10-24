package com.spring.project.service.task;

import com.spring.project.controller.user.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@RequiredArgsConstructor
public class TaskCreatingAspect {

    private final UserService userService;
    private final TaskDao taskDao;

    @Before(value = "execution(* com.spring.project.service.task.TaskServiceImpl.createTask(Long, String))" +
            " && args(userId, taskName)"
            , argNames = "userId, taskName")
    public void checkSubscriptionClause(Long userId, String taskName) {
        if (userService.checkSubscription(userId)) {
            return;
        }
        Long taskCountByUserId = taskDao.getTaskCountByUserId(userId);
        if (taskCountByUserId == 10) {
            throw new SubscriptionException();
        }
    }
}