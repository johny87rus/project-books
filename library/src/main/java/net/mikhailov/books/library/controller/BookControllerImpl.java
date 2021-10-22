package net.mikhailov.books.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("books")
public class BookControllerImpl implements BookController {
    BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Value("classpath:isbndb.json")
    Resource resourceFile;

    @Override
    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO book = bookService.saveBook(bookDTO);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @Override
    @PostMapping("/addByISBN")
    public ResponseEntity<Void> addByISBN(@RequestBody Set<String> isbnSet) {
        bookService.addByISBN(isbnSet);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @PostMapping("/initialize")
    @Override
    public ResponseEntity<Void> initialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = objectMapper.readValue(resourceFile.getInputStream(), String[].class);
        addByISBN(new HashSet<>(Arrays.asList(strings)));
        return ResponseEntity.ok().build();
    }



}
