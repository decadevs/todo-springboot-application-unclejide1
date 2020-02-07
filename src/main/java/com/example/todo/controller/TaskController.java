package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public MyResponse<Task> createTask(@RequestBody Task task, HttpServletResponse response){
        Task t = taskService.createTask(task);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Todo created successfully";
        if(t == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "common get out with your bad request!!!";
        }
        response.setStatus(statusCode.value());
        return new MyResponse<>(statusCode, message, t);
    }


    @GetMapping
    @RequestMapping("{id}")
    public  MyResponse<Task> getOneTask(@PathVariable Long id, HttpServletResponse response){
        Task retrievedTask = taskService.getATask(id);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Task gotten successfully";
        if(retrievedTask == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, retrievedTask);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public MyResponse<List<Task>> getAllTask(HttpServletResponse response){
        List<Task> allTask = taskService.getAllTask();
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "All tasks has been retrieved successfully";
        if(allTask.isEmpty()){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, allTask);
    }

    @GetMapping
    @RequestMapping("/Status/{status}")
    public MyResponse<List<Task>> findByStatus(@PathVariable String status, HttpServletResponse response){
        List<Task> taskFoundByStatus = taskService.findByStatus(status);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "All " + status + " tasks has been retrieved successfully";
        if(taskFoundByStatus.isEmpty()){
            statusCode = HttpStatus.BAD_REQUEST;
            message = status + " task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, taskFoundByStatus);
    }

    @RequestMapping(path="{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MyResponse<Task> updateTask(@RequestBody Task task, @PathVariable Long id, HttpServletResponse response ){
            Task updatedTodo = taskService.updateTask(task, id);
            HttpStatus statusCode = HttpStatus.CREATED;
            String message = "Task updated successfully";
            if(updatedTodo == null){
                statusCode = HttpStatus.BAD_REQUEST;
                message = "Task does not exist";
            }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, updatedTodo);
    }

    @RequestMapping(path="{id}", method = RequestMethod.DELETE)
    public MyResponse<Long> deleteTask(@PathVariable(name = "id") Long id, HttpServletResponse response){
        Long deletedTaskId = taskService.deleteTask(id);
        HttpStatus statusCode = HttpStatus.OK;
        String message = "Task Deleted Successfully";
        if(deletedTaskId == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, deletedTaskId);
    }
}
