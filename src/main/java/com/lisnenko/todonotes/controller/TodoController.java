package com.lisnenko.todonotes.controller;

import com.lisnenko.todonotes.model.Todo;
import com.lisnenko.todonotes.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/todos")
    public String showListTodo(Model model) {
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/showNewTodoForm")
    public String showNewTodoForm(Model model) {
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        return "newTodo";
    }

    @GetMapping({"/showFormForUpdate/{id}"})
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);

        model.addAttribute("todo", todo);
        return "updateTodo";
    }

    @PostMapping("/saveTodo")
    public String saveTodo(@ModelAttribute("todo") Todo todo) {
        todoService.insert(todo);
        return "redirect:/todos";
    }

    @PostMapping("/updateTodo/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute("todo") Todo todo) {
        todoService.updateTodo(id ,todo);
        return "redirect:/todos";
    }


    @GetMapping({"/deleteTodo/{id}"})
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}
