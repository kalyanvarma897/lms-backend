package com.lms.controller;

import com.lms.dto.LoginRequest;
import com.lms.dto.SignUpRequest;
import com.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest request){
        try{
            String token =authService.registerUser(request);
            return ResponseEntity.ok(token);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request){
        try{
            String token=authService.loginUser(request);
            return ResponseEntity.ok(token);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
