package org.yl.oop_web.repository;

import org.yl.oop_web.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Additional query methods can be defined here if needed
}