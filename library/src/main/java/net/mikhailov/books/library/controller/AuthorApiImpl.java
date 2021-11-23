package net.mikhailov.books.library.controller;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.mapper.AuthorMapper;
import net.mikhailov.books.library.service.AuthorService;
import net.mikhailov.books.model.AuthorDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@RestController
@RequiredArgsConstructor
public class AuthorApiImpl implements AuthorsApi {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(authorMapper::authorToAuthorDTO).toList();
    }

    @Override
    public AuthorDTO postAuthor(AuthorDTO authorDTO) {
        return authorMapper.authorToAuthorDTO(authorService.postAuthor(authorMapper.authorDTOToAuthor(authorDTO)));
    }

    @Override
    public AuthorDTO putAuthor(Long id, AuthorDTO authorDTO) {
        return authorMapper.authorToAuthorDTO(authorService.putAuthor(id.intValue(), authorMapper.authorDTOToAuthor(authorDTO)));
    }

}
