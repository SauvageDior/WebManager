package com.example.demo.controllers;

import com.example.demo.model.Task;
import com.example.demo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String tasks(@RequestParam(name = "name", required = false) String name,  Model model, Principal principal) {
        model.addAttribute("tasks", taskService.list(name));
        model.addAttribute("user", taskService.getUserByPrincipal(principal));
        return "tasks";
    }
    @PostMapping("/task/create")
    public String createTask(Task task, Principal principal){
        taskService.saveTask(principal, task);
        return "redirect:/";
    }
    @PostMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal){
        taskService.deleteTask(taskService.getUserByPrincipal(principal), id);
        return "redirect:/";
    }
    @GetMapping("/task/{id}")
    public String taskInfo(@PathVariable Long id, Model model){
        model.addAttribute("task", taskService.getTaskByID(id));
        return "task-info";
    }
}
