package fr.formation.training.forum;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TechnologyControllerIT extends IntegrationTest {

    @Test
    void shouldGetOneWithOkStatus() throws Exception {
        api.perform(get("/api/technologies/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetOneWithNotFoundStatus() throws Exception {
        api.perform(get("/api/technologies/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithOkStatus() throws Exception {
        api.perform(get("/api/technologies/"))
                .andExpect(status().isOk());
    }

}
