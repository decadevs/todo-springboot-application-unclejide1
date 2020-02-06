package com.example.todo.services;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        Task createdTask = null;

        try {
            createdTask = taskRepository.save(task);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return createdTask;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
}
