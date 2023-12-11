package com.example.app.login_and_registration.http;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    @NotBlank
    @Size(min=4, message = "username must be at least 4 characters")
    private String username;
    @Email(message = "this is not a valid mail")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String confirmPassword;
    @NotEmpty
    @NotNull
    private String cin;
    @NotEmpty
    @NotNull
    @Size(min = 10, max = 10, message = "phone number must be contain 10 numbers")
    private String phoneNumber;
}