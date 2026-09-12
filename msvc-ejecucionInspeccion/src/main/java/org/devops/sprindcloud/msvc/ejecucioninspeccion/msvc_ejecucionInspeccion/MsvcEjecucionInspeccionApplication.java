package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcEjecucionInspeccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcEjecucionInspeccionApplication.class, args);
	}

}