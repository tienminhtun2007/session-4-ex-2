package com.example.session3.modals.repositories;

import com.example.session3.modals.entities.Course;
import com.example.session3.modals.entities.CourseStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1L, "Lap trinh Java Co ban", CourseStatus.ACTIVE, 1L));
        courses.add(new Course(2L, "Spring Boot Web Development", CourseStatus.PENDING, 2L));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course course) {
        if (course.getId() == null) {
            long nextId = courses.stream()
                    .mapToLong(Course::getId)
                    .max()
                    .orElse(0L) + 1;
            course.setId(nextId);
        }
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course updatedCourse) {
        Course existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khóa học với ID: " + id));
        existing.setTitle(updatedCourse.getTitle());
        existing.setStatus(updatedCourse.getStatus());
        existing.setInstructorId(updatedCourse.getInstructorId());
        return existing;
    }

    public Course deleteById(Long id) {
        Course existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khóa học với ID: " + id));
        courses.remove(existing);
        return existing;
    }
}
