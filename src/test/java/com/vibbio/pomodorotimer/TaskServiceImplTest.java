package com.vibbio.pomodorotimer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibbio.pomodorotimer.model.Task;
import com.vibbio.pomodorotimer.repository.TaskRepository;
import com.vibbio.pomodorotimer.service.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

  @Autowired
  ObjectMapper mapper;

  private TaskServiceImpl taskService;

   @Autowired
  TaskRepository taskRepository;

  @Before
  public void setUp() throws Exception {
    taskService = new TaskServiceImpl();
    taskService.setTaskRepository(taskRepository);
  }

  @Test
  public void saveTaskTest(){
    Task task=new Task("Task 1","2 sec");
   Integer taskId= taskService.saveTask(task);
    assertNotNull(task);
    assertNotNull(taskId);
  }

  @Test
  public void getListOfTaskTest(){
    List<Task> taskList = taskService.findAllTasks();
    assertNotNull(taskList);
  }
}
