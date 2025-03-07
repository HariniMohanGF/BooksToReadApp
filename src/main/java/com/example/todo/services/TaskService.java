package com.example.todo.services;

import com.example.todo.models.Task;
import com.example.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public Task searchTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> searchTasksByName(String name) {
        return taskRepository.searchByBookName(name);
    }

    public Task replaceTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }



}
