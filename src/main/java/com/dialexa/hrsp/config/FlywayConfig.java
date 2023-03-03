package com.dialexa.hrsp.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Value("${HRSP_DB_URL}")
    private String url;

    @Value("${HRSP_DB_USER}")
    private String user;

    @Value("${HRSP_DB_KEY}")
    private String password;

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                              .dataSource(url,user,password)
                              .load();
        flyway.migrate();
        return flyway;
    }

}