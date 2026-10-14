package com.example.session3.modals.services;

import com.example.session3.modals.entities.Course;
import com.example.session3.modals.repositories.CourseRepository;
import com.example.session3.modals.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khóa học với ID: " + id));
    }

    public Course createCourse(Course course) {
        if (course.getInstructorId() == null
                || instructorRepository.findById(course.getInstructorId()).isEmpty()) {
            throw new IllegalArgumentException("Giảng viên không tồn tại hoặc dữ liệu thiếu");
        }
        return courseRepository.create(course);
    }

    public Course updateCourse(Long id, Course course) {
        if (course.getInstructorId() != null
                && instructorRepository.findById(course.getInstructorId()).isEmpty()) {
            throw new IllegalArgumentException("Giảng viên không tồn tại");
        }
        return courseRepository.update(id, course);
    }

    public Course deleteCourseById(Long id) {
        return courseRepository.deleteById(id);
    }
}
