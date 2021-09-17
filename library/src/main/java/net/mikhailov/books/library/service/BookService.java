package net.mikhailov.books.library.service;

import net.mikhailov.books.library.dto.BookDTO;

public interface BookService {
    BookDTO saveBook(BookDTO bookDTO);
}
