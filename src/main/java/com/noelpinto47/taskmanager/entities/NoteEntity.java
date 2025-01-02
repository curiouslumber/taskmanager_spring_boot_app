package com.noelpinto47.taskmanager.entities;

import lombok.Data;

@Data
public class NoteEntity {
    private int id;
    private String title;
    private String body;
}
