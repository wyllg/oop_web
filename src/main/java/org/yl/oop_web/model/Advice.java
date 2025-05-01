package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Store hashtags as comma separated string or map to entity (simple approach here)
    @ElementCollection
    @CollectionTable(name="advice_hashtags", joinColumns=@JoinColumn(name="advice_id"))
    @Column(name="hashtag")
    private List<String> hashtags = new ArrayList<>();

    // Relationship with comments
    @OneToMany(mappedBy = "advice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Advice() {}

    public Advice(String name, String content) {
        this.name = name;
        this.content = content;
    }
}


