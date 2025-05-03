package org.yl.oop_web.repository;

import org.yl.oop_web.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    List<Advice> findByHashtagsContaining(String hashtag);
}
