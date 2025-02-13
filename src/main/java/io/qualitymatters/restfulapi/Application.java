package io.qualitymatters.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts a spring boot servlet container.
 * 
 * Run ./mvnw clean spring-boot:run to kick start the servlet.
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
