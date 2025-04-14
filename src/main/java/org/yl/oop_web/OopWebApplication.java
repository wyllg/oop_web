package org.yl.oop_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yl.oop_web.util.EnvLoader;

@SpringBootApplication
public class OopWebApplication {

	public static void main(String[] args) {
		EnvLoader.load(".env");
		SpringApplication.run(OopWebApplication.class, args);


	}
asd
}