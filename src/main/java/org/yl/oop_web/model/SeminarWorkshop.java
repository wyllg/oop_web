package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // Add this annotation for a no-args constructor
@Entity
@Table(name = "seminar_workshop") // Change the table name as needed
public class SeminarWorkshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String date;
    private String link;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id") // Assuming a user can create a seminar/workshop
    private User user;

    // Constructor for easy instantiation
    public SeminarWorkshop(String title, String description, String date, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
        this.imagePath = "https://i.imgur.com/0000000.png"; // Default image path
    }
}