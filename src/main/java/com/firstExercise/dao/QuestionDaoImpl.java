package com.firstExercise.dao;

import com.firstExercise.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private final String resourcePath;

    public QuestionDaoImpl(@Value("${app.questions-resource}") String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public List<Question> getQuestions(Locale locale) {
        String fileName = locale.getLanguage().equals("ru") ? resourcePath + "questions_ru.csv"
                : resourcePath + "questions.csv";

        List<Question> questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream(fileName), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length >= 2) {
                    String text = parts[0];
                    String answer = parts[1];
                    List<String> options = new ArrayList<>();

                    // добавляем варианты, если они есть
                    if (parts.length > 2) {
                        for (int i = 2; i < parts.length; i++) {
                            options.add(parts[i]);
                        }
                    }

                    questions.add(new Question(text, answer, options));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read questions file", e);
        }

        return questions;
    }
}
