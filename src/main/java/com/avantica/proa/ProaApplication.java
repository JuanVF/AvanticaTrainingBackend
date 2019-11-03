package com.avantica.proa;

import com.avantica.proa.OAuth2.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.avantica"})
@EnableConfigurationProperties(AppProperties.class)
public class ProaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProaApplication.class, args);
    }

}
