package net.mikhailov.books.library.domain.author;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.exceptions.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;
    private final AuthorFactory authorFactory;


    /**
     * {@inheritDoc}
     */
    @Override
    public Author postAuthor(AuthorInfo authorInfo) {
        if (authorRepository.existsAuthorByFirstnameAndLastname(authorInfo.getFirstname(), authorInfo.getLastname())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Author with same name found");
        }
        return authorRepository.save(authorFactory.build(authorInfo));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author putAuthor(AuthorInfo authorInfo) {
        if (!authorRepository.existsById(authorInfo.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        return authorRepository.save(authorFactory.build(authorInfo));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getByID(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }
}
