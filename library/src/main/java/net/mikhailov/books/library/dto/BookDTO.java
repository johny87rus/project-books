package net.mikhailov.books.library.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class BookDTO {
    private Integer id;

    @NotBlank
    private String title;

    private List<Long> isbnList;

    @Valid
    @NotEmpty
    private List<AuthorDTO> authorList;

    @Getter
    @Setter
    public static class AuthorDTO {
        @NotBlank
        private String authorName;
        @NotBlank
        private String authorLastName;
    }

}
