package net.mikhailov.books.library.service;

import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBNQueue;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.library.repository.ISBNQueueRepository;
import net.mikhailov.books.library.service.providers.BookProvider;
import net.mikhailov.books.library.util.IsbnConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mikhailov Evgenii
 */
@Service
public class BookFinderImpl implements BookFinder {

    BookRepository bookRepository;
    ISBNQueueRepository isbnQueueRepository;
    AuthorRepository authorRepository;
    IsbnConverter isbnConverter;

    List<BookProvider> bookProviders;

    private final ReentrantLock reentrantLock = new ReentrantLock();


    public BookFinderImpl(BookRepository bookRepository, ISBNQueueRepository isbnQueueRepository, AuthorRepository authorRepository, IsbnConverter isbnConverter, List<BookProvider> bookProviders) {
        this.bookRepository = bookRepository;
        this.isbnQueueRepository = isbnQueueRepository;
        this.authorRepository = authorRepository;
        this.isbnConverter = isbnConverter;
        this.bookProviders = bookProviders;
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
        for (BookProvider bookProvider : bookProviders) {
            Optional<Book> book = bookProvider.getBook(isbn);
            if (book.isPresent()) {
                bookList.add(book.get());
                isbnQueueRepository.delete(isbnQueue);
                return;
            }
        }
        isbnQueueRepository.delete(isbnQueue);
    }
}
