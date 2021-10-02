package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringOpenshiftExampleApplication {

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Openshift";
	}
	
	@GetMapping("/{input}")
	public String getMessage(@PathVariable String input) {
		return "You have entered " + input + " message";
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringOpenshiftExampleApplication.class, args);
	}

}
