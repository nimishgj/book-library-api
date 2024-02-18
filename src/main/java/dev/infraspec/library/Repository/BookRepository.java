package dev.infraspec.library.Repository;

import dev.infraspec.library.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM book where title like concat('%',:title,'%') limit 1")
    Book findByTitleContaining(@Param("title") String title);

    @Query(nativeQuery = true, value = "SELECT * FROM book")
    List<Book> getAllBooks();

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id = :id")
    Book getBookById(@Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO book (id, title, author, year_published) VALUES (:id, :title, :author, :year)")
    int add(@Param("id") int id, @Param("title") String title, @Param("author") String author, @Param("year") int year);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE book " +
            "SET title = :title, author = :author, year_published = :year " +
            "WHERE id = :id")
    int edit(@Param("id") int id, @Param("title") String title, @Param("author") String author, @Param("year") int year);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM book WHERE id = :id")
    void deleteBookById(@Param("id") Long id);

}
