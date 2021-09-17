package net.mikhailov.books.library.service;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Book;

public interface BookService {
    Book saveBook(BookDTO bookDTO);
}
