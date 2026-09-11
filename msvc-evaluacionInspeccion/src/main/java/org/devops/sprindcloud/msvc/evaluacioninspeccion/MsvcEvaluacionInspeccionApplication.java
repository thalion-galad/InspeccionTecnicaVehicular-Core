package org.devops.sprindcloud.msvc.evaluacioninspeccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcEvaluacionInspeccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				MsvcEvaluacionInspeccionApplication.class,
				args
		);
	}
}