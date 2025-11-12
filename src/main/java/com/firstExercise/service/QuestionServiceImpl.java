package com.firstExercise.service;

import com.firstExercise.dao.QuestionDao;
import com.firstExercise.domain.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Locale;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final MessageSource messageSource;

    public QuestionServiceImpl(QuestionDao questionDao, MessageSource messageSource) {
        this.questionDao = questionDao;
        this.messageSource = messageSource;
    }

    @Override
    public List<Question> getAllQuestions(Locale locale) {
        String welcome = messageSource.getMessage("welcome", null, locale);
        System.out.println(welcome);

        return questionDao.getQuestions(locale);
    }
}
