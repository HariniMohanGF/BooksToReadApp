package com.example.todo.services;
import com.example.todo.dto.TaskDTO;
import org.modelmapper.ModelMapper;
import com.example.todo.models.Task;
import com.example.todo.repositories.TaskRepository;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return taskRepository.save(modelMapper.map(taskDTO, Task.class));

    }

    public Task replaceTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return taskRepository.save(modelMapper.map(taskDTO, Task.class));

    }


    public List<TaskDTO> getAllTask() {
        List<Task> task = taskRepository.findAll();
        Type listType = new TypeToken<List<TaskDTO>>(){}.getType();

       ModelMapper modelMapper = new ModelMapper();
       List<TaskDTO> taskDTOList = modelMapper.map(task, listType);
       return taskDTOList;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id,Map<String, Object> updates) {


        TaskDTO existingTask = searchTask(id);
//        if (existingTask == null) {
//            return ResponseEntity.notFound().build();
//        }
        if (updates.get("bookName") != null) {
            existingTask.setBookName((String) updates.get("bookName"));
        }
        if (updates.containsKey("description")) {
            existingTask.setDescription((String) updates.get("description"));
        }
       // TaskDTO updatedTask = updateTask(existingTask);
        ModelMapper modelMapper = new ModelMapper();
        return taskRepository.save(modelMapper.map(existingTask, Task.class));

    }

        public TaskDTO searchTask(Long id) {
            ModelMapper modelMapper = new ModelMapper();
            TaskDTO taskDTO = modelMapper.map(taskRepository.findById(id).get(), TaskDTO.class);
            return taskDTO;

    }

    public List<TaskDTO> searchTasksByName(String name) {
            List<Task> t = taskRepository.searchByBookName(name);
            Type ListType = new TypeToken<List<TaskDTO>>(){}.getType();
            ModelMapper modelMapper = new ModelMapper();
            List<TaskDTO> tt = modelMapper.map(t, ListType);
            return tt;
    }


    public List<TaskDTO> findAllInCompleteTask() {
        List<Task> task = taskRepository.findByCompletedFalse();
        Type listType = new TypeToken<List<TaskDTO>>(){}.getType();

        ModelMapper modelMapper = new ModelMapper();
        List<TaskDTO> taskDTOList = modelMapper.map(task, listType);
        return taskDTOList;

    }



}
