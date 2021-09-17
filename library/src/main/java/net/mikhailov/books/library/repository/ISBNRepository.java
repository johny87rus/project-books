package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.ISBN;
import org.springframework.data.repository.CrudRepository;

public interface ISBNRepository extends CrudRepository<ISBN, Integer> {
}
