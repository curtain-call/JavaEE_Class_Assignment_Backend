package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.model.ChatRequest;
import com.javaeeAssignment.ai_coach_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/usepy")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/usepy/call/{scriptType}")
    public String callPythonScript(@PathVariable String scriptType, @RequestBody(required = false) ChatRequest chatRequest) throws IllegalAccessException {
//  ToDo 路径参数显然还是字符串枚举比较好
        if (scriptType.equals("push-up")){
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/push_up.py", "--standard_example", "", "--custom_video", "");
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

//            读取进程的标准输出流, 命令行输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    // Handle errors if needed
                    return "Error occurred during python script execution.";
                }

                return output.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error occurred.";
            }
        } else if (scriptType.equals("chat")) {
            if (chatRequest == null){
                throw new IllegalAccessException("ChatRequest cannot be null when type == chat");
            }
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/resources/chatAI.py", "--message", chatRequest.getMessage());
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

//            读取进程的标准输出流, 命令行输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    // Handle errors if needed
                    return "Error occurred during python script execution.";
                }

                return output.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error occurred.";
            }
        }else
            return "not implemented";

    }
}
