package org.yl.oop_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public void addAdvice(Advice advice) {
        List<String> tags = Advice.extractHashtags(advice.getContent());
        advice.setHashtags(tags != null ? tags : List.of());
        adviceRepository.save(advice);
    }

    @Transactional
    public void deleteAdviceById(Long id) {
        adviceRepository.deleteById(id);
    }
}

