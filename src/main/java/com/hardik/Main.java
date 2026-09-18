package com.hardik;

// 1. The class should have the annotation SpringBootApplication to make that
//    class act as a Spring Boot Application Entry Point
// 2. The class should run the SpringBoot Application

// Spring Boot Application has three type of web apps - Servlet (Default) , Reactive, and None.
// None will not start any server.

// For any class to have REST EndPoints, the class should have the annotation
// of the RestController to mark it as the class having endpoint mappings.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public GreetResponse greet(){
//    public String greet(){
//        return "Welcome to Your SpringBoot App";
          return new GreetResponse("Welcome to Your First Spring Boot Application");
//        To Return a JSON Object, we can do Something like -
    }

    record GreetResponse(String greet){}
}
