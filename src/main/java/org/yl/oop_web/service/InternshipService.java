package org.yl.oop_web.service;

import org.yl.oop_web.model.Internship;
import org.yl.oop_web.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    // Get all internships
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    // Add or update an internship
    public void addInternship(Internship internship) {
        internshipRepository.save(internship); // Handles both add and update
    }

    // Get internship by ID
    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElse(null); // Fetch internship by ID
    }
}
