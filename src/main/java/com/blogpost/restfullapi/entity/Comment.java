package com.blogpost.restfullapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "comments"
)
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "name")
    private String name;

    private String email;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)//lazy loading
    @JoinColumn(name = "post_id", nullable = false)//relation
    private Post post; //many comments to one post
}
