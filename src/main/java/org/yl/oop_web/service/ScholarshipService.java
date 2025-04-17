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

    // Method to get all scholarships from the database
    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll(); // Fetch scholarships from the database
    }

    // Method to add a new scholarship
    public void addScholarship(Scholarship scholarship) {
        scholarshipRepository.save(scholarship); // Save the scholarship to the database
    }

    // Additional methods for update and delete can be added here
}