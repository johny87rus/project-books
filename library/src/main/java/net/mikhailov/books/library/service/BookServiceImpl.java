package net.mikhailov.books.library.service;

import net.mikhailov.books.library.config.BookMapper;
import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    BookRepository bookRepository;

    BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        bookDTO.setId(bookRepository.save(bookMapper.toEntity(bookDTO)).getId());
        return bookDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        Iterable<Book> allBooks = bookRepository.findAll();
        List<BookDTO> result = new LinkedList<>();
        allBooks.forEach(book -> result.add(bookMapper.toDto(book)));
        return result;
    }
}
