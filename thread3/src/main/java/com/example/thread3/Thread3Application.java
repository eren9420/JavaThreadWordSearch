package com.example.thread3;



import javax.annotation.PostConstruct;

import com.example.thread3.service.WordGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Thread3Application {

    private final ApplicationContext context;

    public Thread3Application(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Thread3Application.class, args);
    }

    @Bean
    public WordGeneratorService wordGeneratorService() {
        return new WordGeneratorService();
    }

    @PostConstruct
    public void generateRandomWordsFile() {
        WordGeneratorService wordGeneratorService = context.getBean(WordGeneratorService.class);
        wordGeneratorService.generateRandomWordsFile();
    }
}

