package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByFirstnameAndLastnameAllIgnoreCase(String firstName, String lastName);
    boolean existsAuthorByFirstnameAndLastname(String firstName, String lastName);
}
