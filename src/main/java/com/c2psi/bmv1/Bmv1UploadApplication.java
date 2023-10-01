package com.c2psi.bmv1;

import com.c2psi.bmv1.upload.exceptions.UploadDirectoriesNotCreatedException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@EnableJpaAuditing //To precise that we have some classes that spring must auditing.
@Slf4j
public class Bmv1UploadApplication {

	@Value("${user.dir}/img")
	public String photosDir;

	@PostConstruct
	void init(){
		log.info("Creation or verification of the folder in which image or file will be uploaded {}", photosDir);
		try {
			Files.createDirectories(Paths.get(photosDir));
			BMGlobalArguments.photosPersonsDir = photosDir;
		}
		catch (IOException e){
			throw new UploadDirectoriesNotCreatedException("Could not initialize folder for upload file!");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Bmv1UploadApplication.class, args);
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
