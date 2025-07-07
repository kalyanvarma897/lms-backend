package com.lms;

import com.lms.model.Role;
import com.lms.model.User;
import com.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByEmail("student@lms.com")){
            User user=new User();
            user.setEmail("student@lms.com");
            user.setName("Student User");
            user.setPassword("123456");
            user.setRole(Role.STUDENT);
            userRepository.save(user);

            System.out.println("Student user created: " + user.getEmail());

        }

    }
}
