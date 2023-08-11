package com.example.thread3.controller;



import com.example.thread3.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wordcount")
public class WordCountController {

    private final WordCountService wordCountService;

    @Autowired
    public WordCountController(WordCountService wordCountService) {
        this.wordCountService = wordCountService;
    }

    @GetMapping("/{word}")
    public long getWordCount(@PathVariable String word) {
        return wordCountService.countWordOccurrences(word);
    }
}
