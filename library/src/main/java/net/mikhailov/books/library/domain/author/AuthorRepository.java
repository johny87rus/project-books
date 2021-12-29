package net.mikhailov.books.library.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByFirstnameAndLastnameAllIgnoreCase(String firstName, String lastName);
    boolean existsAuthorByFirstnameAndLastname(String firstName, String lastName);
}
