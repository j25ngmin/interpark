package com.interpark.ncl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class RecommendProductApplication {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(RecommendProductApplication.class, args);
    }

}
