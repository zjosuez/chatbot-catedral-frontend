package org.teixeira.chatbotcatedralfrontend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teixeira.chatbotcatedralfrontend.dto.MessageRequest;
import org.teixeira.chatbotcatedralfrontend.utils.FaqAnswers;

@RestController
@RequestMapping("/api/chat")
public class FaqController {
    @PostMapping
    public ResponseEntity<String> answerQuestion(@RequestBody MessageRequest request) {
        FaqAnswers faqAnswers = new FaqAnswers();
        return ResponseEntity.ok("deu certo");
    }
}
