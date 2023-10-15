package com.dva.springcolud.msvc.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcUusarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcUusarioApplication.class, args);
	}

}
