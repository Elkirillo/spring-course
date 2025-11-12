package library;

import com.library.LibraryApplication;
import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LibraryApplication.class)
class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
}