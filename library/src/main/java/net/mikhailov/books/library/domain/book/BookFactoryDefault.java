package net.mikhailov.books.library.domain.book;

import org.springframework.stereotype.Component;

/**
 * Дефолтная реализация фабрики книг
 */
@Component
public class BookFactoryDefault implements BookFactory{
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
    }
}
