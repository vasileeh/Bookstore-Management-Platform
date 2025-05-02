package com.example.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
    private List<Order> orders;
}
