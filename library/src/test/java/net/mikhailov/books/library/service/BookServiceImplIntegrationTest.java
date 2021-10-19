package net.mikhailov.books.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mikhailov.books.library.repository.ISBNQueueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookServiceImplIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ISBNQueueRepository isbnQueueRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenPostValidJSON() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"TestName\",\"lastname\":\"TestLastName\"}],\"bookname\":\"TestBook\",\"id\":null,\"isbn\":1234567890123, \"description\":\"TestDesc\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonTestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("\"id\":1")));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("\"id\":1")));
    }

    @Test
    void shouldWriteToDB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/addByISBN")
                        .content(objectMapper.writeValueAsString(List.of("123456", "123456", "1234567")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Assertions.assertEquals(2, isbnQueueRepository.count());
    }


}