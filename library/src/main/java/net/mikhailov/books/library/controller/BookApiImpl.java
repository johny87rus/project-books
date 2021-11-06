package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.service.BookService;
import net.mikhailov.books.model.BookDTOFull;
import net.mikhailov.books.model.BookDTOPost;
import net.mikhailov.books.model.ISBNResultList;
import net.mikhailov.books.model.IdDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@RestController
public class BookApiImpl implements BooksApi  {
    BookService bookService;

    public BookApiImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public List<BookDTOFull> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public ISBNResultList initBooks(List<String> isbnList) {
        return bookService.initBooks(isbnList);
    }

    @Override
    public IdDTO postBook(BookDTOPost bookDTOPost) {
        return bookService.postBook(bookDTOPost);
    }

}
