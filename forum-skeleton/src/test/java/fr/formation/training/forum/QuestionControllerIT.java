package fr.formation.training.forum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

public class QuestionControllerIT extends IntegrationTest {

    @Value("${mockserver.paths.questions}")
    private String path; // /api/questions

    @ParameterizedTest // @Test
    @CsvFileSource(resources = "/questions-success.csv", numLinesToSkip = 1,
	    delimiterString = CSV_DELIMITER)
    void shouldAddQuestion(String json) throws Exception {
	api.perform(post(path).contentType(MediaType.APPLICATION_JSON)
		.content(json)).andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/questions-error.csv",
	    delimiterString = CSV_DELIMITER, numLinesToSkip = 1)
    void shouldNotAddQuestion(String json) throws Exception {
	api.perform(post(path).contentType(MediaType.APPLICATION_JSON)
		.content(json)).andExpect(status().isBadRequest());
    }
}
