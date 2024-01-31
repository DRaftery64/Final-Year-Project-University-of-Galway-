package springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import springboot.model.Task;

public interface TaskService {
 List<Task> getAllTasks();
 void saveTask(Task task);
 Task getTaskById(long task_id);
 void deleteTaskById(long task_id);
 Page<Task> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}