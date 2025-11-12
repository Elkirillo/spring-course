package com.firstExercise.dao;


import com.firstExercise.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDao {
    List<Question> getQuestions(Locale locale);

}
