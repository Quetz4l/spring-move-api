package com.quetz4l.getflix.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createGenre() throws Exception {

        String requestBody = "{\"name\": \"Action\"}";

        mockMvc.perform(post("/api/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result", is("Successful")));
    }

    @Test
    void findGenreById() throws Exception {
        mockMvc.perform(get("/api/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Action")));
    }

    @Test
    public void testUpdateRecord() throws Exception {
        String requestBody = "{\"name\":  \"Horror\"}";

        mockMvc.perform(patch("/api/genres/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Horror")));
    }

    @Test
    void findAllGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.[0].name", is("Horror")));
    }

    @Test
    void removeMovie() throws Exception {
        mockMvc.perform(delete("/api/genres/1"))
                .andExpect(status().isNoContent());
    }
}
