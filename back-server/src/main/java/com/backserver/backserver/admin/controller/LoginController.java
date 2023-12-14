package com.backserver.backserver.admin.controller;

import com.backserver.backserver.admin.http.AdminLoginRequest;
import com.backserver.backserver.admin.model.Admin;
import com.backserver.backserver.admin.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final AdminRepo adminRepository;

    @Autowired
    public LoginController(AdminRepo adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest adminLoginRequest) {
        String username = adminLoginRequest.getUsername();
        String password = adminLoginRequest.getPassword();

        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            // Successful login
            return ResponseEntity.ok("Successfully logged in");
        } else {
            // Failed login
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
}

