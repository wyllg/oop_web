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

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public void addInternship(Internship internship) {
        internshipRepository.save(internship); // Handles both add and update
    }

    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElse(null); // Fetch internship by ID
    }
}
