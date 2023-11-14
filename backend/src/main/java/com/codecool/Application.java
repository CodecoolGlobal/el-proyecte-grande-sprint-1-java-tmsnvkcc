package com.codecool;

import com.codecool.postgresDb.PsqlConnector;
import com.codecool.postgresDb.PsqlConnectorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public PsqlConnector psqlConnector() {
    return new PsqlConnectorImpl();
  }
}
