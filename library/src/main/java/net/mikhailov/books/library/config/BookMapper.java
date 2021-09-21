package net.mikhailov.books.library.config;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBN;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookMapper {
    ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Book toEntity(BookDTO dto) {
        Book result = new Book();
        if (Objects.nonNull(dto.getId())) {
            result.setId(dto.getId());
        }
        result.setTitle(dto.getTitle());
        if (Objects.nonNull(dto.getAuthorList())) {
            List<Author> authorList = new ArrayList<>(dto.getAuthorList().size());
            dto.getAuthorList().forEach(authorDTO -> {
                Author author = new Author();
                author.setId(authorDTO.getId());
                author.setFirstname(authorDTO.getAuthorName());
                author.setLastname(authorDTO.getAuthorLastName());
                authorList.add(author);
            });
            result.setAuthors(authorList);
        }
        if (Objects.nonNull(dto.getIsbnList())) {
            List<ISBN> isbnList = new ArrayList<>(dto.getIsbnList().size());
            dto.getIsbnList().forEach(isbnDTO -> {
                ISBN isbn = new ISBN();
                if (Objects.nonNull(isbnDTO.getId())) {
                    isbn.setId(isbnDTO.getId());
                }
                isbn.setIsbnNumber(isbnDTO.getIsbnNumber());
                isbn.setBook(result);
                isbnList.add(isbn);
            });
            result.setIsbnList(isbnList);
        }

        return result;
    }

    public BookDTO toDto(Book entity) {
        BookDTO result = new BookDTO();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        if (Objects.nonNull(entity.getAuthors())) {
            List<BookDTO.AuthorDTO> authorList = new ArrayList<>(entity.getAuthors().size());
            entity.getAuthors().forEach(author -> {
                BookDTO.AuthorDTO authorDTO = new BookDTO.AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setAuthorName(author.getFirstname());
                authorDTO.setAuthorLastName(author.getLastname());
                authorList.add(authorDTO);
            });
            result.setAuthorList(authorList);
        }
        if (Objects.nonNull(entity.getIsbnList())) {
            List<BookDTO.ISBNDTO> isbnList = new ArrayList<>(entity.getIsbnList().size());
            entity.getIsbnList().forEach(isbn -> {
                BookDTO.ISBNDTO isbnDTO = new BookDTO.ISBNDTO();
                isbnDTO.setId(isbn.getId());
                isbnDTO.setIsbnNumber(isbn.getIsbnNumber());
                isbnList.add(isbnDTO);
            });
            result.setIsbnList(isbnList);
        }

        return result;


    }
}