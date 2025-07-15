package com.lms.service.impl;

import com.lms.dto.CourseDto;
import com.lms.model.Course;
import com.lms.model.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.UserRepository;
import com.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User instructor = userRepository.findByEmail(email);
        if (instructor == null) {
            throw new RuntimeException("Instructor not found");
        }
        Course course=Course.builder()
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .instructor(instructor)
                .build();
        Course savedCourse = courseRepository.save(course);


        return mapToDto(savedCourse);
    }

    private CourseDto mapToDto(Course savedCourse) {
        return CourseDto.builder()
                .id(savedCourse.getId())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .instructorName(savedCourse.getInstructor().getEmail())
                .build();
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return mapToDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses=courseRepository.findAll();
        List<CourseDto> courseDto= new ArrayList<>();
        for(Course course :courses){
            CourseDto dto = mapToDto(course);
            courseDto.add(dto);

        }
        return courseDto;
    }
}
