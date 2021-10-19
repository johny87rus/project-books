package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findAuthorByFirstnameAndLastnameAllIgnoreCase(String firstName, String lastName);
}
