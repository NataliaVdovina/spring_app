package com.spring.project.service.task;

import com.spring.project.repository.TaskRepository;
import com.spring.project.service.user.UserService;
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
    private final TaskRepository taskRepository;

    @Pointcut (value = "execution(* com.spring.project.service.task.TaskServiceImpl.createTask(Long, ..))")
    public void pointcutCreateTask(){}

    @Before ("pointcutCreateTask()")
    public void checkSubscriptionClause(JoinPoint joinPoint) {
        Long userId = (Long) joinPoint.getArgs()[0];
        if (userService.checkSubscription(userId)) {
            return;
        }
        int taskCountByUserId = taskRepository.findByUserId(userId).size();
        if (taskCountByUserId == 10) {
            throw new SubscriptionException();
        }
    }
}
