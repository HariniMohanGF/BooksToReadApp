package com.example.todo.repositories;
import com.example.todo.models.Task;
import com.example.todo.dto.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    // public Task getById(Long id);
    public List<Task> searchByBookName(String name);
    public List<Task> findAll();
    public List<Task> findByCompletedFalse();
}
