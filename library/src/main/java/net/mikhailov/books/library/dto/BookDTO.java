package net.mikhailov.books.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDTO {
    private String title;
    private List<Long> isbnList;
    private List<AuthorDTO> authorList;

    @Getter
    @Setter
    public static class AuthorDTO {
        private String authorName;
        private String authorLastName;
    }

}
