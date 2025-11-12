package com.library.service;

import com.library.entities.Book;
import com.library.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    Optional<Book> findById(Long id);

    /**
     * Return saved book (with id)
     */
    Book create(String title, Long authorId, Long genreId);

    Book update(Long id, String title, Long authorId, Long genreId);

    void delete(Long id);

    // comments
    Comment addComment(Long bookId, String text);

    void deleteComment(Long commentId);

    List<Comment> listComments(Long bookId);
}