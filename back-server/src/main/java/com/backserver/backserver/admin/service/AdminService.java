package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.model.Admin;
import com.backserver.backserver.admin.repo.AdminRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepo adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepo adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAdminUser() {
        String adminUsername = "admin";
        String adminPassword = "adminPassword"; // You should encrypt this password before saving it.

        // Check if the admin user already exists
        if (adminRepository.findByUsername(adminUsername) == null) {
            Admin adminUser = new Admin();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));

            adminRepository.save(adminUser);
        }
    }
}