package com.quetz4l.getflix.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static Long resourceId;

    @Test
    @Order(1)
    void createGenre() throws Exception {
        String requestBody = "{\"name\": \"Action\"}";

        MvcResult result = mockMvc.perform(post("/api/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        resourceId = jsonNode.get("data").get("id").asLong();
    }

    @Test
    @Order(2)
    void findGenreById() throws Exception {
        mockMvc.perform(get("/api/genres/" + resourceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Action")));
    }

    @Test
    @Order(3)
    public void testUpdateRecord() throws Exception {
        String requestBody = "{\"name\":  \"Horror\"}";

        mockMvc.perform(patch("/api/genres/" + resourceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Horror")));
    }

    @Test
    @Order(4)
    void findAllGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.[0].name", is("Horror")));
    }

    @Test
    @Order(5)
    void removeMovie() throws Exception {
        mockMvc.perform(delete("/api/genres/" + resourceId))
                .andExpect(status().isNoContent());
    }
}
