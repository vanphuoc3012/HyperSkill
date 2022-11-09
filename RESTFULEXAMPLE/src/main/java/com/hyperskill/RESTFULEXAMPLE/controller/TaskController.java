package com.hyperskill.RESTFULEXAMPLE.controller;

import com.hyperskill.RESTFULEXAMPLE.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final List<Task> taskList = List.of(
            new Task(1, "task1", "A first test task", false),
            new Task(2, "task2", "A second test task", true)
    );

    @GetMapping("/test")
    public int returnOne(){
        return 1;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return this.taskList;
    }

//    @GetMapping("/tasks/{id}")
//    public Task getTask(@PathVariable int id){
//        return this.taskList.get(id - 1);
//    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTasks(@PathVariable int id){
        return new ResponseEntity<>(taskList.get(id-1), HttpStatus.FORBIDDEN);
    }
}
