package com.avantica.proa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.avantica"})
public class ProaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProaApplication.class, args);
    }

}
