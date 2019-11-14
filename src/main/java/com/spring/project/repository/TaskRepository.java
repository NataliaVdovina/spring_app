package com.spring.project.repository;

import com.spring.project.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(@Param("userId") Long userId);
}
