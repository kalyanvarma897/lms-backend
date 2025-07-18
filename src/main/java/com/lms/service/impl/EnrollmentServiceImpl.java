package com.lms.service.impl;

import com.lms.model.Course;
import com.lms.model.Enrollment;
import com.lms.model.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.EnrollmentRepository;
import com.lms.repository.UserRepository;
import com.lms.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    @Override
    public String enrollStudent(Long courseId) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        Course course= courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        boolean alreadyEnrolled= enrollmentRepository.existByStudentAndCourse(student, course);
        if (alreadyEnrolled) {
            throw new RuntimeException("Student already enrolled in this course");
        }
        Enrollment enrollment=Enrollment.builder()
                .student(student)
                .course(course)
                .build();
        enrollmentRepository.save(enrollment);

        return "Student enrolled successfully in course: " + course.getTitle();
    }

    @Override
    public List<Long> getEnrolledCoursesByStudent(String email) {
        User student = userRepository.findByEmail(email);
        if(student != null){
            List<Enrollment>enrollments = enrollmentRepository.findByStudent(student);
            return enrollments.stream()
                    .map(enrollment -> enrollment.getCourse().getId())
                    .collect(Collectors.toList());

        }
        return List.of();
    }
}
