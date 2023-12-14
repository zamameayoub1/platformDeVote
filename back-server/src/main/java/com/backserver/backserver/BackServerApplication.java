package com.backserver.backserver;

import com.backserver.backserver.admin.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackServerApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(AdminService userService) {
//        return args -> {
//            userService.createAdminUser();
//        };
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
