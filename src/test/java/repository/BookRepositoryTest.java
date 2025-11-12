package repository;

import com.library.LibraryApplication;
import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Comment;
import com.library.entities.Genre;
import com.library.repository.BookRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = LibraryApplication.class)
@ActiveProfiles("test")
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void cleanDb() {
        bookRepository.deleteAll();
    }

    @Test
    void testSaveAndFindBookWithAuthorAndGenre() {
        Author author = new Author();
        author.setName("Author1");
        em.persist(author);

        Genre genre = new Genre();
        genre.setName("Genre1");
        em.persist(genre);

        Book book = new Book();
        book.setTitle("Book1");
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);
        em.flush();

        List<Book> books = bookRepository.findAllWithAuthorAndGenre();
        assertEquals(1, books.size());
        assertEquals("Author1", books.get(0).getAuthor().getName());
        assertEquals("Genre1", books.get(0).getGenre().getName());
    }

    @Test
    void testBookWithComments() {
        Author author = new Author();
        author.setName("Author2");
        em.persist(author);

        Genre genre = new Genre();
        genre.setName("Genre2");
        em.persist(genre);

        Book book = new Book();
        book.setTitle("Book2");
        book.setAuthor(author);
        book.setGenre(genre);

        Comment comment = new Comment();
        comment.setText("Great!");
        comment.setBook(book);
        book.getComments().add(comment);

        bookRepository.save(book);
        em.flush();

        Book found = bookRepository.findByIdWithComments(book.getId()).orElseThrow();
        assertEquals(1, found.getComments().size());
        assertEquals("Great!", found.getComments().get(0).getText());
    }
}