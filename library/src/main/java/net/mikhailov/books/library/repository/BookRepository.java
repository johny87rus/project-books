package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findBookByIsbn(String isbn);
}
