package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // Add this annotation for a no-args constructor
@Entity
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String link;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor for easy instantiation
    public Scholarship(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.imagePath = "https://i.imgur.com/0000000.png";
    }
}