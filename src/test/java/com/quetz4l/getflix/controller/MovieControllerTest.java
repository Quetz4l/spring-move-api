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
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static Long resourceId;

    @Test
    @Order(1)
    void createMovie() throws Exception {
        String requestBody = "{" +
                "    \"title\":  \"Riddick\"," +
                "    \"releaseYear\": \"2013\"," +
                "    \"duration\": 128," +
                "    \"genres\": []" +
                "}";

        MvcResult result = mockMvc.perform(post("/api/movies")
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
    void findMovieById() throws Exception {
        mockMvc.perform(get("/api/movies/" + resourceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.title", is("Riddick")));
    }

    @Test
    @Order(3)
    public void testUpdateRecord() throws Exception {
        String requestBody = "{" +
                "    \"title\":  \"Riddick 2\"," +
                "    \"releaseYear\": \"2020\"," +
                "    \"duration\": 68," +
                "    \"genres\": []" +
                "}";

        mockMvc.perform(patch("/api/movies/" + resourceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.title", is("Riddick 2")));
    }

    @Test
    @Order(4)
    void findAllMovies() throws Exception {
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.[0].title", is("Riddick 2")));
    }


    @Test
    @Order(5)
    void removeMovie() throws Exception {
        mockMvc.perform(delete("/api/movies/" + resourceId))
                .andExpect(status().isNoContent());
    }


}
