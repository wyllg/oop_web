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
    private String location;
    @Column(length = 3000)
    private String description;
    @Column(length = 600)
    private String link;
    @Column(length = 600)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor for easy instantiation
    public Internship(String title, String company, String description, String link) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.link = link;
        this.imagePath = "https://i.imgur.com/0000000.png";
    }
}
