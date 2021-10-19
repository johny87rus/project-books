package net.mikhailov.books.library.controller;

import net.mikhailov.books.library.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface BookController {
    ResponseEntity<BookDTO> saveBook(BookDTO bookDTO);
    ResponseEntity<List<BookDTO>> getAllBook();
    ResponseEntity<Void> addByISBN(Set<String> isbnSet);
}
