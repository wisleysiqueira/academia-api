package com.example.wisley.academia.api;

import com.example.wisley.academia.api.config.property.AcademiaApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AcademiaApiProperty.class)
public class AcademiaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApiApplication.class, args);
	}

}
