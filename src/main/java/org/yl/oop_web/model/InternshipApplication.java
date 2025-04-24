package org.yl.oop_web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "internship_application")
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // Store the username of the applicant

    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship; // Reference to the internship being applied for

    // Default constructor
    public InternshipApplication() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }
}
