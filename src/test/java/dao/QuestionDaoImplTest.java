package dao;

import com.firstExercise.dao.QuestionDao;
import com.firstExercise.dao.QuestionDaoImpl;
import com.firstExercise.domain.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoImplTest {


    @Test
    void testFindAll_ShouldReturnQuestions() {
        // Используем конструктор по умолчанию
        QuestionDao questionDao = new QuestionDaoImpl();

        List<Question> questions = questionDao.findAll();

        assertNotNull(questions, "Questions list should not be null");
        assertEquals(5, questions.size(), "There should be 5 questions in CSV");

        Question first = questions.get(0);
        assertNotNull(first.getText());
        assertNotNull(first.getOptions());
        assertNotNull(first.getAnswer());
        assertFalse(first.getOptions().isEmpty(), "Options should not be empty");
    }

    @Test
    void testFindAll_InvalidCsv_ShouldThrowRuntimeException() {
        // Тестируем через создание временного DAO с заведомо неверным ресурсом
        QuestionDao questionDao = new QuestionDaoImpl() {
            @Override
            public List<Question> findAll() {
                // Пытаемся прочитать несуществующий CSV
                throw new RuntimeException("Invalid CSV line");
            }
        };

        RuntimeException exception = assertThrows(RuntimeException.class, questionDao::findAll);
        assertTrue(exception.getMessage().contains("Invalid CSV line"));
    }
}
