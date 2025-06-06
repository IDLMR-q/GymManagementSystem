package com.example.gymmanagementsystem.service;

import com.example.gymmanagementsystem.entity.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAllCourses();
    Optional<Course> findById(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
}
