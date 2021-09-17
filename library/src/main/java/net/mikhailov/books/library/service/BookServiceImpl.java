package net.mikhailov.books.library.service;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBN;
import net.mikhailov.books.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService{
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());

        List<Author> authorList = new ArrayList<>(bookDTO.getAuthorList().size());

        for (BookDTO.AuthorDTO authorDTO : bookDTO.getAuthorList()) {
            Author author = new Author();
            author.setFirstname(authorDTO.getAuthorName());
            author.setLastname(authorDTO.getAuthorLastName());
            authorList.add(author);
        }

        if (Objects.nonNull(bookDTO.getIsbnList())) {
            List<ISBN> isbnList = new ArrayList<>(bookDTO.getIsbnList().size());
            for (Long isbnNum : bookDTO.getIsbnList()) {
                ISBN isbn = new ISBN();
                isbn.setBook(book);
                isbn.setIsbnNumber(isbnNum);
                isbnList.add(isbn);
            }
            book.setIsbnList(isbnList);
        }
        book.setAuthors(authorList);

        return bookRepository.save(book);
    }
}
