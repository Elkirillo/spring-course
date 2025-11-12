package com.library.dao;

import com.library.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Book> rowMapper = (rs, rowNum) ->
            new Book(rs.getLong("id"), rs.getString("title"),
                    rs.getLong("author_id"), rs.getLong("genre_id"));

    public BookDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", rowMapper);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id = :id",
                        new MapSqlParameterSource("id", id), rowMapper)
                .stream().findFirst();
    }

    @Override
    public void insert(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books(title, author_id, genre_id) VALUES (:title, :authorId, :genreId)",
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("authorId", book.getAuthorId())
                        .addValue("genreId", book.getGenreId())
        );
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET title = :title, author_id = :authorId, genre_id = :genreId WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("authorId", book.getAuthorId())
                        .addValue("genreId", book.getGenreId())
                        .addValue("id", book.getId())
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = :id",
                new MapSqlParameterSource("id", id));
    }
}
