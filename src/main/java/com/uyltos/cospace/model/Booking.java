package com.uyltos.cospace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @ManyToOne private User user;
    @ManyToOne private Space space;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
}
