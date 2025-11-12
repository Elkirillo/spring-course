package com.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genres")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    public Genre() {} // нужен JPA

    public Genre(Long id, String name) { // этот конструктор для тестов
        this.id = id;
        this.name = name;
    }

}
