package com.firstExercise.runner;

import com.firstExercise.domain.Question;
import com.firstExercise.service.MessageService;
import com.firstExercise.service.QuestionService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class TestRunner implements CommandLineRunner {

    private final QuestionService questionService;
    private final MessageService messageService;

    public TestRunner(QuestionService questionService, MessageService messageService) {
        this.questionService = questionService;
        this.messageService = messageService;
    }

    @Override
    public void run(String... args) {
        Locale locale = Locale.forLanguageTag("ru");
        System.out.println(messageService.getMessage("welcome", locale));
        questionService.getAllQuestions(locale)
                .forEach(q -> System.out.println(q.getText() + " — " + q.getAnswer()));
    }
}
