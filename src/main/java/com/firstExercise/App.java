package com.firstExercise;

import com.firstExercise.config.AppConfig;
import com.firstExercise.service.QuestionService;
import com.firstExercise.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testService = context.getBean(TestService.class);
        testService.startTest();
        context.close();
    }
}