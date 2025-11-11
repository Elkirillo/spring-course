package com.firstExercise.domain;


import java.util.List;

public class Question {
    private final String text;
    private final List<String> options;

    public Question(String text, List<String> options) {
        this.text = text;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(text + "\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((i + 1)).append(") ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}
