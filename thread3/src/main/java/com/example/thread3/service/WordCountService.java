package com.example.thread3.service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class WordCountService {

    public long countWordOccurrences(String wordToCount) {
        AtomicLong wordCount = new AtomicLong(0);
        try (BufferedReader reader = new BufferedReader(new FileReader("random_words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.equals(wordToCount)) {
                        wordCount.incrementAndGet();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount.get();
    }
}
