package com.library.shell;

import com.library.entities.Book;
import com.library.entities.Comment;
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

    // ---------- BOOK COMMANDS ----------

    @ShellMethod(value = "List all books", key = {"books", "list-books"})
    public String listBooks() {
        List<Book> books = bookService.listAll();
        if (books.isEmpty()) {
            return "📚 No books found.";
        }

        StringBuilder sb = new StringBuilder("Books:\n");
        for (Book b : books) {
            sb.append(String.format(" - #%d: \"%s\" (Author: %s, Genre: %s)\n",
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor() != null ? b.getAuthor().getName() : "unknown",
                    b.getGenre() != null ? b.getGenre().getName() : "unknown"));
        }
        return sb.toString();
    }

    @ShellMethod(value = "Find book by id", key = {"book", "find-book"})
    public String findBook(@ShellOption long id) {
        return bookService.findById(id)
                .map(b -> String.format("Book #%d: \"%s\" (Author: %s, Genre: %s)",
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor() != null ? b.getAuthor().getName() : "unknown",
                        b.getGenre() != null ? b.getGenre().getName() : "unknown"))
                .orElse("Book not found.");
    }

    @ShellMethod(value = "Create book", key = {"add-book", "create-book"})
    public String createBook(
            @ShellOption String title,
            @ShellOption long authorId,
            @ShellOption long genreId
    ) {
        Book created = bookService.create(title, authorId, genreId);
        return "Created book: " + created.getId() + " — " + created.getTitle();
    }

    @ShellMethod(value = "Update existing book", key = {"update-book"})
    public String updateBook(
            @ShellOption long id,
            @ShellOption(defaultValue = ShellOption.NULL) String title,
            @ShellOption(defaultValue = "-1") long authorId,
            @ShellOption(defaultValue = "-1") long genreId
    ) {
        Long aId = authorId > 0 ? authorId : null;
        Long gId = genreId > 0 ? genreId : null;

        Book updated = bookService.update(id, title, aId, gId);
        return "Updated book: " + updated.getId() + " — " + updated.getTitle();
    }

    @ShellMethod(value = "Delete book", key = {"delete-book", "remove-book"})
    public String deleteBook(@ShellOption long id) {
        bookService.delete(id);
        return "Book deleted: #" + id;
    }

    // ---------- COMMENT COMMANDS ----------

    @ShellMethod(value = "Add comment to book", key = {"add-comment"})
    public String addComment(@ShellOption long bookId, @ShellOption String text) {
        Comment comment = bookService.addComment(bookId, text);
        return String.format("Added comment #%d to book #%d: \"%s\"",
                comment.getId(), bookId, text);
    }

    @ShellMethod(value = "List comments for a book", key = {"comments"})
    public String listComments(@ShellOption long bookId) {
        List<Comment> comments = bookService.listComments(bookId);
        if (comments.isEmpty()) {
            return "No comments for this book.";
        }
        StringBuilder sb = new StringBuilder("Comments for book #" + bookId + ":\n");
        for (Comment c : comments) {
            sb.append(String.format(" - #%d: %s\n", c.getId(), c.getText()));
        }
        return sb.toString();
    }

    @ShellMethod(value = "Delete comment", key = {"delete-comment"})
    public String deleteComment(@ShellOption long commentId) {
        bookService.deleteComment(commentId);
        return "Comment deleted: #" + commentId;
    }
}