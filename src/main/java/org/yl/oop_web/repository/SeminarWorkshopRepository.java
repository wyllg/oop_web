package org.yl.oop_web.repository;

import org.yl.oop_web.model.SeminarWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarWorkshopRepository extends JpaRepository<SeminarWorkshop, Long> {
    // You can add custom query methods here if needed
}