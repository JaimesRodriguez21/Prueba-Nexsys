package com.nexsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nexsys")
public class PruebaNexsysApplication {
    public static void main(String[] args) {
        SpringApplication.run(PruebaNexsysApplication.class, args);
    }

}
