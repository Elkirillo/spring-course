package com.library.service;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Comment;
import com.library.entities.Genre;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CommentRepository;
import com.library.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAllWithAuthorAndGenre();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findByIdWithComments(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

