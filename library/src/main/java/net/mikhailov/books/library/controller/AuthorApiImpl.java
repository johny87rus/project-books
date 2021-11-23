package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.service.AuthorService;
import net.mikhailov.books.model.AuthorDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@RestController
public class AuthorApiImpl implements AuthorsApi {
    AuthorService authorService;

    public AuthorApiImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }


    @Override
    public AuthorDTO postAuthor(AuthorDTO authorDTO) {
        return authorService.postAuthor(authorDTO);
    }

    @Override
    public AuthorDTO putAuthor(Long id, AuthorDTO authorDTO) {
        return authorService.putAuthor(id, authorDTO);
    }

}
