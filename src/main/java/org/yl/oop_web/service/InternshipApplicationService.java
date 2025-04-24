package org.yl.oop_web.service;

import org.yl.oop_web.model.InternshipApplication;
import org.yl.oop_web.repository.InternshipApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternshipApplicationService {

    @Autowired
    private InternshipApplicationRepository internshipApplicationRepository;

    // Method to apply for an internship
    public void applyForInternship(InternshipApplication internshipApplication) {
        internshipApplicationRepository.save(internshipApplication);
    }

    // Method to check if the user has already applied for the internship
    public boolean hasApplied(String username, Long internshipId) {
        return internshipApplicationRepository.findAll().stream()
                .anyMatch(application -> application.getUsername().equals(username) &&
                        application.getInternship().getId().equals(internshipId));
    }
}
