package com.firstExercise.dao;

import com.firstExercise.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private final String resourcePath;

    public QuestionDaoImpl(@Value("${app.questions-resource}") String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public List<Question> findAll() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourcePath)),
                        StandardCharsets.UTF_8))) {

            return reader.lines()
                    .map(line -> {
                        String[] parts = line.split(";");
                        if (parts.length < 3) throw new RuntimeException("Invalid CSV line: " + line);
                        String text = parts[0];
                        List<String> options = Arrays.asList(parts[1].split(","));
                        String answer = parts[2];
                        return new Question(text, options, answer);
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to read questions from CSV", e);
        }
    }
}
