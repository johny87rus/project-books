package net.mikhailov.books.library.domain.isbnqueue.providers;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.domain.author.Author;
import net.mikhailov.books.library.domain.book.Book;
import net.mikhailov.books.library.domain.isbnqueue.providers.google.GoogleBookList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashSet;
import java.util.Objects;
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
        var restTemplate = new RestTemplate();
        final var URI = URL + isbn + KEY + googleApiKey;
        var googleBookList = restTemplate.getForObject(URI, GoogleBookList.class);
        if (googleBookList == null || googleBookList.getItems() == null || googleBookList.getItems().isEmpty()) {
            return Optional.empty();
        }
        var googleBookOptional = findBook(googleBookList, isbn);
        if (googleBookOptional.isEmpty()) {
            return Optional.empty();
        }
        var googleBook = googleBookOptional.get();
        var book = new Book();
        book.setTitle(googleBook.getVolumeInfo().getTitle());
        book.setDescription(googleBook.getVolumeInfo().getDescription());
        book.setImageurl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
        book.setIsbn(isbn);
        var authorSet = new LinkedHashSet<Author>();
        if (googleBook.getVolumeInfo().getAuthors().isEmpty()) {
            return Optional.empty();
        }
        googleBook.getVolumeInfo().getAuthors().forEach(it -> {
            var author = new Author();
            author.setFirstname(it.split(" ")[0]);
            author.setLastname(it.split(" ")[1]);
            authorSet.add(author);
        });
        book.setAuthors(authorSet);
        if (book.getAuthors() == null) {
            return Optional.empty();
        }
        return Optional.of(book);
    }

    private Optional<GoogleBookList.GoogleBook> findBook(GoogleBookList googleBookList, Long isbn) {
        Optional<GoogleBookList.GoogleBook> byIsbn = googleBookList.getItems().stream()
                .filter(it -> isbn.equals(Long.valueOf(it.getVolumeInfo().getIndustryIdentifiers().stream().filter(isbnCode -> isbnCode.getType().equals("ISBN_13")).map(GoogleBookList.IndustryIdentifiers::getIdentifier).findFirst().orElse("0"))))
                .findFirst();
        if (byIsbn.isEmpty()) {
            return googleBookList.getItems().stream()
                    .filter(it -> Objects.nonNull(it.getSearchInfo()))
                    .filter(it -> it.getSearchInfo().getTextSnippet().contains(isbn.toString())).findFirst();
        }
        return byIsbn;
    }
}
