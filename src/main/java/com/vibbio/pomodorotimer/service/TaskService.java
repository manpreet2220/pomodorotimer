package com.vibbio.pomodorotimer.service;

import com.vibbio.pomodorotimer.model.Task;

import java.util.List;

public interface TaskService {

  Integer saveTask(Task task);

  Task findTask(Integer taskId);

  Task findTaskByName(String taskName);

  List<Task> findAllTasks();
}
