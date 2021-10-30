package net.mikhailov.books.library.mapper;

import net.mikhailov.books.library.model.Author;
import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.model.IdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Mikhailov Evgenii
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdMapper {
    IdDTO bookToIdDTO(Book book);

    IdDTO authorToIdDTO(Author book);
}
