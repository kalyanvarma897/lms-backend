package com.lms.service;

import com.lms.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);
    CourseDto getCourseById(Long id);
    List<CourseDto> getAllCourses();
}
