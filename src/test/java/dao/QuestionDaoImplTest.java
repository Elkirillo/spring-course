package dao;

import com.firstExercise.App;
import com.firstExercise.dao.QuestionDao;
import com.firstExercise.dao.QuestionDaoImpl;
import com.firstExercise.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void testLoadQuestions() {
        List<Question> questions = questionDao.getQuestions(new Locale("ru"));
        assertThat(questions.size()).isGreaterThan(0);
    }
}
