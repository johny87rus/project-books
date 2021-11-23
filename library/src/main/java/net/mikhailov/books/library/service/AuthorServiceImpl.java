package net.mikhailov.books.library.service;

import lombok.AllArgsConstructor;
import net.mikhailov.books.library.mapper.AuthorMapper;
import net.mikhailov.books.library.mapper.IdMapper;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.model.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public AuthorDTO postAuthor(AuthorDTO authorDTO) {
        if (authorRepository.existsAuthorByFirstnameAndLastname(authorDTO.getName(), authorDTO.getSurname())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Author with same name found");
        }
        return authorMapper.authorToAuthorDTO(authorRepository.save(authorMapper.authorDTOToAuthor(authorDTO)));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(item -> authorMapper.authorToAuthorDTO(item)).toList();
    }

    @Override
    public AuthorDTO putAuthor(Long id, AuthorDTO authorDTO) {
        if (!authorRepository.existsById(id.intValue())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        authorDTO.setId(id.intValue());
        return authorMapper.authorToAuthorDTO(authorRepository.save(authorMapper.authorDTOToAuthor(authorDTO)));

    }
}
