package org.yl.oop_web.service;

import org.yl.oop_web.model.SeminarWorkshop;
import org.yl.oop_web.repository.SeminarWorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeminarWorkshopService {

    @Autowired
    private SeminarWorkshopRepository seminarWorkshopRepository;

    public List<SeminarWorkshop> getAllSeminarsWorkshops() {
        return seminarWorkshopRepository.findAll();
    }

    public void addSeminarWorkshop(SeminarWorkshop seminarWorkshop) {
        seminarWorkshopRepository.save(seminarWorkshop);
    }

    public SeminarWorkshop getSeminarWorkshopById(Long id) {
        return seminarWorkshopRepository.findById(id).orElse(null);
    }
}