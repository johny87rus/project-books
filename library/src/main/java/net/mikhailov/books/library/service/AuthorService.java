package net.mikhailov.books.library.service;

import net.mikhailov.books.library.model.Author;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
public interface AuthorService {
    Author postAuthor(Author author);
    List<Author> getAllAuthors();
    Author putAuthor(Integer authorId, Author author);
}
