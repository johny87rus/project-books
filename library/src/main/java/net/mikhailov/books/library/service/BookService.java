package net.mikhailov.books.library.service;

import net.mikhailov.books.library.dto.BookDTO;

import java.util.List;
import java.util.Set;

public interface BookService {
    BookDTO saveBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    void addByISBN(Set<String> isbnSet);
}
