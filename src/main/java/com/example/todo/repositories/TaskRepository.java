package com.example.todo.repositories;

import com.example.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> searchByBookName(String name);
    public List<Task> findAll();
    public List<Task> findByCompletedFalse();
    // public Task getById(Long id);
}
