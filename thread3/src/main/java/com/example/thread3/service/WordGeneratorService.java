package com.example.thread3.service;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class WordGeneratorService {

    private static final int THREAD_COUNT = 4; // 4 thread
    private static final int WORD_COUNT = 300000000; // 30 milyon kelime
    private static final int WORD_LENGTH = 5; // Kelime uzunluğu
    private static final String FILE_PATH = "random_words.txt"; // path

    // alfabenin icinden secmesi icin harfleri yaziyorum
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    // random wordleri olusturan metodum
    public void generateRandomWordsFile() {
        List<Thread> threads = new ArrayList<>();
        int wordsPerThread = WORD_COUNT / THREAD_COUNT;
        //kelime sayisini threade bolerek her thread basina dusecek kelime hesapliyorum
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                    Random random = new Random();
                    for (int j = 0; j < wordsPerThread; j++) {
                        String word = generateRandomWord(random);
                        writer.write(word);
                        writer.newLine();


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Tüm iş parçacıklarının tamamlanmasını bekleyelim.
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomWord(Random random) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < WORD_LENGTH; i++) {
            int index = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(index);
            word.append(randomChar);
        }
        return word.toString();
    }
}

