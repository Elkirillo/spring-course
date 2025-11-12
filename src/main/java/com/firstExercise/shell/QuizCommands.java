package com.firstExercise.shell;

import com.firstExercise.domain.Question;
import com.firstExercise.service.QuestionService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class QuizCommands {

    private final QuestionService questionService;

    public QuizCommands(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ShellMethod("Start student quiz")
    public void startQuiz(@ShellOption(defaultValue = "Student") String name) {
        List<Question> questions = questionService.getAllQuestions();
        Scanner scanner = new Scanner(System.in);

        int score = 0;

        System.out.println("Hello, " + name + "! Let's start the quiz:");

        for (Question q : questions) {
            System.out.println(q.getText());
            if (!q.getOptions().isEmpty()) {
                for (int i = 0; i < q.getOptions().size(); i++) {
                    System.out.println((i+1) + ". " + q.getOptions().get(i));
                }
            }

            System.out.print("Your answer: ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(q.getAnswer()) ||
                    (q.getOptions().contains(answer))) {
                score++;
            }
        }

        System.out.println("Quiz finished! Your score: " + score + "/" + questions.size());
    }
}
