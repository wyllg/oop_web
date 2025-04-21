package org.yl.oop_web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // Store the username of the applicant

    @ManyToOne
    @JoinColumn(name = "seminar_workshop_id", nullable = false)
    private SeminarWorkshop seminarWorkshop; // Reference to the seminar/workshop

    // Default constructor
    public Application() {
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

    public SeminarWorkshop getSeminarWorkshop() {
        return seminarWorkshop;
    }

    public void setSeminarWorkshop(SeminarWorkshop seminarWorkshop) {
        this.seminarWorkshop = seminarWorkshop;
    }
}