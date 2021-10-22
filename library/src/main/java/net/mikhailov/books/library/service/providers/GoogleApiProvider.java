package net.mikhailov.books.library.service.providers;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
@Slf4j
@Service
@Primary
public class GoogleApiProvider implements BookProvider {
//    static final String URL = "https://www.googleapis.com/books/v1/volumes?q=+isbn:";
    static final String URL = "https://www.googleapis.com/books/v1/volumes?q=";
    static final String KEY = "&key=";

    @Value( "${google.api.key}" )
    private String googleApiKey;

    @Override
    public Optional<Book> getBook(Long isbn) {
        RestTemplate restTemplate = new RestTemplate();
        final String URI = new StringBuilder(URL).append(isbn).append(KEY).append(googleApiKey).toString();
        String result = restTemplate.getForObject(URI, String.class);
        System.out.println(result);
        return Optional.empty();
    }
}
