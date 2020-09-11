package com.azerion.sso.controller.admin;

import com.azerion.sso.controller.TodoController;
import com.azerion.sso.controller.resource.TodoResource;
import com.azerion.sso.controller.resource.assembler.TodoAdminResourceAssembler;
import com.azerion.sso.controller.resource.assembler.TodoResourceAssembler;
import com.azerion.sso.model.Todo;
import com.azerion.sso.service.TodoService;
import com.azerion.sso.service.impl.TodoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/todo")
@Slf4j
public class AdminTodoController implements TodoController {

    private TodoService todoService;
    private TodoResourceAssembler todoResourceAssembler;

    public AdminTodoController(){}

    @Autowired
    public AdminTodoController(TodoServiceImpl todoService, TodoAdminResourceAssembler todoResourceAssembler){
        this.todoService=todoService;
        this.todoResourceAssembler=todoResourceAssembler;
    }

    @GetMapping
    public ResponseEntity<List<TodoResource>> list(){
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(todos.stream().map(todoResourceAssembler::toModel).collect(Collectors.toList()));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResource> find(@PathVariable(name = "todoId",required = true) Long todoId){
        Todo todo = todoService.findBy(todoId);
        return ResponseEntity.ok(todoResourceAssembler.toModel(todo));
    }

    @PostMapping
    public ResponseEntity<TodoResource> save(@RequestBody TodoResource todoResource){
        final Todo savedTodo = todoService.save(todoResourceAssembler.toEntity(todoResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResourceAssembler.toModel(savedTodo));
    }

    @PutMapping
    public ResponseEntity<TodoResource> update(@RequestBody TodoResource todoResource){
        final Todo updatedTodo = todoService.update(todoResourceAssembler.toEntity(todoResource));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(todoResourceAssembler.toModel(updatedTodo));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoResource>  delete(@PathVariable(name = "todoId",required = true) Long todoId){
        final Todo deletedTodo = todoService.deleteBy(todoId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoResourceAssembler.toModel(deletedTodo));
    }

}
