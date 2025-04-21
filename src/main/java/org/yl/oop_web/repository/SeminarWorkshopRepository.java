package org.yl.oop_web.repository;

import org.yl.oop_web.model.SeminarWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeminarWorkshopRepository extends JpaRepository<SeminarWorkshop, Long> {
    // Additional query methods can be defined here if needed
}