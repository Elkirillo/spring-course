package com.library.dao;

import com.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void insert(Book book);
    void update(Book book);
    void deleteById(Long id);
}
