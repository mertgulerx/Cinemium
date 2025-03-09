package org.mertguler.cinemium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mertguler.cinemium.controller.CinemaController;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.core.City;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Cinema> jsonCinema;

    @Test
    void cinemaTest() throws Exception {
        // Prepare a valid UserDto request body
        String cinemaDtoJson = "{\"name\": \"Cevizlibag Cinemium\", " +
                "\"cinemaId\": \"cevizlibag-cinemium\"}";

        // Perform POST request to /user/new with valid UserDto
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cinemas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cinemaDtoJson))
                // Verify that the response status code is 201 create.
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cinemas/cevizlibag-cinemium"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
