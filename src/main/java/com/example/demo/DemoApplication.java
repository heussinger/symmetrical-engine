package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/saysomething")
	public Message getMethodName(@RequestParam(defaultValue="") String name, @RequestParam(defaultValue="") String tchStck) {
		return new Message("Your name is " + name + " and your tech stack is (" + tchStck + ")");    
	}
	
	@GetMapping
	public Message helloName(@RequestParam (defaultValue="World") String name) {
		return new Message("Hello, " + name);
	}

}
