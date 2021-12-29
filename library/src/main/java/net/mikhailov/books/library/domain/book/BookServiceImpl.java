package net.mikhailov.books.library.domain.book;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.author.Author;
import net.mikhailov.books.library.domain.author.AuthorService;
import net.mikhailov.books.library.domain.isbnqueue.providers.BookProvider;
import net.mikhailov.books.model.ISBNResultList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookProvider bookProvider;
    private final BookFactory bookFactory;

    private final AuthorService authorService;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Book postBook(BookInfo bookInfo) {
        if (bookRepository.existsBookByIsbn(bookInfo.getIsbn())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Book with same isbn found");
        }
        return bookRepository.save(bookFactory.build(bookInfo));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ISBNResultList initBooks(List<String> isbnList) {
        ISBNResultList isbnResultList = new ISBNResultList();
        for (String isbn : isbnList) {
            var isbnLong = Long.valueOf(isbn);
            if (bookRepository.existsBookByIsbn(isbnLong)) {
                isbnResultList.addSuccessItem(isbn);
                continue;
            }
            Optional<Book> book = bookProvider.getBook(isbnLong);
            book.ifPresentOrElse(it -> {
                var savedAuthors = new LinkedHashSet<Author>();
                for (Author author : it.getAuthors()) {
                    if (authorService.findAuthorByNameAndSurname(author.getFirstname(), author.getLastname()).isEmpty()) {
                        savedAuthors.add(authorService.postAuthor(author));
                    }
                }
                it.setAuthors(savedAuthors);
                bookRepository.save(it);
                isbnResultList.addSuccessItem(isbn);
            }, () -> isbnResultList.addFailedItem(isbn));
        }
        return isbnResultList;
    }
}
