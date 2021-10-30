package net.mikhailov.books.library.service;

import net.mikhailov.books.model.AuthorDTO;
import net.mikhailov.books.model.IdDTO;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
public interface AuthorService {
    IdDTO postAuthor(AuthorDTO authorDTO);
    List<AuthorDTO> getAllAuthors();
}
