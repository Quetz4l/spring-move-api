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
class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createActor() throws Exception {

        String requestBody = "{    " +
                "\"name\": \"Jason Statham\"," +
                "\"birthDate\": \"1967-07-26\"}";

        mockMvc.perform(post("/api/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result", is("Successful")));
    }

    @Test
    void findActorById() throws Exception {
        mockMvc.perform(get("/api/actors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Jason Statham")));
    }

    @Test
    public void testUpdateRecord() throws Exception {
        String requestBody = "{" +
                "    \"name\":  \"Mega Jason Statham\"," +
                "    \"birthDate\": \"1967-07-26\"" +
                "}";

        mockMvc.perform(patch("/api/actors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.name", is("Mega Jason Statham")));
    }

    @Test
    void findAllActors() throws Exception {
        mockMvc.perform(get("/api/actors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("Successful")))
                .andExpect(jsonPath("$.data.[0].name", is("Mega Jason Statham")));
    }

    @Test
    void removeMovie() throws Exception {
        mockMvc.perform(delete("/api/actors/1"))
                .andExpect(status().isNoContent());
    }

}