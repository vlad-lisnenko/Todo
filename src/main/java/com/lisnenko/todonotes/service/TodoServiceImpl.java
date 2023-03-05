package com.lisnenko.todonotes.service;

import com.lisnenko.todonotes.model.Todo;
import com.lisnenko.todonotes.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = null;
        if(optionalTodo.isPresent()) {
            todo = optionalTodo.get();
        }else {
            throw new RuntimeException("Todo not found for id ::" + id);
        }
        return todo;
    }

    @Override
    public Todo insert(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDb = todoRepository.findById(id).get();
        log.info("Old object before to update: " + todoFromDb);
        todoFromDb.setDescription(todo.getDescription());
        todoFromDb.setStatus(todo.getStatus());
        todoFromDb.setTitle(todo.getTitle());
        todoRepository.save(todoFromDb);
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
