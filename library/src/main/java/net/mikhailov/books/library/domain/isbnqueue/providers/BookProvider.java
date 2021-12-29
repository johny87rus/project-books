package net.mikhailov.books.library.domain.isbnqueue.providers;

import net.mikhailov.books.library.domain.book.Book;

import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
public interface BookProvider {
    Optional<Book> getBook(Long isbn);
}
