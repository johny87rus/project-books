package net.mikhailov.books.library.service;

import net.mikhailov.books.model.BookDTO;
import net.mikhailov.books.model.IdDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    IdDTO postBook(BookDTO bookDTO);
}
