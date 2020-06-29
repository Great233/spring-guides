package com.example.authenticatingldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Great
 */
@SpringBootApplication
@EnableConfigurationProperties
public class AuthenticatingLdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatingLdapApplication.class, args);
    }

}
