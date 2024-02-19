package dev.infraspec.library.repositories;

import dev.infraspec.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM library.books")
    List<Book> getAllBooks();
}