package com.vibbio.pomodorotimer;

import com.vibbio.pomodorotimer.service.TaskService;
import com.vibbio.pomodorotimer.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PomodorotimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomodorotimerApplication.class, args);
	}

	@Bean
	public TaskService getTaskService() {
			return new TaskServiceImpl();
	}
}
