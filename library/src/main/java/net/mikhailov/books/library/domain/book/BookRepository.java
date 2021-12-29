package net.mikhailov.books.library.domain.book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByIsbn(Long isbn);

    @EntityGraph(attributePaths = {"authors"})
    List<Book> findAllBy();
}
