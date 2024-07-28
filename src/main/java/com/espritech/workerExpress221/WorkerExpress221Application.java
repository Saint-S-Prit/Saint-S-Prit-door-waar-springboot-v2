package com.espritech.workerExpress221;

import com.espritech.workerExpress221.config.ConfigProperties;
import com.espritech.workerExpress221.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WorkerExpress221Application {
	public static void main(String[] args) {
		SpringApplication.run(WorkerExpress221Application.class, args);
	}


}
