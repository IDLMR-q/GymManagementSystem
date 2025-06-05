package com.example.gymmanagementsystem.controller;

import com.example.gymmanagementsystem.entity.Course;
import com.example.gymmanagementsystem.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "course_list";
    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        courseService.findById(id).ifPresent(course -> model.addAttribute("course", course));
        return "course_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
