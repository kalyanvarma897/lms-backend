package com.lms.controller;

import com.lms.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enroll")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> enrollInCourse(@PathVariable Long courseId){
        String message = enrollmentService.enrollStudent(courseId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Long>> getEnrolledCourses() {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        List<Long> courseIds = enrollmentService.getEnrolledCoursesByStudent(email);
        return ResponseEntity.ok(courseIds);

    }
}
