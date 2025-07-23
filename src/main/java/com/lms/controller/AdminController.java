package com.lms.controller;

import com.lms.dto.UserDto;
import com.lms.model.Role;
import com.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/add-instructor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addInstructor(@RequestBody UserDto userDto) {
        UserDto createdUser = adminService.addInstructor(userDto);
        return ResponseEntity.ok("Instructor added successfully: " + createdUser.getEmail());
    }

    @GetMapping("/users-by-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getUsersByRole(@RequestParam String role) {
        List<UserDto> users = adminService.getUsersByRole(Role.valueOf(role));
        return ResponseEntity.ok(users);
    }

}
