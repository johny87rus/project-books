package net.mikhailov.books.library.service;

import lombok.extern.slf4j.Slf4j;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBNQueue;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.library.repository.ISBNQueueRepository;
import net.mikhailov.books.library.util.IsbnConverter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mikhailov Evgenii
 */
@Service
@Slf4j
public class BookFinderImpl implements BookFinder {
    static final String URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&new_used=*&destination=ru&currency=USD&mode=basic&st=sr&ac=qr&isbn=";

    BookRepository bookRepository;
    ISBNQueueRepository isbnQueueRepository;
    AuthorRepository authorRepository;
    IsbnConverter isbnConverter;

    private final ReentrantLock reentrantLock = new ReentrantLock();


    public BookFinderImpl(BookRepository bookRepository, ISBNQueueRepository isbnQueueRepository, AuthorRepository authorRepository, IsbnConverter isbnConverter) {
        this.bookRepository = bookRepository;
        this.isbnQueueRepository = isbnQueueRepository;
        this.authorRepository = authorRepository;
        this.isbnConverter = isbnConverter;
    }

    @Override
    @Async
    public void enrichBooks() {
        try {
            reentrantLock.lock();
            Iterator<ISBNQueue> iterator = isbnQueueRepository.findAll().iterator();
            List<Book> bookList = new LinkedList<>();
            while (iterator.hasNext()) {
                ISBNQueue isbnQueue = iterator.next();
                processIsbn(bookList, isbnQueue);
            }
            bookRepository.saveAll(bookList);
        } finally {
            reentrantLock.unlock();
        }
    }

    private void processIsbn(List<Book> bookList, ISBNQueue isbnQueue) {
        Long isbn = isbnConverter.convertFromString(isbnQueue.getIsbn());
        if (Boolean.TRUE.equals(Objects.isNull(isbn) || bookRepository.existsBookByIsbn(isbn)) || bookList.stream().anyMatch(it -> it.getIsbn().equals(isbn))) {
            return;
        }
        Document document = null;
        try {
            document = Jsoup
                    .connect(URL + isbn)
                    .method(Connection.Method.GET).get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        String title = document.select("#describe-isbn-title").text();
        if (title.isBlank()) {
            isbnQueueRepository.delete(isbnQueue);
            return;
        }
        String desc = document.select("#bookSummary").text();
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(desc);
        book.setIsbn(isbn);
        bookList.add(book);

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
        //TODO добавить язык
        //String language = document.select("span[itemprop=\"inLanguage\"]").text();
    }
}
