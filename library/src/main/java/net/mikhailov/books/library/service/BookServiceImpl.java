package net.mikhailov.books.library.service;

import net.mikhailov.books.library.config.BookMapper;
import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBNQueue;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.library.repository.ISBNQueueRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{
    BookRepository bookRepository;
    ISBNQueueRepository isbnQueueRepository;
    BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, ISBNQueueRepository isbnQueueRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.isbnQueueRepository = isbnQueueRepository;
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

    @Override
    public void addByISBN(Set<String> isbnSet) {
        Set<ISBNQueue> isbnQueues = new HashSet<>(isbnSet.size());
        for (String isbn : isbnSet) {
            ISBNQueue isbnQueue = new ISBNQueue();
            isbnQueue.setIsbn(isbn);
            isbnQueues.add(isbnQueue);
        }
        isbnQueueRepository.saveAll(isbnQueues);
    }

}
