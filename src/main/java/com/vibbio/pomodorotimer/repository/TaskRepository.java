package com.vibbio.pomodorotimer.repository;
import com.vibbio.pomodorotimer.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

  Task findTaskByTaskName(String taskName);

  // Find all the task where timelogged is not empty
  @Query("SELECT t from Task t where t.timeLogged <> ''")
  List<Task> findAllTasks();

  // Find last saved task from database
  Task findTopByOrderByTaskIdDesc();
}