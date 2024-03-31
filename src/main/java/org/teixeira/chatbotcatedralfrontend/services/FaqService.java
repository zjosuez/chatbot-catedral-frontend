package org.teixeira.chatbotcatedralfrontend.services;

import org.springframework.stereotype.Service;
import org.teixeira.chatbotcatedralfrontend.domain.FaqAnswer;
import org.teixeira.chatbotcatedralfrontend.utils.FaqAnswers;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class FaqService {
    final private FaqAnswers faqAnswers = new FaqAnswers();
    final private Pattern nonAsciiPattern = Pattern.compile("[^\\p{ASCII}]");
    final private Pattern specialCharsPattern = Pattern.compile("[/:;?!]");
    final private Pattern cedillaPattern = Pattern.compile("ç");

    public String getAnswer(String question) {
        String cleanedQuestion = cleanQuestion(question);
        Set<String> wordsSet = new HashSet<>(Arrays.asList(cleanedQuestion.toLowerCase().split("\\s+")));

        return faqAnswers.getAnswers().stream()
                .filter(entry -> entry.getKeywords().stream().anyMatch(keyword -> wordsSet.contains(keyword.toLowerCase())))
                .findFirst()
                .map(FaqAnswer::getAnswer)
                .orElse(faqAnswers.getDefaultAnswer());
    }

    private String cleanQuestion(String question) {
        String normalized = Normalizer.normalize(question, Normalizer.Form.NFD);
        String accentRemoved = nonAsciiPattern.matcher(normalized).replaceAll("");
        String cedillaReplaced = cedillaPattern.matcher(accentRemoved).replaceAll("c");
        return specialCharsPattern.matcher(cedillaReplaced).replaceAll("");
    }
}
