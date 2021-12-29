package net.mikhailov.books.library.domain.isbnqueue.providers;

import net.mikhailov.books.library.domain.book.Book;
import net.mikhailov.books.library.domain.isbnqueue.providers.itbook.ItBook;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * https://api.itbook.store/
 */
@Service
public class ItBookProvider implements BookProvider {
    static final String URL = "https://api.itbook.store/1.0/books/";


    @Override
    public Optional<Book> getBook(Long isbn) {
        var restTemplate = new RestTemplate();
        final var URI = URL + isbn;
        var response = restTemplate.getForObject(URI, ItBook.class);
        return Optional.empty();
    }
}
