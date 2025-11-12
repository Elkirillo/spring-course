package com.firstExercise.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Question {
    private String text;
    private String answer;
    private List<String> options = new ArrayList<>();

    public Question() {} // нужен для Spring / Jackson

    public Question(String text, String answer, List<String> options) {
        this.text = text;
        this.answer = answer;
        this.options = options;
    }
}
