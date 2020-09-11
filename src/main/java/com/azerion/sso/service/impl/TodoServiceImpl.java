package com.azerion.sso.service.impl;


import com.azerion.sso.exception.EntityNotFoundException;
import com.azerion.sso.exception.NotAnAppropriateInputParameterException;
import com.azerion.sso.model.CreationDate;
import com.azerion.sso.model.Todo;
import com.azerion.sso.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private static List<Todo> availableTodos;
    static{
        availableTodos=new ArrayList<>();
        availableTodos.add(Todo.builder().id(101L).name("Yapılacak 1").creationDate(CreationDate.builder().createdDate(LocalDateTime.now()).build()).build());
        availableTodos.add(Todo.builder().id(102L).name("Yapılacak 2").creationDate(CreationDate.builder().createdDate(LocalDateTime.now()).build()).build());
    }

    @Override
    public List<Todo> findAll() {
        return availableTodos;
    }

    @Override
    public Todo findBy(Long todoId) {
        Predicate<Todo> predicateFindTodo=todo ->todo.getId().equals(todoId);
        Optional<Todo> todoOptional = availableTodos.stream().filter(predicateFindTodo).findAny();
        if (todoOptional.isPresent()){
            return todoOptional.get();
        }
        throw new EntityNotFoundException(todoId+" numaralı Todo bulunamadı");
    }

    @Override
    public Todo save(Todo todo) {
        if (todo.getId()!=null){
            throw new NotAnAppropriateInputParameterException("Id");
        }
        if (todo.getName()==null){
            throw new NotAnAppropriateInputParameterException("Name");
        }
        Random rnd=new Random();
        todo.setId(rnd.nextLong());
        todo.setVersion(0L);
        if (!todo.existCreationDate()){
            todo.setCreationDate(CreationDate.builder().createdDate(LocalDateTime.now()).build());
        }else{
            todo.getCreationDate().setCreatedDate(LocalDateTime.now());
        }
        availableTodos.add(todo);
        return todo;
    }

    @Override
    public Todo update(Todo todo) {
        if (todo.getId()==null){
            throw new NotAnAppropriateInputParameterException("Id");
        }else if (todo.getName()==null){
            throw new NotAnAppropriateInputParameterException("Name");
        }
        Predicate<Todo> predicateFindTodo=todo1 ->todo1.getId().equals(todo.getId());
        if (availableTodos.stream().noneMatch(predicateFindTodo)){
            throw new EntityNotFoundException(todo.getId()+" numaralı Todo bulunamadı");
        }
        if (todo.existCreationDate()){
            todo.getCreationDate().setUpdatedDate(LocalDateTime.now());
        }else{
            todo.setCreationDate(CreationDate.builder().createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build());
        }
        availableTodos.removeIf(predicateFindTodo);
        availableTodos.add(todo);
        return null;
    }

    @Override
    public Todo deleteBy(Long todoId) {
        final Optional<Todo> foundedTodo = availableTodos.stream().filter(todo -> todo.getId().equals(todoId)).findFirst();
        if (!availableTodos.removeIf(todo->todo.getId().equals(todoId))){
            throw new EntityNotFoundException(todoId+" numaralı Todo bulunamadı");
        }
        availableTodos.add(foundedTodo.get());// Gerçekten çıkarmamak için
        return foundedTodo.get();
    }
}
