package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.service.AuthorService;
import net.mikhailov.books.model.AuthorDTO;
import net.mikhailov.books.model.IdDTO;
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
    public IdDTO postAuthor(AuthorDTO authorDTO) {
        return authorService.postAuthor(authorDTO);
    }
}
