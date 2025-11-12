package service;


import com.firstExercise.App;
import com.firstExercise.config.AppConfig;
import com.firstExercise.domain.Question;
import com.firstExercise.service.QuestionService;
import com.firstExercise.service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void testGetAllQuestionsRussian() {
        Locale locale = new Locale("ru");
        List<Question> questions = questionService.getAllQuestions(locale);

        assertThat(questions).isNotEmpty();
        assertThat(questions.get(0).getText()).isNotBlank();
    }
}