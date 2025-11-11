package com.firstExercise;

import com.firstExercise.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("context.xml")) {

            QuestionService questionService = context.getBean(QuestionService.class);
            questionService.printAllQuestions();
        }
    }
}