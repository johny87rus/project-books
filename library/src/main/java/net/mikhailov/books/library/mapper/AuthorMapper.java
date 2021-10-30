package net.mikhailov.books.library.mapper;

import net.mikhailov.books.library.model.Author;
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

    @Mapping(source = "name", target = "firstname")
    @Mapping(source = "surname", target = "lastname")
    @Mapping(target = "books", ignore = true)
    Author authorDTOToAuthor(AuthorDTO author);
}
