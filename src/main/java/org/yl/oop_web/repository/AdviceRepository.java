package org.yl.oop_web.repository;

import org.yl.oop_web.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    // You can add custom queries here if needed
}
