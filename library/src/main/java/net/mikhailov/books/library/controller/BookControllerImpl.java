package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookControllerImpl implements BookController {
    BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO book = bookService.saveBook(bookDTO);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
