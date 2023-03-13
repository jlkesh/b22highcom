package dev.jlkeesh.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Column(nullable = false)
    private String message;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;

}


