package com.boot.controller;

import com.boot.model.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ollama")
public class OllamaController {

    private final ChatClient chatClient;

    public OllamaController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> getHome() {
        log.debug("OllamaController.getHome");
        return ResponseEntity.ok(chatClient.prompt()
                .user("Please tell me a dad joke about computers")
                .call()
                .content());
    }

    @PostMapping("/prompt")
    public ResponseEntity<String> getPrompt(@RequestBody ChatModel chatModel) {
        log.debug("OllamaController.getPrompt");
        return ResponseEntity.ok(
                chatClient.prompt()
                        .user(chatModel.getPrompt())
                        .call()
                        .content());
    }
}
