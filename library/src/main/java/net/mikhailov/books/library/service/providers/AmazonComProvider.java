package net.mikhailov.books.library.service.providers;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.repository.AuthorRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
@Service
@Slf4j
public class AmazonComProvider implements BookProvider {
    static final String URL = "https://www.amazon.com/s?i=stripbooks&rh=p_66%3A";
    static final String TITLESELECTOR = "#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div.s-result-item.s-asin.sg-col-0-of-12.sg-col-16-of-20.sg-col.s-widget-spacing-small.sg-col-12-of-16 > div > span > div > div > div:nth-child(2) > div.sg-col.sg-col-4-of-12.sg-col-8-of-16.sg-col-12-of-20 > div > div > div.a-section.a-spacing-none > h2 > a > span";
    static final String AUTHORSELECTOR = "#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div.s-result-item.s-asin.sg-col-0-of-12.sg-col-16-of-20.sg-col.s-widget-spacing-small.sg-col-12-of-16 > div > span > div > div > div:nth-child(2) > div.sg-col.sg-col-4-of-12.sg-col-8-of-16.sg-col-12-of-20 > div > div > div.a-section.a-spacing-none > div > div > span:nth-child(6)";
    static final String IMAGESELECTOR = "#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div.s-result-item.s-asin.sg-col-0-of-12.sg-col-16-of-20.sg-col.s-widget-spacing-small.sg-col-12-of-16 > div > span > div > div > div:nth-child(2) > div.sg-col.sg-col-4-of-12.sg-col-4-of-16.sg-col-4-of-20 > div > div > span > a > div > img";
    static final String LANGSELECTOR = "#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div.s-result-item.s-asin.sg-col-0-of-12.sg-col-16-of-20.sg-col.s-widget-spacing-small.sg-col-12-of-16 > div > span > div > div > div:nth-child(2) > div.sg-col.sg-col-4-of-12.sg-col-8-of-16.sg-col-12-of-20 > div > div > div.a-section.a-spacing-none > div > div > span:nth-child(1)";
    static final String ROBOTSELECTOR = "body > div > div.a-row.a-spacing-double-large > div.a-box.a-alert.a-alert-info.a-spacing-base > div > p";

    private final AuthorRepository authorRepository;

    public AmazonComProvider(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> getBook(Long isbn) {
        Document document = null;
        try {
            document = Jsoup
                    .connect(URL + isbn)
                    .method(Connection.Method.GET).get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
        String title = document.select(TITLESELECTOR).text();
        if (title.isBlank()) {
            return Optional.empty();
        }
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);

        String authorNames = document.select(AUTHORSELECTOR).text();
        String[] authorName = authorNames.split(" ");
        List<Author> authorList = new LinkedList<>();
        String firstName = authorName[1].toLowerCase().trim();
        String lastName = authorName[0].toLowerCase().trim();
        authorRepository.findAuthorByFirstnameAndLastnameAllIgnoreCase(firstName, lastName).ifPresentOrElse(authorList::add, () -> {
            Author author = new Author();
            author.setFirstname(firstName);
            author.setLastname(lastName);
            authorList.add(author);
        });
        book.setAuthors(authorList);
        return Optional.of(book);
    }
}
