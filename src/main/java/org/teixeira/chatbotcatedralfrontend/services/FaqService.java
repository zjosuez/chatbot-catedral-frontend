package org.teixeira.chatbotcatedralfrontend.services;

import org.springframework.stereotype.Service;
import org.teixeira.chatbotcatedralfrontend.domain.FaqAnswer;
import org.teixeira.chatbotcatedralfrontend.utils.FaqAnswers;

import java.util.Arrays;
import java.util.List;

@Service
public class FaqService {
    final private FaqAnswers faqAnswers = new FaqAnswers();

    public String getAnswer(String question) {
        List<String> wordsList = Arrays.asList(question.toLowerCase().split("\\s+"));
        return faqAnswers.getAnswers().stream()
                .filter(entry -> entry.getKeywords().stream().anyMatch(wordsList::contains))
                .findFirst()
                .map(FaqAnswer::getAnswer)
                .orElse(faqAnswers.getDefaultAnswer());
    }
}
