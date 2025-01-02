package com.noelpinto47.taskmanager.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.noelpinto47.taskmanager.entities.TaskEntity;

@Service
public class TaskService  {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, String deadline) {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
