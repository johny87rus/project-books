package net.mikhailov.books.library.domain.book;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.author.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Дефолтная реализация фабрики книг
 */
@Component
@RequiredArgsConstructor
public class BookFactoryDefault implements BookFactory{
    private final AuthorService authorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Book build(BookInfo bookInfo) {
        var book = new Book();
        setValues(book, bookInfo);
        return book;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setValues(Book book, BookInfo bookInfo) {
        book.setId(bookInfo.getId());
        book.setIsbn(bookInfo.getIsbn());
        book.setImageurl(bookInfo.getImageurl());
        book.setTitle(bookInfo.getTitle());
        book.setDescription(bookInfo.getDescription());
        var authors = bookInfo.getAuthors();
        if (Objects.nonNull(authors)) {
            authors.forEach(it -> authorService.getByID(it.getId()));
        }
        book.setAuthors(authors);
    }
}
