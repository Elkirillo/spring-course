package com.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;
    public Author(Long id, String name) { // этот конструктор для тестов
        this.id = id;
        this.name = name;
    }

    public Author() {

    }
}
