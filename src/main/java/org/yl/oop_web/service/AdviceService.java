package org.yl.oop_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yl.oop_web.model.Advice;
import org.yl.oop_web.repository.AdviceRepository;

import java.util.List;

@Service
public class AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    public List<Advice> getAllAdvices() {
        return adviceRepository.findAll();
    }

    public Advice saveAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }

    public void deleteAdvice(Long id) {
        adviceRepository.deleteById(id);
    }
}