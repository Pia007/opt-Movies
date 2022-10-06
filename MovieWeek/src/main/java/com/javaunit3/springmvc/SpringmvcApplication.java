package com.javaunit3.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringmvcApplication.class, args);
    }
}

/*

Part2: Websites
Part2-1: Setup
  a: create templates directory inside of resources
  b: in com.javaunit3.springmvc folder create a new Java file called SpringmvcApplication
    -- annotate it with SpringBootApplication
    -- create a main method
        -- write out SpringApplication.run(SpringmvcApplication.class, args)
*/
