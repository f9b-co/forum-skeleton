package fr.formation.training.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TechnologyControllerIT extends IntegrationTest {

    @Value("${mockserver.paths.technologies}")
    private String path;

    @Test
    void shouldGetOneWithOkStatus() throws Exception {
        api.perform(get(path + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetOneWithNotFoundStatus() throws Exception {
        api.perform(get(path + "/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithOkStatus() throws Exception {
        api.perform(get(path))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllOrderedByRatingDesc() throws Exception {
        api.perform(get(path)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].rating", is(16.48)))
                .andExpect(jsonPath("$.[-1].rating", is(1.53)));
    }

}
