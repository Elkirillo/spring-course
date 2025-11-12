package com.library.service;

import com.library.dao.BookDao;
import com.library.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    public BookServiceImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAllBooks() { return dao.findAll(); }

    @Override
    public Book getBook(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void addBook(Book book) { dao.insert(book); }

    @Override
    public void updateBook(Book book) { dao.update(book); }

    @Override
    public void deleteBook(Long id) { dao.deleteById(id); }
}

