package net.mikhailov.books.library.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    AuthorRepository authorRepository;


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
    public Author putAuthor(Integer id, Author author) {
        if (!authorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        author.setId(id);
        return authorRepository.save(author);

    }
}
