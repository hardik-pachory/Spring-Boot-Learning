package com.hardik;

// 1. The class should have the annotation SpringBootApplication to make that
//    class act as a Spring Boot Application Entry Point
// 2. The class should run the SpringBoot Application

// Spring Boot Application has three type of web apps - Servlet (Default) , Reactive, and None.
// None will not start any server.

// For any class to have REST EndPoints, the class should have the annotation
// of the RestController to mark it as the class having endpoint mappings.

// Jackson is the package responsible for converting the Java Objects into the JSON
// and vice versa.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Main.class, args);
        printBeans(configurableApplicationContext);
    }

    @Bean
    public Foo getFoo(){
        return new Foo("Bar");
    }

    record Foo(String name){}

    private static void printBeans(ConfigurableApplicationContext ctx){
        System.out.println("\n\n Printing the Beans \n\n");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
