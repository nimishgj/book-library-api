package dev.infraspec.library.Repository;

import dev.infraspec.library.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM book where title like concat('%',:title,'%') limit 1")
    Book findByTitleContaining(@Param("title") String title);
}
