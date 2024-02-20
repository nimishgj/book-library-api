package dev.infraspec.library.repositories;

import dev.infraspec.library.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM library.books")
    List<Book> getAllBooks();

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO library.books (id, title, author, year_published, is_checked_out) VALUES (:id, :title, :author, :year, false)")
    int add(@Param("id") int id, @Param("title") String title, @Param("author") String author, @Param("year") int year);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE library.books SET title = :title, author = :author, year_published = :year WHERE id = :id")
    int update(@Param("id") int id, @Param("title") String title, @Param("author") String author, @Param("year") int year);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM library.books WHERE id = :id")
    int deleteBookById(@Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE library.books SET is_checked_out = true WHERE id = :id AND is_checked_out=false")
    int checkoutBookById(@Param("id") int id);
}
