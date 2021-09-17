package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookController {
    ResponseEntity<Book> saveBook(BookDTO bookDTO);
}
