package com.lms.repository;

import com.lms.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Additional query methods can be defined here if needed
}
