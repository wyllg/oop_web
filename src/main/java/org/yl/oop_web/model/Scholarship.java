package org.yl.oop_web.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Scholarship {
    // Getters and Setters
    private String name;
    private String description;
    private String link;

    public Scholarship(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

}