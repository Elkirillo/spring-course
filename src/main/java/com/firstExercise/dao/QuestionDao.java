package com.firstExercise.dao;


import com.firstExercise.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
