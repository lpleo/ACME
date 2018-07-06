package it.lpleo.management.camp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunnerDevelopment {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "development");
		SpringApplication.run(Application.class, args);
	}
}