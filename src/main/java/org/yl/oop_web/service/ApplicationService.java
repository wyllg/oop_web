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
}