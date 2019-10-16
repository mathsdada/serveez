package com.loopkillers.serveez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServeezApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeezApplication.class, args);
	}

}
