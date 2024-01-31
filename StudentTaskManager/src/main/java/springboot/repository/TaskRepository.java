package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}