package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsBookByIsbn(Long isbn);

    @EntityGraph(attributePaths = {"authors"})
    List<Book> findAllBy();
}
