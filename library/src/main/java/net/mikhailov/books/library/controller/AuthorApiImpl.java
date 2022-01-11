package net.mikhailov.books.library.controller;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.controller.mapper.AuthorAdapter;
import net.mikhailov.books.library.controller.mapper.AuthorMapper;
import net.mikhailov.books.library.domain.author.AuthorService;
import net.mikhailov.books.model.AuthorDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorApiImpl implements AuthorsApi {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Void deleteAuthor(Long authorId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(authorMapper::authorToAuthorDTO).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorDTO postAuthor(AuthorDTO authorDTO) {
        return authorMapper.authorToAuthorDTO(authorService.postAuthor(new AuthorAdapter(authorDTO)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorDTO putAuthor(Long authorId, AuthorDTO authorDTO) {
        return authorMapper.authorToAuthorDTO(authorService.putAuthor(new AuthorAdapter(authorId, authorDTO)));
    }

}
