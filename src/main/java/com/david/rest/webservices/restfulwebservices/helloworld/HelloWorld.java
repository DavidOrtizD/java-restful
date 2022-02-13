package com.david.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping(path="/hello-world-bean") 
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping(path="/hello-world-bean/{name}") 
	public HelloWorldBean helloWorldBean(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello World Bean, %s", name));
	}
}
