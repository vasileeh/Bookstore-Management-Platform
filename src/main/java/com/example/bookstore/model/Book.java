package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name="genre_id", nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private Double price;

    @Column
    private Integer stock;
}
