package net.mikhailov.books.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookServiceImplIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostValidJSON() throws Exception {
        String jsonTestString = "{\"authorlist\":[{\"name\":\"TestName\",\"lastname\":\"TestLastName\"}],\"bookname\":\"TestBook\",\"id\":null,\"isbnlist\":[{\"isbn\":1234567890123}]}";


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

}