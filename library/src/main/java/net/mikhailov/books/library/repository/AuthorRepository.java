package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
