package com.azerion.sso.controller;

import com.azerion.sso.controller.resource.TodoResource;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TodoController {
    public ResponseEntity<List<TodoResource>> list();
    public ResponseEntity<TodoResource> find(Long todoId);
    public ResponseEntity<TodoResource> save(TodoResource todoResource);
    public ResponseEntity<TodoResource> update(TodoResource todoResource);
    public ResponseEntity delete(Long todoId);
}
