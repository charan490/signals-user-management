package com.kamadhenu.signalsusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Entry point for application
 */
@SpringBootApplication
@EnableJpaRepositories
public class SignalsUserManagementApplication {

    /**
     * Main entry method
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SignalsUserManagementApplication.class, args);
    }
}
