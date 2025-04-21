package org.yl.oop_web.service;

import org.yl.oop_web.model.Application;
import org.yl.oop_web.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void apply(Application application) {
        applicationRepository.save(application);
    }

    // New method to check if the user has already applied for the seminar/workshop
    public boolean hasApplied(String username, Long seminarWorkshopId) {
        return applicationRepository.findAll().stream()
                .anyMatch(application -> application.getUsername().equals(username) &&
                        application.getSeminarWorkshop().getId().equals(seminarWorkshopId));
    }
}