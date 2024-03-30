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
        String[] words = question.toLowerCase().split("\\s+");
        List<String> wordsList = Arrays.asList(words);
        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyword : entry.getKeywords()) {
                if (wordsList.contains(keyword)) {
                    return entry.getAnswer();
                }
            }
        }
        return faqAnswers.getDeafultAnswer();
    }
}
