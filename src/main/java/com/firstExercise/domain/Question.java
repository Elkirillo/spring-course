package com.firstExercise.domain;


import java.util.List;

public class Question {
    private final String text;
    private final List<String> options;
    private final String answer;

    public Question(String text, List<String> options, String answer) {
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getText() { return text; }
    public List<String> getOptions() { return options; }
    public String getAnswer() { return answer; }
}
