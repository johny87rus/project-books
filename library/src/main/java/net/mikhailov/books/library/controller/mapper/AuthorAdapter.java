package net.mikhailov.books.library.controller.mapper;

import net.mikhailov.books.library.domain.author.AuthorInfo;
import net.mikhailov.books.model.AuthorDTO;


/**
 * Адаптер для Автора
 */
public class AuthorAdapter implements AuthorInfo {
    private final Long id;
    private final String firstname;
    private final String lastname;


    public AuthorAdapter(AuthorDTO authorDTO) {
        this.id = authorDTO.getId();
        this.firstname = authorDTO.getName();
        this.lastname = authorDTO.getSurname();
    }

    public AuthorAdapter(Long authorId, AuthorDTO authorDTO) {
        this.id = authorId;
        this.firstname = authorDTO.getName();
        this.lastname = authorDTO.getSurname();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }
}
