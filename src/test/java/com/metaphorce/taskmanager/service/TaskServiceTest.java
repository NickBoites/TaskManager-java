package com.metaphorce.taskmanager.service;

import com.metaphorce.taskmanager.model.Task;
import com.metaphorce.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    TaskService TaskService;

    @Mock
    TaskRepository TaskRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTaskByIdTest() {
        Task Task = new Task();
        Task.setId(1L);
        Task.setDescripcion("Test");
        Task.setEstado("pendiente");

        when(TaskRepository.findById(1L)).thenReturn(Optional.of(Task));

        Task found = TaskService.getTaskById(1L);

        assertEquals(Task.getDescripcion(), found.getDescripcion());
    }
}
