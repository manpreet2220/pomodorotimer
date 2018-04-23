package com.vibbio.pomodorotimer.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibbio.pomodorotimer.model.Task;
import com.vibbio.pomodorotimer.service.TaskService;
import com.vibbio.pomodorotimer.service.TaskServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class TimerController {

  private Logger log = LogManager.getLogger(TaskServiceImpl.class);

  @Autowired
  TaskService taskService;

  @Autowired
  ObjectMapper objectMapper;

  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    Task task=new Task();
    task.setTaskName("");
    model.put("task",task);
    return "timer";
  }

   /**
    * This method save the task into database
   * @param task
   * @return ResponseEntity<?>
   */
  @PostMapping("/timer/task/save")
  public  ResponseEntity<?> saveTask( @ModelAttribute("task") Task task1,
                                     @Valid @RequestBody Task task) throws IOException {
    Integer taskId=taskService.saveTask(task);
    task.setTaskId(taskId);
    log.info("Task saved with taskId "+ task.getTaskId());
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("timer");
    modelAndView.addObject("task",task);
    return ResponseEntity.ok(task);
  }

  //This method gives list of all the task from database
  @RequestMapping("timer/task/list")
  public ResponseEntity<?> getListOfTasks(Model model){
    List<Task> taskList=taskService.findAllTasks();
    log.info("Task list size "+ taskList.size());
    model.addAttribute("taskList",taskList);
    return ResponseEntity.ok(taskList);
  }

}