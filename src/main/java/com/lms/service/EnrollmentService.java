package com.lms.service;

import java.util.List;

public interface EnrollmentService {
    String enrollStudent(Long courseId);
    List<Long> getEnrolledCoursesByStudent(String email);
}
