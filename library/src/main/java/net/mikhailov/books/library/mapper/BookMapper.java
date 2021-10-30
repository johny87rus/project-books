package net.mikhailov.books.library.mapper;

import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.model.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Mikhailov Evgenii
 */
@Mapper(componentModel = "spring", uses = AuthorMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper {
    BookDTO bookToBookDTO(Book book);

    Book bookDTOtoBook(BookDTO bookDTO);
}
