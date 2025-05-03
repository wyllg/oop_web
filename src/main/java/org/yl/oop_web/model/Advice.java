package org.yl.oop_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name = "advice_hashtags", joinColumns = @JoinColumn(name = "advice_id"))
    @Column(name = "hashtag")
    private List<String> hashtags = new ArrayList<>();

    public static List<String> extractHashtags(String content) {
        if (content == null) return List.of();
        return Arrays.stream(content.split("\\s+"))
                .filter(word -> word.startsWith("#"))
                .map(word -> word.replaceAll("[^a-zA-Z0-9#]", ""))
                .collect(Collectors.toList());
    }
}

