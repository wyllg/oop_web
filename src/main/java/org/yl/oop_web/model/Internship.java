package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // No-args constructor
@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String description;
    private String link;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor for easy instantiation
    public Internship(String title, String company, String description, String link) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.link = link;
        this.imagePath = "https://i.imgur.com/0000000.png"; // Replace with actual default image path if needed
    }
}
