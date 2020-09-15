package com.azerion.sso.service;

import com.azerion.sso.model.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> findAll();
    public Todo findBy(Long todoId);
    public Todo save(Todo todo);
    public Todo update(Todo todo);
    public Todo deleteBy(Long todoId);
}
