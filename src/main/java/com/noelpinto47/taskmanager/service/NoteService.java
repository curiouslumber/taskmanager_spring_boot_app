package com.noelpinto47.taskmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.noelpinto47.taskmanager.entities.NoteEntity;
import com.noelpinto47.taskmanager.entities.TaskEntity;

public class NoteService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNoteHolders = new HashMap<>();

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotes(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null) {
            return null;
        }

        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        return taskNoteHolders.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null) {
            return null;
        }

        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskNoteHolders.get(taskId).noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }
}
