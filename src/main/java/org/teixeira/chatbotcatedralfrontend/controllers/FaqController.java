package org.teixeira.chatbotcatedralfrontend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teixeira.chatbotcatedralfrontend.dto.MessageRequest;
import org.teixeira.chatbotcatedralfrontend.dto.MessageResponse;
import org.teixeira.chatbotcatedralfrontend.services.FaqService;
import org.teixeira.chatbotcatedralfrontend.utils.FaqAnswers;

@RestController
@RequestMapping("/api/chat")
public class FaqController {
    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> answerQuestion(@RequestBody MessageRequest request) {
        String answer = faqService.getAnswer(request.message());
        MessageResponse response = new MessageResponse(answer);
        return ResponseEntity.ok(response);
    }
}
