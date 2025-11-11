package com.firstExercise.runner;

import com.firstExercise.domain.Question;
import com.firstExercise.service.QuestionService;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Scanner;

@Component
public class TestRunner implements CommandLineRunner {

    private final QuestionService questionService;

    public TestRunner(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Hello " + firstName + " " + lastName + "! Let's start the test.");

        List<Question> questions = questionService.getAllQuestions();
        int correct = 0;

        for (Question q : questions) {
            System.out.println("\n" + q.getText());
            for (int i = 0; i < q.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + q.getOptions().get(i));
            }

            System.out.print("Your answer (number): ");
            int answerIndex = scanner.nextInt() - 1;

            if (q.getOptions().get(answerIndex).equals(q.getAnswer())) {
                correct++;
            }
        }

        System.out.println("\nTest finished. Correct answers: " + correct + "/" + questions.size());
    }
}
