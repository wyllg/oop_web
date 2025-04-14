package org.yl.oop_web.service;

import org.yl.oop_web.model.Scholarship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScholarshipService {

    public List<Scholarship> getAllScholarships() {
        List<Scholarship> scholarships = new ArrayList<>();

        scholarships.add(new Scholarship("Scholarship Name 1", "Description of Scholarship 1.", "https://www.example.com/scholarship1"));
        scholarships.add(new Scholarship("Scholarship Name 2", "Description of Scholarship 2.", "https://www.example.com/scholarship2"));
        scholarships.add(new Scholarship("Scholarship Name 3", "Description of Scholarship 3.", "https://www.example.com/scholarship3"));

        // Add more scholarships as needed

        return scholarships;
    }
}