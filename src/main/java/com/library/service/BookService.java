package com.library.service;

import com.library.entities.Book;
import com.library.entities.Comment;

import java.util.List;
import java.util.Optional;


public interface BookService {
    Book save(Book book);                     // Создать или обновить книгу
    Optional<Book> findById(Long id);        // Найти книгу по ID с комментариями
    List<Book> findAll();                     // Получить все книги с автором и жанром
    void delete(Long id);                     // Удалить книгу по ID
}