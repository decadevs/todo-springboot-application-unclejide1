package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public MyResponse<List<Task>> getAllTask(){
        List<Task> t = taskService.getAllTask();
        if(t.isEmpty()){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task Available", null);
        }
        return  new MyResponse<>(HttpStatus.OK, "Retrieved Successfully", t);
    }
}