package com.example.todo.services;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
           //Date object
           Date date= new Date();
           //getTime() returns current time in milliseconds
           long time = date.getTime();
           //Passed the milliseconds to constructor of Timestamp class
           Timestamp completedTime = new Timestamp(time);
           String status = taskToUpdate.getStatus();
           String completedStatus = "completed";
           if (status.equalsIgnoreCase(completedStatus)){
               oldTask.setStatus(completedStatus);
               oldTask.setCompletedAT(completedTime);
           }
           else{
               oldTask.setStatus(status);
               oldTask.setCompletedAT(null);
           }
           return taskRepository.save(oldTask);
       }
       return  taskToUpdate;
    }

    public Long deleteTask(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
