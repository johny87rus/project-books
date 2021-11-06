package net.mikhailov.books.library.service;

import lombok.AllArgsConstructor;
import net.mikhailov.books.library.mapper.BookMapper;
import net.mikhailov.books.library.mapper.IdMapper;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.library.service.providers.BookProvider;
import net.mikhailov.books.model.BookDTOFull;
import net.mikhailov.books.model.BookDTOPost;
import net.mikhailov.books.model.ISBNResultList;
import net.mikhailov.books.model.IdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;
    private final IdMapper idMapper;
    private final BookRepository bookRepository;

    private final BookProvider bookProvider;

    private final AuthorRepository authorRepository;



    @Override
    public List<BookDTOFull> getAllBooks() {
        return bookRepository.findAllBy().stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    @Override
    public IdDTO postBook(BookDTOPost bookDTO) {
        if (bookRepository.existsBookByIsbn(bookDTO.getIsbn().longValue())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Book with same isbn found");
        }
        return idMapper.bookToIdDTO(bookRepository.save(bookMapper.bookDTOtoBook(bookDTO)));
    }

    @Override
    @Transactional
    public ISBNResultList initBooks(List<String> isbnList) {
        ISBNResultList isbnResultList = new ISBNResultList();
        for (String isbn : isbnList) {
            Long isbnLong = Long.valueOf(isbn);
            if (bookRepository.existsBookByIsbn(isbnLong)) {
                isbnResultList.addSuccessItem(isbn);
            }
            Optional<Book> book = bookProvider.getBook(isbnLong);
            book.ifPresent(it -> {
                List<Author> savedAuthors  = new LinkedList<>();
                for (Author author : it.getAuthors()) {
                    savedAuthors.add(authorRepository.save(author));
                }
                it.setAuthors(savedAuthors);
                bookRepository.save(it);
                isbnResultList.addSuccessItem(isbn);
            });
        }
        return isbnResultList;
    }
}
