package net.mikhailov.books.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @JsonProperty("isbn")
    @Min(1_000_000_000)
    @Max(999_99999_99999L)
    private Long isbn;

    @Valid
    @NotEmpty
    @JsonProperty("authorlist")
    private List<AuthorDTO> authorList;

    @Getter
    @Setter
    public static class AuthorDTO {
        @JsonProperty("id")
        private Integer id;

        @NotBlank
        @JsonProperty("name")
        private String authorName;
        @NotBlank
        @JsonProperty("lastname")
        private String authorLastName;
    }

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageurl")
    private String imageurl;

}
