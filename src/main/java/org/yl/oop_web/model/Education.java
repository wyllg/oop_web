package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;
    private String collegeProgram;
    private String yearLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
