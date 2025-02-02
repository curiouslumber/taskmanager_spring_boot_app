package com.noelpinto47.taskmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

// DTO - Data Transfer Object
@Data
@NoArgsConstructor
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;
}
