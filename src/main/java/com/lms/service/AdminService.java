package com.lms.service;

import com.lms.dto.UserDto;
import com.lms.model.Role;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
    UserDto addInstructor(UserDto userDto);


    List<UserDto> getUsersByRole(Role role);
}
