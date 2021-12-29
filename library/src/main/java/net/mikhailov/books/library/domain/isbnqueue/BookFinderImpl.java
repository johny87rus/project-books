package net.mikhailov.books.library.domain.isbnqueue;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.author.AuthorRepository;
import net.mikhailov.books.library.domain.book.Book;
import net.mikhailov.books.library.domain.book.BookRepository;
import net.mikhailov.books.library.domain.isbnqueue.providers.BookProvider;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mikhailov Evgenii
 */
@Service
@RequiredArgsConstructor
public class BookFinderImpl implements BookFinder {

    private final BookRepository bookRepository;
    private final ISBNQueueRepository isbnQueueRepository;
    private final AuthorRepository authorRepository;

    private final BookProvider bookProvider;

    private final ReentrantLock reentrantLock = new ReentrantLock();

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
        Long isbn = IsbnConverter.convertFromString(isbnQueue.getIsbn());
        if (Boolean.TRUE.equals(Objects.isNull(isbn) || bookRepository.existsBookByIsbn(isbn)) || bookList.stream().anyMatch(it -> it.getIsbn().equals(isbn))) {
            return;
        }
        bookProvider.getBook(isbn).ifPresentOrElse(book -> {
            bookList.add(book);
            isbnQueueRepository.delete(isbnQueue);
        }, () -> isbnQueueRepository.delete(isbnQueue));
    }
}
