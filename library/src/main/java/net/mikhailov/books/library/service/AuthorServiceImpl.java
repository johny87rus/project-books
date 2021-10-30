package net.mikhailov.books.library.service;

import lombok.AllArgsConstructor;
import net.mikhailov.books.library.mapper.AuthorMapper;
import net.mikhailov.books.library.mapper.IdMapper;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.model.AuthorDTO;
import net.mikhailov.books.model.IdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mikhailov Evgenii
 */
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    IdMapper idMapper;


    @Override
    public IdDTO postAuthor(AuthorDTO authorDTO) {
        if (authorRepository.existsAuthorByFirstnameAndLastname(authorDTO.getName(), authorDTO.getSurname())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Author with same name found");
        }
        return idMapper.authorToIdDTO(authorRepository.save(authorMapper.authorDTOToAuthor(authorDTO)));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(item -> authorMapper.authorToAuthorDTO(item)).collect(Collectors.toList());
    }
}
