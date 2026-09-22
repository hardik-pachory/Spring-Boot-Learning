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

// After creating the docker-compose file for the project, the following are some useful commands -
// 1. "docker compose up -d" : creates and starts the service in the docker file in "detached" mode
//                             that means containers run in the background and your terminal remains usable.
// 2. "docker compose ps" : ps stands for Process Status. It simply asks docker "What is the current state of the containers belonging to this Compose project?"
// 3. "docker exec -it postgres bash" : Open an interactive Bash terminal inside my already-running postgres container. 'postgres' is the name of the container that we made
// 4. "psql -U hardikpac" : This is to switch to your user rather than using the root.


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Main.class, args);
//        printBeans(configurableApplicationContext);
    }

    private static void printBeans(ConfigurableApplicationContext ctx){
        System.out.println("\n\n Printing the Beans \n\n");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
