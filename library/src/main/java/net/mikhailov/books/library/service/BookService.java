package net.mikhailov.books.library.service;

import net.mikhailov.books.model.BookDTOFull;
import net.mikhailov.books.model.BookDTOPost;
import net.mikhailov.books.model.IdDTO;

import java.util.List;

public interface BookService {
    List<BookDTOFull> getAllBooks();
    IdDTO postBook(BookDTOPost bookDTO);
}
