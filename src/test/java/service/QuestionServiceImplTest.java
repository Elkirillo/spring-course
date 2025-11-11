package service;


import com.firstExercise.service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QuestionServiceImplTest {

    @Test
    void shouldLoadQuestionsWithoutExceptions() {
        QuestionServiceImpl service = new QuestionServiceImpl();
        service.setResourcePath("questions.csv");
        assertDoesNotThrow(service::printAllQuestions);
    }
}