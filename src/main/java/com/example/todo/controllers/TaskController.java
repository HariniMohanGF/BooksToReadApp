package com.example.todo.controllers;
import com.example.todo.models.Task;
import com.example.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

//    @PostMapping("")
//    public Task createTask(@RequestBody Task task) {
//        return taskService.createNewTask(task);
//    }


    @GetMapping("/display")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Task> searchTask(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.searchTask(id));
    }

//    @GetMapping("/check={name}")
//    public ResponseEntity<List<Task>> searchTasksByName(@PathVariable String name) {
//        List<Task> tasks = taskService.searchTasksByName(name);
//        return ResponseEntity.ok(tasks);
//    }

    @GetMapping("/check")
    public ResponseEntity<List<Task>> searchTaskByName(@RequestParam("bookName") String name) {
            List<Task> tasks = taskService.searchTasksByName(name);
            return ResponseEntity.ok(tasks);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(true);
    }


    @PatchMapping("/replace/{id}")
    public ResponseEntity<Task> replace(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Task existingTask = taskService.searchTask(id);

        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        //Task taskToUpdate = existingTask.get();
        if (updates.get("bookName")!=null) {
            existingTask.setBookName((String) updates.get("bookName"));
        }
        if (updates.containsKey("description")) {
            existingTask.setDescription((String) updates.get("description"));
        }

        Task updatedTask = taskService.updateTask(existingTask);

        return ResponseEntity.ok(updatedTask);

    }
}
