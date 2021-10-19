package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    boolean existsBookByIsbn(Long isbn);
}
