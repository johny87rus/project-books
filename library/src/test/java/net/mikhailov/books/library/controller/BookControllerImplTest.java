package net.mikhailov.books.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mikhailov.books.library.dto.BookDTO;
import net.mikhailov.books.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mikhailov Evgenii
 */
@WebMvcTest
class BookControllerImplTest {
    @MockBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenPostEmptyJSON() throws Exception {
        String book = "{}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(book)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void whenPostWithoutTitle() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"TestName\",\"lastname\":\"TestLastName\"}],\"bookname\":\"\",\"id\":null,\"isbnlist\":[{\"isbn\":1234567890123}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"title\":\"must not be blank\"}"));
    }

    @Test
    void whenPostWithoutISBN() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"TestName\",\"lastname\":\"TestLastName\"}],\"bookname\":\"bookTestName\",\"id\":null,\"isbnlist\":[]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"isbnList\":\"must not be empty\"}"));
    }

    @Test
    void whenPostWithoutAuthors() throws Exception {
        String jsonTestString = "{\"authorlist\":[],\"bookname\":\"bookTestName\",\"id\":null,\"isbnlist\":[{\"isbn\":1234567890123}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"authorList\":\"must not be empty\"}"));
    }

    @Test
    void whenPostWithoutAuthorName() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"\",\"lastname\":\"TestLastName\"}],\"bookname\":\"bookTestName\",\"id\":null,\"isbnlist\":[{ \"isbn\":1234567890123 }]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"authorList[0].authorName\":\"must not be blank\"}"));
    }

    @Test
    void whenPostWithoutAuthorLastName() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"Name\",\"lastname\":\"\"}],\"bookname\":\"bookTestName\",\"id\":null,\"isbnlist\":[{\"isbn\":1234567890123}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"authorList[0].authorLastName\":\"must not be blank\"}"));
    }

    @Test
    void whenPostValidBookDTO() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestBook");
        List<BookDTO.ISBNDTO> isbnList = new ArrayList<>(1);
        BookDTO.ISBNDTO isbndto = new BookDTO.ISBNDTO();
        isbndto.setIsbnNumber(123_45678_90123L);
        isbnList.add(isbndto);
        bookDTO.setIsbnList(isbnList);

        List<BookDTO.AuthorDTO> authorDTOList = new ArrayList<>(1);
        BookDTO.AuthorDTO authorDTO = new BookDTO.AuthorDTO();
        authorDTO.setAuthorName("TestName");
        authorDTO.setAuthorLastName("TestLastName");
        authorDTOList.add(authorDTO);
        bookDTO.setAuthorList(authorDTOList);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(objectMapper.writeValueAsString(bookDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenPostValidJSON() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"TestName\",\"lastname\":\"TestLastName\"}],\"bookname\":\"TestBook\",\"id\":null,\"isbnlist\":[{\"isbn\":1234567890123}]}";

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1);
        Mockito.when(bookService.saveBook(Mockito.any(BookDTO.class))).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("\"id\":1")));
    }

}