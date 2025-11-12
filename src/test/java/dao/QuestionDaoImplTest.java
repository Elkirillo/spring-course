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
        QuestionDao questionDao = new QuestionDaoImpl("questions.csv");

        List<Question> questions = questionDao.findAll();

        assertNotNull(questions, "Questions list should not be null");
        assertEquals(5, questions.size(), "There should be 5 questions in CSV");

        Question first = questions.get(0);
        assertNotNull(first.getText());
        assertNotNull(first.getOptions());
        assertNotNull(first.getAnswer());
        assertFalse(first.getOptions().isEmpty(), "Options should not be empty");
    }

}
