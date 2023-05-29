package com.blogpost.restfullapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="images")
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;

   private String name;
    // Getters and Setters
}