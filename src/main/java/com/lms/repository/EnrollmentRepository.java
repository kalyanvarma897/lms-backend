package com.lms.repository;

import com.lms.model.Course;
import com.lms.model.Enrollment;
import com.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Additional query methods can be defined here if needed
    boolean existsByStudentAndCourse(User student, Course course);
    List<Enrollment> findByStudent(User student);
}
