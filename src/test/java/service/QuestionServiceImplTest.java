package service;


import com.firstExercise.config.AppConfig;
import com.firstExercise.domain.Question;
import com.firstExercise.service.QuestionService;
import com.firstExercise.service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionServiceImplTest {

    @Test
    void testGetAllQuestions() {
        // Создаем Spring-контекст
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        QuestionService questionService = context.getBean(QuestionService.class);

        List<Question> questions = questionService.getAllQuestions();

        // Проверяем, что список не пустой
        assertNotNull(questions);
        assertEquals(5, questions.size(), "There should be exactly 5 questions");

        // Проверяем, что каждая запись имеет текст и варианты ответа
        for (Question q : questions) {
            assertNotNull(q.getText(), "Question text should not be null");
            assertNotNull(q.getOptions(), "Options list should not be null");
            assertTrue(q.getOptions().size() > 0, "Options list should have at least 1 option");
            assertNotNull(q.getAnswer(), "Answer should not be null");
        }

        context.close();
    }
}