package net.mikhailov.books.library.service.providers;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.repository.AuthorRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Evgenii Mikhailov
 */
@Slf4j
@Service
@Primary
public class BookFinderCom implements BookProvider{
    static final String URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&new_used=*&destination=ru&currency=USD&mode=basic&st=sr&ac=qr&isbn=";

    private final AuthorRepository authorRepository;

    public BookFinderCom(AuthorRepository authorRepository) {
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
        String title = document.select("#describe-isbn-title").text();
        if (title.isBlank()) {
            return Optional.empty();
        }
        String desc = document.select("#bookSummary").text();
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(desc);
        book.setIsbn(isbn);

        String authorNames = document.select("span[itemprop=\"author\"]").text();
        String[] authors = authorNames.split(";");
        List<Author> authorList = new LinkedList<>();
        for (String authorFullName : authors) {
            String[] split = authorFullName.split(",");
            if (split.length < 2) {
                split = authorFullName.split(" ");
            }
            String firstName = split[1].toLowerCase().trim();
            String lastName = split[0].toLowerCase().trim();
            authorRepository.findAuthorByFirstnameAndLastnameAllIgnoreCase(firstName, lastName).ifPresentOrElse(authorList::add, () -> {
                Author author = new Author();
                author.setFirstname(firstName);
                author.setLastname(lastName);
                authorList.add(author);
            });

        }
        book.setAuthors(authorList);
        return Optional.of(book);
    }
}
