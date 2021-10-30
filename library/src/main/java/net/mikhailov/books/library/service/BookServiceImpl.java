package net.mikhailov.books.library.service;

import lombok.AllArgsConstructor;
import net.mikhailov.books.library.mapper.BookMapper;
import net.mikhailov.books.library.mapper.IdMapper;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.model.BookDTO;
import net.mikhailov.books.model.IdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;
    private final IdMapper idMapper;
    private final BookRepository bookRepository;



    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAllBy().stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    @Override
    public IdDTO postBook(BookDTO bookDTO) {
        if (bookRepository.existsBookByIsbn(bookDTO.getIsbn().longValue())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Book with same isbn found");
        }
        return idMapper.bookToIdDTO(bookRepository.save(bookMapper.bookDTOtoBook(bookDTO)));
    }
}
