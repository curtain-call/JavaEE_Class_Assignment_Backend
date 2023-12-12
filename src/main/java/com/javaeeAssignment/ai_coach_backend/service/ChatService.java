package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.ChatRequest;
import com.javaeeAssignment.ai_coach_backend.dto.ChatResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ChatService {
    public ChatResponse getChatResonse(ChatRequest chatRequest) throws IOException, InterruptedException{
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/chatAI.py", chatRequest.getMessage());
//       Todo 这是什么用法
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to execute python script");
        }

        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setReply(output.toString());
        return chatResponse;
    }
}
