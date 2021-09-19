package net.mikhailov.books.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
public class BookDTO {
    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("bookname")
    private String title;

    @JsonProperty("isbnlist")
    @NotEmpty
    private List<Long> isbnList;

    @Valid
    @NotEmpty
    @JsonProperty("authorlist")
    private List<AuthorDTO> authorList;

    @Getter
    @Setter
    public static class AuthorDTO {
        @NotBlank
        @JsonProperty("name")
        private String authorName;
        @NotBlank
        @JsonProperty("lastname")
        private String authorLastName;
    }

}