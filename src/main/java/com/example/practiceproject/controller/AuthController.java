package com.example.practiceproject.controller;

import com.example.practiceproject.dto.JwtRequest;
import com.example.practiceproject.dto.RegistrationUserDto;
import com.example.practiceproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * AuthController handles authentication and user registration requests.
 * It exposes endpoints for creating JWT tokens and registering new users.
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Handles POST requests to the /auth endpoint 
     * for creating authentication tokens.
     *
     * @param authRequest the JWT request containing username and password
     * @return a ResponseEntity containing the authentication token
     */
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(
        @RequestBody JwtRequest authRequest
    ) {
        final ResponseEntity<?> responseEntity = authService.createAuthToken(
            authRequest
            );
        System.out.println("Response Entity: " + responseEntity);
        System.out.println(
            "Response Status Code: " + responseEntity.getStatusCode()
        );
        System.out.println("Response Body: " + responseEntity.getBody());
        System.out.println("Response Headers: " + responseEntity.getHeaders());
        if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(
                HttpStatus.UNAUTHORIZED
                ).body(
                "Invalid credentials"
                );
        }
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(responseEntity.getBody());
        }
        return ResponseEntity.status(
            HttpStatus.INTERNAL_SERVER_ERROR
            ).body(
                "An error occurred while processing the request"
            );
    }

    /**
     * Handles POST requests to the /registration endpoint
     *  for creating new users.
     * It also creates a storage directory for the user if 
     * it doesn't already exist.
     *
     * @param registrationUserDto the user registration details
     * @return a ResponseEntity indicating success or failure 
     * of user registration
     */
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(
        @RequestBody RegistrationUserDto registrationUserDto
    ) {
        final String projectPath = System.getProperty("user.dir");
        final File targetDirectory = new File(
            projectPath + 
            File.separator + "storage" + 
            File.separator + registrationUserDto.getUsername()
            );

        if (!targetDirectory.exists()) {
            targetDirectory.mkdir();

            return authService.createNewUser(registrationUserDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
