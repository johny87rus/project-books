package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.service.BookService;
import net.mikhailov.books.model.BookDTO;
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
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public IdDTO postBook(BookDTO bookDTO) {
        return bookService.postBook(bookDTO);
    }


}
