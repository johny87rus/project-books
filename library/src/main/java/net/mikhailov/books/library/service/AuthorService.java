package net.mikhailov.books.library.service;

import net.mikhailov.books.model.AuthorDTO;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
public interface AuthorService {
    AuthorDTO postAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO putAuthor(Long id, AuthorDTO authorDTO);
}
