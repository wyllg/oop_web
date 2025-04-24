package org.yl.oop_web.repository;

import org.yl.oop_web.model.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
    // Custom query methods can be added here if needed
}
