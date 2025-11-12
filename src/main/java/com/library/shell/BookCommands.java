package com.library.shell;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Comment;
import com.library.entities.Genre;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    public void createBook(String title, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        bookService.save(book);
        System.out.println("Book created: " + book.getTitle());
    }

    public void listBooks() {
        List<Book> books = bookService.findAll();
        books.forEach(b ->
                System.out.println(
                        "Book: " + b.getTitle() +
                                ", Author: " + b.getAuthor().getName() +
                                ", Genre: " + b.getGenre().getName()
                )
        );
    }

    public void deleteBook(Long id) {
        bookService.delete(id);
        System.out.println("Book deleted with id: " + id);
    }

    public void showBook(Long id) {
        bookService.findById(id).ifPresentOrElse(
                b -> {
                    System.out.println("Book: " + b.getTitle());
                    System.out.println("Author: " + b.getAuthor().getName());
                    System.out.println("Genre: " + b.getGenre().getName());
                    System.out.println("Comments:");
                    b.getComments().forEach(c -> System.out.println(" - " + c.getText()));
                },
                () -> System.out.println("Book not found with id: " + id)
        );
    }
}