package com.quetz4l.getflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "actor")
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birthDate;
}