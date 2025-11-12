package repository;

import com.library.LibraryApplication;
import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Comment;
import com.library.entities.Genre;
import com.library.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = LibraryApplication.class)
@ActiveProfiles("test")
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private EntityManager em;

    @Test
    void testSaveBookWithAuthorAndGenre() {
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

        bookRepo.save(book);
        em.flush();

        List<Book> books = bookRepo.findAllWithAuthorAndGenre();
        assertEquals(1, books.size());
        assertEquals("Author1", books.get(0).getAuthor().getName());
        assertEquals("Genre1", books.get(0).getGenre().getName());
    }

    @Test
    void testSaveBookWithComments() {
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

        Comment comment1 = new Comment();
        comment1.setText("Great book!");
        comment1.setBook(book);

        Comment comment2 = new Comment();
        comment2.setText("Loved it!");
        comment2.setBook(book);

        book.getComments().add(comment1);
        book.getComments().add(comment2);

        bookRepo.save(book);
        em.flush();

        Book found = bookRepo.findByIdWithComments(book.getId()).orElseThrow();
        assertEquals(2, found.getComments().size());
        assertTrue(found.getComments().stream().anyMatch(c -> c.getText().equals("Great book!")));
        assertTrue(found.getComments().stream().anyMatch(c -> c.getText().equals("Loved it!")));
    }

    @Test
    void testCascadeDeleteBookWithComments() {
        Author author = new Author();
        author.setName("Author3");
        em.persist(author);

        Genre genre = new Genre();
        genre.setName("Genre3");
        em.persist(genre);

        Book book = new Book();
        book.setTitle("Book3");
        book.setAuthor(author);
        book.setGenre(genre);

        Comment comment = new Comment();
        comment.setText("Comment");
        comment.setBook(book);

        book.getComments().add(comment);

        bookRepo.save(book);
        em.flush();

        // Удаляем книгу — комментарий тоже должен удалиться
        bookRepo.delete(book);
        em.flush();

        assertTrue(bookRepo.findById(book.getId()).isEmpty());
        assertTrue(em.createQuery("select c from Comment c", Comment.class).getResultList().isEmpty());
    }
}
