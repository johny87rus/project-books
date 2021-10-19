package net.mikhailov.books.library.config;

import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookMapper {

    public Book toEntity(BookDTO dto) {
        Book result = new Book();
        if (Objects.nonNull(dto.getId())) {
            result.setId(dto.getId());
        }
        result.setTitle(dto.getTitle());
        result.setIsbn(dto.getIsbn());
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

        return result;
    }

    public BookDTO toDto(Book entity) {
        BookDTO result = new BookDTO();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setIsbn(entity.getIsbn());
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

        return result;


    }
}
