package service;


import com.firstExercise.config.AppConfig;
import com.firstExercise.dao.QuestionDao;
import com.firstExercise.domain.Question;
import com.firstExercise.service.QuestionService;
import com.firstExercise.service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {

    @Test
    void testGetAllQuestions_ShouldReturnQuestions() {
        // Мокаем QuestionDao
        QuestionDao mockDao = mock(QuestionDao.class);
        Question q1 = new Question("Q1?", List.of("A","B"), "A");
        Question q2 = new Question("Q2?", List.of("X","Y"), "Y");
        when(mockDao.findAll()).thenReturn(List.of(q1, q2));

        QuestionService service = new QuestionServiceImpl(mockDao);

        List<Question> result = service.getAllQuestions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Q1?", result.get(0).getText());
        assertEquals("Q2?", result.get(1).getText());

        verify(mockDao, times(1)).findAll();
    }
}
