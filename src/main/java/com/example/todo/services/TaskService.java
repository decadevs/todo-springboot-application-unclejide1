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

    public Task getATask(Long id){
        return taskRepository.findById(id).orElse(null);
    }


    public List<Task> findByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTask(Task taskToUpdate, Long id){
       Task oldTask = taskRepository.findById(id).orElse(null);
       if(oldTask != null){
           oldTask.setTitle(taskToUpdate.getTitle());
           oldTask.setDescription(taskToUpdate.getDescription());
           return taskRepository.save(oldTask);
       }
       return  taskToUpdate;
    }
}
