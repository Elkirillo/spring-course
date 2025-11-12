package com.firstExercise.service;

import com.firstExercise.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

    private final QuestionService questionService;

    public TestServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void startTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("\nHello " + firstName + " " + lastName + "! Let's start the test.\n");

        List<Question> questions = questionService.getAllQuestions();
        int score = 0;

        for (Question q : questions) {
            System.out.println(q.getText());
            List<String> options = q.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(q.getAnswer())) {
                score++;
            }
            System.out.println();
        }

        System.out.println("Test finished! " + firstName + ", your score: " + score + "/" + questions.size());
    }
}
