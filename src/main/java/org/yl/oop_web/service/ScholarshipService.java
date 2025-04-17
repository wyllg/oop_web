package org.yl.oop_web.service;

import org.yl.oop_web.model.Scholarship;
import org.yl.oop_web.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public void addScholarship(Scholarship scholarship) {
        scholarshipRepository.save(scholarship); // This will handle both add and update
    }

    public Scholarship getScholarshipById(Long id) {
        return scholarshipRepository.findById(id).orElse(null); // Fetch scholarship by ID
    }
}