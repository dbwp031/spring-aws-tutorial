package com.dbwp031.springaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class SpringawsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringawsApplication.class, args);
	}

}
