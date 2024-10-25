package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        System.out.println("Hello from server!");
        SpringApplication.run(Server.class,args);

    }
}
