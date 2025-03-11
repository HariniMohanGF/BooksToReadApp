package com.example.todo.controllers;
import com.example.todo.models.Task;
import com.example.todo.dto.TaskDTO;
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
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createNewTask(taskDTO));
    }

//    @PostMapping("")
//    public Task createTask(@RequestBody Task task) {
//        return taskService.createNewTask(task);
//    }

//
//    @GetMapping("/display")
//    public ResponseEntity<List<Task>> getAllTasks() {
//        return ResponseEntity.ok(taskService.getAllTask());
//    }

    @GetMapping("/display")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }


    @GetMapping("/todo")
    public ResponseEntity<List<TaskDTO>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<TaskDTO> searchTask(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.searchTask(id));
    }

    @GetMapping("/check")
    public ResponseEntity<List<TaskDTO>> searchTaskByName(@RequestParam("bookName") String name) {
            List<TaskDTO> tasks = taskService.searchTasksByName(name);
            return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        taskDTO.setId(id);
        return ResponseEntity.ok(taskService.replaceTask(taskDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/replace/{id}")
    public ResponseEntity<Task> replace(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//        TaskDTO existingTask = taskService.searchTask(id);
//        if (existingTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//        if (updates.get("bookName")!=null) {
//            existingTask.setBookName((String) updates.get("bookName"));
//        }
//        if (updates.containsKey("description")) {
//            existingTask.setDescription((String) updates.get("description"));
//        }
      //  Task updatedTask = taskService.updateTask(existingTask);
        Task updatedTask = taskService.updateTask(id,updates);

        return ResponseEntity.ok(updatedTask);

    }
}
