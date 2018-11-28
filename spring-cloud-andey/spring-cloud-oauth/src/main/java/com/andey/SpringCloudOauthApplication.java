package com.andey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.andey.dao")
public class SpringCloudOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOauthApplication.class, args);
	}

}
