package com.firstExercise.service;

import com.firstExercise.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionService {
    List<Question> getAllQuestions(Locale locale);
}
