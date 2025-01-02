package com.noelpinto47.taskmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;
}
