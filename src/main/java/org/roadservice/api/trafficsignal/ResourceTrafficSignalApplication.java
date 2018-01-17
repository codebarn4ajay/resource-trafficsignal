package org.roadservice.api.trafficsignal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResourceTrafficSignalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResourceTrafficSignalApplication.class, args);
	}
}
