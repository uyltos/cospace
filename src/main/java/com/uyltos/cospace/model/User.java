package com.uyltos.cospace.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    private String firstName;
    private String lastName;
    private String post;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true) private String email;
}
