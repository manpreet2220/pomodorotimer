package com.vibbio.pomodorotimer.model;

import javax.persistence.*;

@Entity
@Table
public class Task {

  @SequenceGenerator(name = "TASK_ID_SEQ", sequenceName = "TASK_ID_SEQ",initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_SEQ")
  @Id
  private Integer taskId;
  private String taskName;
  private String timeLogged;

  public Task() {
  }

  public Task(String taskName, String timeLogged) {
    this.taskName = taskName;
    this.timeLogged = timeLogged;
  }

  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTimeLogged() {
    return timeLogged;
  }

  public void setTimeLogged(String timeLogged) {
    this.timeLogged = timeLogged;
  }
}
