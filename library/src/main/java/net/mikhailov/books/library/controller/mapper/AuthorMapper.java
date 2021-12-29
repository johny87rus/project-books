package net.mikhailov.books.library.controller.mapper;

import net.mikhailov.books.library.domain.author.Author;
import net.mikhailov.books.model.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Mikhailov Evgenii
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuthorMapper {
    @Mapping(source = "firstname", target = "name")
    @Mapping(source = "lastname", target = "surname")
    AuthorDTO authorToAuthorDTO(Author author);
}
