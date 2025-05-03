package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String course;

    @Column(columnDefinition = "TEXT")
    private String content;

    // This stores hashtags as one string (e.g. "life tips college")
    private String hashtag;

    @OneToMany(mappedBy = "advice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Optional utility method to extract hashtags as list
    public String[] getHashtagList() {
        return (hashtag != null) ? hashtag.trim().split("\\s+") : new String[0];
    }
}

