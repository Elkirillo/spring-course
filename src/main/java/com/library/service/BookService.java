package com.library.service;

import com.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBook(Long id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Long id);
}
