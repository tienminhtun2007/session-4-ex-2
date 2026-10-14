package com.example.session3.modals.services;

import com.example.session3.modals.entities.Instructors;
import com.example.session3.modals.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructors> findAll() {
        return instructorRepository.findAll();
    }

    public Instructors getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giảng viên với ID: " + id));
    }

    public Instructors createInstructor(Instructors instructor) {
        return instructorRepository.create(instructor);
    }

    public Instructors updateInstructor(Long id, Instructors instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructors deleteInstructorById(Long id) {
        return instructorRepository.deleteById(id);
    }
}
