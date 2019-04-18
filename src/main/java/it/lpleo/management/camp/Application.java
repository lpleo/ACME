package it.lpleo.management.camp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "production");
    SpringApplication.run(SecurityAutoConfiguration.class, args);
  }
}
