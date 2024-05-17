package com.edu.smartpersionsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.smartpersionsys.mapper")
public class SmartPersionSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartPersionSysApplication.class, args);
	}

}
