package com.mcwl.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.mcwl.authentication.repository")
//@EntityScan(basePackages = "com.mcwl.authentication.entity")
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
