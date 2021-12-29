package net.mikhailov.books.library.domain.isbnqueue.providers;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.domain.author.Author;
import net.mikhailov.books.library.domain.book.Book;
import net.mikhailov.books.library.domain.isbnqueue.providers.google.GoogleBookList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
@Slf4j
@Service
@Primary
public class GoogleApiProvider implements BookProvider {
    static final String URL = "https://www.googleapis.com/books/v1/volumes?q=";
    static final String KEY = "&key=";

    @Value( "${google.api.key:empty}" )
    private String googleApiKey;

    @Override
    public Optional<Book> getBook(Long isbn) {
        RestTemplate restTemplate = new RestTemplate();
        final String URI = URL + isbn + KEY + googleApiKey;
        GoogleBookList googleBookList = restTemplate.getForObject(URI, GoogleBookList.class);
        if (googleBookList == null || googleBookList.getItems() == null || googleBookList.getItems().isEmpty()) {
            return Optional.empty();
        }
        GoogleBookList.GoogleBook googleBook = googleBookList.getItems().get(0);
        Book book = new Book();
        book.setTitle(googleBook.getVolumeInfo().getTitle());
        book.setDescription(googleBook.getVolumeInfo().getDescription());
        book.setImageurl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
        Optional<String> isbn13 = googleBook.getVolumeInfo().getIndustryIdentifiers().stream().filter(it -> it.getType().equals("ISBN_13")).map(GoogleBookList.IndustryIdentifiers::getIdentifier).findFirst();
        if (isbn13.isEmpty()) {
            return Optional.empty();
        } else {
            book.setIsbn(Long.valueOf(isbn13.get()));
        }
        List<Author> authorList = new LinkedList<>();
        if (googleBook.getVolumeInfo().getAuthors().isEmpty()) {
            return Optional.empty();
        }
        googleBook.getVolumeInfo().getAuthors().forEach(it -> {
            Author author = new Author();
            author.setFirstname(it.split(" ")[0]);
            author.setLastname(it.split(" ")[1]);
            authorList.add(author);
        });
        book.setAuthors(authorList);
        if (book.getAuthors() == null) {
            return Optional.empty();
        }
        return Optional.of(book);
    }
}
