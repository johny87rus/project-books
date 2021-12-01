package net.mikhailov.books.library.service;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.repository.AuthorRepository;
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


    @Override
    public Author postAuthor(Author author) {
        if (authorRepository.existsAuthorByFirstnameAndLastname(author.getFirstname(), author.getLastname())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Author with same name found");
        }
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author putAuthor(Integer authorId, Author author) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        author.setId(authorId);
        return authorRepository.save(author);

    }
}
