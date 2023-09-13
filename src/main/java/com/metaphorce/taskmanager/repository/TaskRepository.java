package com.metaphorce.taskmanager.repository;


import com.metaphorce.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
