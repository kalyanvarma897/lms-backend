package com.lms.dto;

import com.lms.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private Role role; // "STUDENT" or "INSTRUCTOR"
}
