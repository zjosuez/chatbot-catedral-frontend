package org.teixeira.chatbotcatedralfrontend.utils;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.teixeira.chatbotcatedralfrontend.domain.FaqAnswer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FaqAnswers {
    private ArrayList<FaqAnswer> answers;
    private String deafultAnswer;

    public FaqAnswers() {
        try {
            JSONTokener tokener = new JSONTokener(new FileInputStream("src/main/resources/static/answers.json"));
            JSONObject faqDAta = new JSONObject(tokener);
            JSONArray faqArray = faqDAta.getJSONArray("faq");
            this.answers = new ArrayList<>();

            for (int i = 0; i < faqArray.length(); i++) {
                JSONObject faqEntry = faqArray.getJSONObject(i);
                JSONArray keywordsArray = faqEntry.getJSONArray("keywords");
                List<String> keywords = new ArrayList<>();

                for (int j = 0; j< keywordsArray.length(); j++) {
                    keywords.add(keywordsArray.getString(j));
                }

                String answer = faqEntry.getString("answer");
                this.answers.add(new FaqAnswer(keywords, answer));
            }

            this.deafultAnswer = faqDAta.getString("default");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
