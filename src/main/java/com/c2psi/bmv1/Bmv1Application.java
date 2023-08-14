package com.c2psi.bmv1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableJpaAuditing //To precise that we have some classes that spring must auditing.
@Slf4j
public class Bmv1Application {

	public static void main(String[] args) {

		SpringApplication.run(Bmv1Application.class, args);
	}

//	@Bean
//	public void test(){
//		log.info("Start the bean test");
//		//String nullString = "";//The string is empty and blank also
//		String nullString = "    ";//The string is blank only
//
//		if (nullString == null) {
//			log.warn("String is null");
//		}
//		else {
//			if (!StringUtils.hasLength(nullString)) {
//				log.warn("String is empty");
//			}
//
//			if (!StringUtils.hasLength(nullString.trim())) {
//				log.warn("String is blank {}", nullString.length());
//			}
//		}
//		log.info("End the bean test");
//	}

}
