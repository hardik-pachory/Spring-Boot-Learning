package com.hardik;

// 1. The class should have the annotation SpringBootApplication to make that
//    class act as a Spring Boot Application Entry Point
// 2. The class should run the SpringBoot Application

// Spring Boot Application has three type of web apps - Servlet (Default) , Reactive, and None.
// None will not start any server.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(Main.class, args);
    }
}
