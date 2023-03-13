package dev.jlkeesh.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String overview;
    @Column(nullable = false)
    private String content;
    @OneToOne(targetEntity = Upload.class, cascade = CascadeType.ALL)
    private Upload pic;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private String createdBy;
}
