package com.backserver.backserver.admin.service;

import com.backserver.backserver.admin.Dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<UserDTO> users = new ArrayList<>();
    public boolean loginUser(UserDTO userDTO) {
        for (UserDTO existingUser : users) {
            if (existingUser.getUsername().equals(userDTO.getUsername())
                    && existingUser.getPassword().equals(userDTO.getPassword())) {
                return true; // Successful login
            }
        }
        return false;
    }

    public void registerUser(UserDTO userDTO) {
        for (UserDTO existingUser : users) {
            if (existingUser.getUsername().equals(userDTO.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
        }
        users.add(userDTO);
    }
}
