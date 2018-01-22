package org.roadservice.api.trafficsignal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResourceTrafficsignalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResourceTrafficsignalApplication.class, args);
	}

}
