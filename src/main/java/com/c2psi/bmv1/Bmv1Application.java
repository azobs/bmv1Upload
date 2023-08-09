package com.c2psi.bmv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //To precise that we have some classes that spring must auditing.
public class Bmv1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bmv1Application.class, args);
	}

}
