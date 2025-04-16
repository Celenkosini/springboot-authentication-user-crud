package com.nyawo.user_management_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserManagementDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementDemoApplication.class, args);
	}

	@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
