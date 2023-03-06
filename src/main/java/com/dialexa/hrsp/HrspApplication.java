package com.dialexa.hrsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.dialexa.hrsp.config.FlywayConfig;

@SpringBootApplication
@EnableAutoConfiguration()
@Import(FlywayConfig.class)
public class HrspApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrspApplication.class, args)
    }
  

}

