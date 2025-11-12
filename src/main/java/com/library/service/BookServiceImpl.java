package com.library.service;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Comment;
import com.library.entities.Genre;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CommentRepository;
import com.library.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;
    private final CommentRepository commentRepo;

    public BookServiceImpl(BookRepository bookRepo,
                           AuthorRepository authorRepo,
                           GenreRepository genreRepo,
                           CommentRepository commentRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> listAll() {
        // use repository method that fetches author+genre to avoid N+1
        return bookRepo.findAllWithAuthorAndGenre();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    @Transactional
    public Book create(String title, Long authorId, Long genreId) {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found, id=" + authorId));
        Genre genre = genreRepo.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre not found, id=" + genreId));

        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setGenre(genre);
        return bookRepo.save(b);
    }

    @Override
    @Transactional
    public Book update(Long id, String title, Long authorId, Long genreId) {
        Book existing = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found, id=" + id));

        if (title != null && !title.isBlank()) {
            existing.setTitle(title);
        }

        if (authorId != null) {
            Author author = authorRepo.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found, id=" + authorId));
            existing.setAuthor(author);
        }

        if (genreId != null) {
            Genre genre = genreRepo.findById(genreId)
                    .orElseThrow(() -> new RuntimeException("Genre not found, id=" + genreId));
            existing.setGenre(genre);
        }

        return bookRepo.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found, id=" + id);
        }
        bookRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Comment addComment(Long bookId, String text) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found, id=" + bookId));
        Comment c = new Comment();
        c.setText(text);
        c.setBook(book);
        return commentRepo.save(c);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepo.existsById(commentId)) {
            throw new RuntimeException("Comment not found, id=" + commentId);
        }
        commentRepo.deleteById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> listComments(Long bookId) {
        // ensure book exists (optional)
        if (!bookRepo.existsById(bookId)) {
            throw new RuntimeException("Book not found, id=" + bookId);
        }
        return commentRepo.findAllByBookId(bookId);
    }
}

