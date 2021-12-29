package net.mikhailov.books.library.controller;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.controller.mapper.BookInfoAdapter;
import net.mikhailov.books.library.controller.mapper.BookMapper;
import net.mikhailov.books.library.controller.mapper.IdMapper;
import net.mikhailov.books.library.domain.book.BookService;
import net.mikhailov.books.model.BookDTOFull;
import net.mikhailov.books.model.BookDTOPost;
import net.mikhailov.books.model.ISBNResultList;
import net.mikhailov.books.model.IdDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@RestController
@RequiredArgsConstructor
public class BookApiImpl implements BooksApi  {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final IdMapper idMapper;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookDTOFull> getAllBooks() {
        return bookService.getAllBooks().stream().map(bookMapper::bookToBookDTO).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISBNResultList initBooks(List<String> isbnList) {
        return bookService.initBooks(isbnList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdDTO postBook(BookDTOPost bookDTOPost) {
        return idMapper.bookToIdDTO(bookService.postBook(new BookInfoAdapter(bookDTOPost)));
    }

}
