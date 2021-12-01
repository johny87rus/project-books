package net.mikhailov.books.library.service.providers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.repository.AuthorRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AbebooksProvider implements BookProvider{
    static final String URL = "https://www.abebooks.com/servlet/SearchResults?isbn=";
    static final String ROOT_URL = "https://www.abebooks.com/";

    private final AuthorRepository authorRepository;


    @Override
    public Optional<Book> getBook(Long isbn) {
        Document document = null;
        try {
            document = Jsoup
                    .connect(URL + isbn)
                    .method(Connection.Method.GET).get();
            String href = document.select("#book-1 > div.result-data.col-xs-9.cf > div.cf > div.result-detail.col-xs-8 > h2 > a").attr("href");
            document = Jsoup
                    .connect(ROOT_URL + href)
                    .method(Connection.Method.GET).get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }

        String title = document.select("#book-title > span").text();
        String desc = document.select("#rmjs-1 > p:nth-child(2)").text();
        if (desc.isBlank()) {
            desc = document.select("#product > div:nth-child(1) > div").text();
        }
        String authorFullName = document.select("#book-author > a > span").text();

        return Optional.empty();
    }
}
