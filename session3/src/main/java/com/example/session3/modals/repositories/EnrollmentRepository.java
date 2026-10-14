package com.example.session3.modals.repositories;

import com.example.session3.modals.entities.StudentEnrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Repository
public class EnrollmentRepository {
    private final List<StudentEnrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new StudentEnrollment(1L, "Le Van C", 1L));
        enrollments.add(new StudentEnrollment(2L, "Pham Thi D", 2L));
    }

    public List<StudentEnrollment> findAll() {
        return enrollments;
    }

    public Optional<StudentEnrollment> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst();
    }

    public StudentEnrollment create(StudentEnrollment enrollment) {
        if (enrollment.getId() == null) {
            long nextId = enrollments.stream()
                    .mapToLong(StudentEnrollment::getId)
                    .max()
                    .orElse(0L) + 1;
            enrollment.setId(nextId);
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    public StudentEnrollment update(Long id, StudentEnrollment updatedEnrollment) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy lượt đăng ký với ID: " + id));
        existing.setStudentName(updatedEnrollment.getStudentName());
        existing.setCourseId(updatedEnrollment.getCourseId());
        return existing;
    }

    public StudentEnrollment deleteById(Long id) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy lượt đăng ký với ID: " + id));
        enrollments.remove(existing);
        return existing;
    }
}
