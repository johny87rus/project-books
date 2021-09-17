package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.dto.BookDTO;
import org.springframework.http.ResponseEntity;

public interface BookController {
    ResponseEntity<BookDTO> saveBook(BookDTO bookDTO);
}
