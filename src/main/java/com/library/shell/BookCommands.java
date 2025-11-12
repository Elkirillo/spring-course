package com.library.shell;

import com.library.domain.Book;
import com.library.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("List all books")
    public List<Book> listBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod("Add new book")
    public String addBook(@ShellOption String title, @ShellOption Long authorId, @ShellOption Long genreId) {
        bookService.addBook(new Book(null, title, authorId, genreId));
        return "Book added";
    }

    @ShellMethod("Delete book by id")
    public String deleteBook(@ShellOption Long id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }

    @ShellMethod("Update book")
    public String updateBook(@ShellOption Long id, @ShellOption String title,
                             @ShellOption Long authorId, @ShellOption Long genreId) {
        bookService.updateBook(new Book(id, title, authorId, genreId));
        return "Book updated";
    }
}