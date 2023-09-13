package com.metaphorce.taskmanager.controller;


import com.metaphorce.taskmanager.model.Task;
import com.metaphorce.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tasks")
public class TaskController {
    @Autowired
    private TaskService TaskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return TaskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return TaskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task Task) {
        return TaskService.createTask(Task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task Task) {
        Task.setId(id);
        return TaskService.updateTask(Task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        TaskService.deleteTask(id);
    }
}
