package com.spring.ai.aws_learning.controller;

import java.util.Map;

import org.springframework.ai.bedrock.titan.BedrockTitanChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class AWSChatController {
	
    private final BedrockTitanChatModel chatModel;

    public AWSChatController(BedrockTitanChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("aws/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatModel.call(message));
    }
    
    @GetMapping("aws/ai/generateStream")
	public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }

}
