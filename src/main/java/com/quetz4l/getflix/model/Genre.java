package com.quetz4l.getflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "genre")
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany()
    private List<Movie> movies;
}
