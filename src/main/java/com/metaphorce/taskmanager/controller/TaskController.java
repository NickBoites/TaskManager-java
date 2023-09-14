package com.metaphorce.taskmanager.controller;

import com.metaphorce.taskmanager.model.ErrorResponse;
import com.metaphorce.taskmanager.model.Task;
import com.metaphorce.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tasks")
public class TaskController {

    @Autowired
    private TaskService TaskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        try {
            List<Task> tasks = TaskService.getAllTasks();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            Task task = TaskService.getTaskById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Task not found for ID: " + id, System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task Task) {
        try {
            Task createdTask = TaskService.createTask(Task);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task Task) {
        try {
            Task.setId(id);
            Task updatedTask = TaskService.updateTask(Task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Task not found for ID: " + id, System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            TaskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Task not found for ID: " + id, System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
