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

    public List<Advice> searchAdvicesByHashtag(String hashtag) {
        return adviceRepository.findByHashtagsContaining(hashtag);
    }

    // Other methods remain unchanged
}
