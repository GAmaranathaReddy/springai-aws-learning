package com.spring.ai.aws_learning.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingModel.InputType;
import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSEmbedController {
	
	private final EmbeddingModel embeddingModel;
	
	@Autowired
    public AWSEmbedController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }
    
    @GetMapping("/ai/embedding")
    public Map embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    	EmbeddingResponse embeddingResponse = embeddingModel.call(
    		    new EmbeddingRequest(List.of("Hello World", "World is big and salvation is near"),
    		        BedrockTitanEmbeddingOptions.builder()
    		        	.withInputType(InputType.TEXT)
    		        .build()));
        return Map.of("embedding", embeddingResponse);
    }

}
