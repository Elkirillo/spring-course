package com.library.repository;

import com.library.entities.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Загружаем книгу с автором и жанром сразу (чтобы не было N+1)
    @EntityGraph(attributePaths = {"author", "genre"})
    @Query("select b from Book b")
    List<Book> findAllWithAuthorAndGenre();

    // Получить книгу с комментариями
    @Query("select b from Book b left join fetch b.comments where b.id = :id")
    Optional<Book> findByIdWithComments(Long id);
}
