package com.dialexa.hrsp;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration()
public class HrspApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrspApplication.class, args);

        String url = System.getenv("HRSP_DB_URL");
        String user = System.getenv("HRSP_DB_USER");
        String password = System.getenv("HRSP_DB_KEY");
        
        Flyway flyway = Flyway.configure()
                              .dataSource(url, user, password)
                              .load();
        flyway.migrate();
    }
  

}

