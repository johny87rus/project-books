package net.mikhailov.books.library.mapper;

import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.model.BookDTOFull;
import net.mikhailov.books.model.BookDTOPost;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Mikhailov Evgenii
 */
@Mapper(componentModel = "spring", uses = { IdMapper.class, AuthorMapper.class}, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BookMapper {
    BookDTOFull bookToBookDTO(Book book);
    Book bookDTOtoBook(BookDTOPost bookDTO);

}
