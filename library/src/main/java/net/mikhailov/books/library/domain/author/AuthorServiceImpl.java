package net.mikhailov.books.library.domain.author;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.exceptions.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Mikhailov Evgenii
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository repository;
    private final AuthorFactory factory;


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Author postAuthor(AuthorInfo authorInfo) {
        if (repository.existsAuthorByFirstnameAndLastname(authorInfo.getFirstname(), authorInfo.getLastname())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Author with same name found");
        }
        return repository.save(factory.build(authorInfo));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Author putAuthor(AuthorInfo authorInfo) {
        if (!repository.existsById(authorInfo.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        return repository.save(factory.build(authorInfo));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getByID(Long id) {
        return repository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public Optional<Author> findAuthorByNameAndSurname(String name, String surname) {
        return repository.findAuthorByFirstnameAndLastnameAllIgnoreCase(name, surname);
    }
}
