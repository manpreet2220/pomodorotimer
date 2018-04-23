package com.vibbio.pomodorotimer.service;

import com.vibbio.pomodorotimer.model.Task;
import com.vibbio.pomodorotimer.repository.TaskRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskServiceImpl implements TaskService {

  private Logger log = LogManager.getLogger(TaskServiceImpl.class);

  @Autowired
  TaskRepository taskRepository;

  public void setTaskRepository(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Integer saveTask(Task task) {
    //Set logged time to previous task and insert new task
    Task previousTask=taskRepository.findTopByOrderByTaskIdDesc();
    if(previousTask!=null){
      previousTask.setTimeLogged(task.getTimeLogged());
      taskRepository.save(previousTask);
    }
    task.setTimeLogged("");
    taskRepository.save(task);
    Integer taskId=task.getTaskId();
    return taskId;
  }

  @Override
  public Task findTask(Integer taskId) {
    Task task=taskRepository.findOne(taskId);
    return task;
  }

  @Override
  public Task findTaskByName(String taskName) {
    Task task=taskRepository.findTaskByTaskName(taskName);
    return task;
  }

  @Override
  public List<Task> findAllTasks() {
    List<Task> taskList=taskRepository.findAllTasks();
    return taskList;
  }
}
