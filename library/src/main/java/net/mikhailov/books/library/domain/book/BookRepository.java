package net.mikhailov.books.library.domain.book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByIsbn(Long isbn);

    @EntityGraph(attributePaths = {"authors"})
    List<Book> findAllBy();

    @Query("delete from Book b where b.id = ?1 or b.isbn = ?1")
    @Modifying
    void deleteByIdOrIsbn(Long value);
}
