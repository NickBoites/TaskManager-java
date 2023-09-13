package com.metaphorce.taskmanager.service;


import com.metaphorce.taskmanager.model.Task;
import com.metaphorce.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository TaskRepository;

    public List<Task> getAllTasks() {
        return TaskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return TaskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task Task) {
        return TaskRepository.save(Task);
    }

    public Task updateTask(Task Task) {
        return TaskRepository.save(Task);
    }

    public void deleteTask(Long id) {
        TaskRepository.deleteById(id);
    }
}
