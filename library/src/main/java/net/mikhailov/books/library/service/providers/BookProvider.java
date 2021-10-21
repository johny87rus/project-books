package net.mikhailov.books.library.service.providers;

import net.mikhailov.books.library.model.Book;

import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
public interface BookProvider {
    Optional<Book> getBook(Long isbn);
}
