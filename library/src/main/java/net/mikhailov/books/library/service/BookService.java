package net.mikhailov.books.library.service;

import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.model.ISBNResultList;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book postBook(Book book);
    ISBNResultList initBooks(List<String> isbnList);
}
