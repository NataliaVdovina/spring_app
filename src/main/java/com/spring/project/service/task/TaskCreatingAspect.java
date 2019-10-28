package com.spring.project.service.task;

import com.spring.project.controller.user.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class TaskCreatingAspect {

    private final UserService userService;
    private final TaskDao taskDao;

    @Pointcut (value = "execution(* com.spring.project.service.task.TaskServiceImpl.createTask(Long, ..))")
    public void pointcutCreateTask(){}

    @Before ("pointcutCreateTask()")
    public void checkSubscriptionClause(JoinPoint joinPoint) {
        Long userId = (Long) joinPoint.getArgs()[0];
        if (userService.checkSubscription(userId)) {
            return;
        }
        Long taskCountByUserId = taskDao.getTaskCountByUserId(userId);
        if (taskCountByUserId == 10) {
            throw new SubscriptionException();
        }
    }
}
