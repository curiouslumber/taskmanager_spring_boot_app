package com.noelpinto47.taskmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noelpinto47.taskmanager.dto.CreateTaskDTO;
import com.noelpinto47.taskmanager.dto.ErrorResponseDTO;
import com.noelpinto47.taskmanager.entities.TaskEntity;
import com.noelpinto47.taskmanager.service.TaskService;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService; //Automatic dependency injection
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id) {
        var task = taskService.getTaskById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO reqBody) throws ParseException {
        TaskEntity task;
        task = taskService.addTask(reqBody.getTitle(), reqBody.getDescription(), reqBody.getDeadline());
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleParseException(Exception e) {
        if(e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}
