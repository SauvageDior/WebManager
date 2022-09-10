package com.example.demo.services;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> list(String name) {
        if (name != null) return taskRepository.findByName(name);
        return taskRepository.findAll();
    }

    public void saveTask(Principal principal, Task task) {
        task.setUser(getUserByPrincipal(principal));
        //log.info("Saving new task");
        taskRepository.save(task);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return (User) userRepository.findByEmail(principal.getName());
    }

    public void deleteTask(User user, Long id) {
        Task task = taskRepository.getById(id);
        if (task != null) {
            if (task.getUser().getId().equals(user.getId())) {
                taskRepository.delete(task);
                log.info("task deleted");
            } else {
                log.error("");
            }
        } else {
            log.error("");
        }
    }

    public Task getTaskByID(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}
