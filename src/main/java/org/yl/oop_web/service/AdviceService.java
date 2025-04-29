package org.yl.oop_web.service;

import org.yl.oop_web.model.Advice;
import org.yl.oop_web.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    public List<Advice> getAllAdvices() {
        return adviceRepository.findAll();
    }

    public void addAdvice(Advice advice) {
        adviceRepository.save(advice); // Save or update advice
    }
}
