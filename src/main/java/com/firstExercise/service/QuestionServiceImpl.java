package com.firstExercise.service;

import com.firstExercise.domain.Question;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionServiceImpl implements QuestionService  {

    private String resourcePath;

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void printAllQuestions() {
        List<Question> questions = loadQuestionsFromCsv(resourcePath);
        for (Question q : questions) {
            System.out.println(q);
        }
    }

    private List<Question> loadQuestionsFromCsv(String resourcePath) {
        List<Question> questions = new ArrayList<>();
        try {
            var resource = new ClassPathResource(resourcePath);
            try (var reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String questionText = parts[0];
                    List<String> options = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));
                    questions.add(new Question(questionText, options));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read questions from " + resourcePath, e);
        }
        return questions;
    }
}
