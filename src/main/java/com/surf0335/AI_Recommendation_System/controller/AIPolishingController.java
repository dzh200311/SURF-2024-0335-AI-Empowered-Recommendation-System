package com.surf0335.AI_Recommendation_System.controller;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.auth.Auth;
import com.baidubce.qianfan.model.completion.CompletionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class AIPolishingController {
    //@Autowired
    //private AIPolishService polishService;

    @Value("${etherpad.api.url}")
    private String etherpadApiUrl;

    @Value("${baidu.ai.api.access}")
    private String baiduAccessKey;

    @Value("${baidu.ai.api.secret}")
    private String baiduApiSecret;

    private final RestTemplate restTemplate = new RestTemplate();


    private final ExecutorService executor = Executors.newCachedThreadPool();
    @GetMapping("/ai_polish")
    public String getAiPolishPage(Model m){
        return "AiPolishing";
    }

    /*public String getAiPrompt(@RequestParam("message") String userMessage, Model model) {
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH, "YUgx4nhKHxcjqPeSOLgJZ7Mc", "8PShKKkAlfsGdwniI5oGFZSw5aD1PK0v");
        System.out.println("receive message " + userMessage);
        // 使用传递的用户消息调用 AI 服务
        ChatResponse response = qianfan.chatCompletion()
                .model("ERNIE-4.0-8K") // 使用model指定预置模型
                .addMessage("user", userMessage) // 添加用户消息
                .temperature(0.7) // 自定义超参数
                .execute(); // 发起请求

        // 获取响应结果
        String result = response.getResult();
        System.out.println("receive result: " + result);
        // 将响应结果添加到模型中，以便前端渲染
        model.addAttribute("aiResponse", result);

        // 返回视图名称
        return "AiPolishing"; // 这里的视图名称应对应你的 Thymeleaf 模板名（不带 .html 后缀）
    }
*/
    /*@GetMapping("/ai_prompt")
    @ResponseBody
    public String getAiPrompt(@RequestParam String message, Model m){
        System.out.println(message);
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH, "YUgx4nhKHxcjqPeSOLgJZ7Mc", "8PShKKkAlfsGdwniI5oGFZSw5aD1PK0v");
        ChatResponse response = qianfan.chatCompletion()
                .model("ERNIE-4.0-8K")
                .addMessage("user", message)
                .temperature(0.7)
                .execute();
        System.out.println("receive result: " + response.getResult());
        return response.getResult();
    }*/
    @GetMapping("/ai_prompt")
    public SseEmitter getAiPrompt(@RequestParam String message) {
        SseEmitter emitter = new SseEmitter();
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH, baiduAccessKey, baiduApiSecret);
        executor.execute(() -> {
            try {
                qianfan.chatCompletion()
                        .model("ERNIE-4.0-8K")
                        .addMessage("user", message)
                        .executeStream()
                        .forEachRemaining(chunk -> {
                            try {
                                String result = chunk.getResult();
                                System.out.print("Received chunk: " + result); // 输出到控制台
                                emitter.send(SseEmitter.event().data(chunk.getResult()));
                            } catch (IOException e) {
                                e.printStackTrace();
                                emitter.completeWithError(e);
                            }
                        });
                emitter.complete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @PostMapping("/generate_letter")
    @ResponseBody
    public Map<String, String> generateLetter(@RequestParam Map<String, String> formData) {
        System.out.println("收到请求： " + formData);
        // 2. 调用百度 AI API 获取推荐信内容
        String content = generateContentWithBaiduAi(formData);

        // 3. 将内容插入到 Etherpad 文档
        String padId = createEtherpadDocument(content);

        // 4. 返回 Etherpad 文档 ID
        return Map.of("padId", padId);
    }

    private String generateContentWithBaiduAi(Map<String, String> formData) {
        // 1. 获取表单数据
        String refereeName = formData.get("refereeName");
        String gender = formData.get("gender");
        String position = formData.get("position");
        String phone = formData.get("phone");
        String email = formData.get("email");
        String organization = formData.get("organization");
        String address = formData.get("address");
        String acquaintanceTime = formData.get("acquaintanceTime");
        String acquaintanceEvent = formData.get("acquaintanceEvent");
        String abilities = formData.get("abilities");
        String examples = formData.get("examples");
        String refereeDetails = formData.get("refereeDetails");
        String languageLevel = formData.get("languageLevel");
        String otherRequirements = formData.get("otherRequirements");
        // 组合成prompt
        String prompt = generatePrompt(refereeName, gender, position, phone, email, organization, address,
                acquaintanceTime, acquaintanceEvent, abilities, examples, refereeDetails, languageLevel, otherRequirements);
        // 调用百度AI API生成推荐信内容
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH, baiduAccessKey, baiduApiSecret);
        CompletionResponse response = qianfan.completion()
                .model("CodeLlama-7b-Instruct")
                .prompt(prompt)
                .execute();
        System.out.println(response.getResult());
        return response.getResult();
    }

    private String generatePrompt(String refereeName, String gender, String position, String phone, String email,
                                  String organization, String address, String acquaintanceTime, String acquaintanceEvent,
                                  String abilities, String examples, String refereeDetails, String languageLevel, String otherRequirements) {
        return String.format("请根据以下信息生成一封英文推荐信:\n推荐人姓名: %s\n性别: %s\n职务: %s\n电话: %s\n邮箱: %s\n工作单位: %s\n通讯地址: %s\n" +
                        "与推荐人认识的时间: %s\n认识的事件: %s\n推荐人主要推荐的能力: %s\n推荐人推荐的能力实例: %s\n推荐人情况: %s\n" +
                        "推荐信语言程度: %s\n其他补充要求: %s",
                refereeName, gender, position, phone, email, organization, address, acquaintanceTime, acquaintanceEvent,
                abilities, examples, refereeDetails, languageLevel, otherRequirements);
    }
    private String generateRandomPadId() {
        // 生成唯一的 padID
        return UUID.randomUUID().toString();
    }
    private String createEtherpadDocument(String content) {
        System.out.println("createEtherpadDocument");
        String padID = generateRandomPadId();
        String etherpadCreateApiUrl = etherpadApiUrl + "/1/createPad";
        String createPadRequestUrl = etherpadCreateApiUrl + "?padID=" + padID + "&apikey=123456";
        String authorId = "1";

        content = "padID: " + padID + "\n\n" + content;
        // 创建 Etherpad 文档
        try {
            // 创建请求体
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            if (content != null) {
                requestBody.add("text", content);
            }
            if (authorId != null) {
                requestBody.add("authorId", authorId);
            }

            // 执行请求
            Map<String, Object> createResponse = restTemplate.postForObject(createPadRequestUrl, requestBody, Map.class);

            if (createResponse == null) {
                throw new RuntimeException("Failed to create pad: response is null");
            }

            // 处理响应
            Integer code = (Integer) createResponse.get("code");
            if (code == 0) {
                // 成功
                System.out.println("创建成功");
                return padID;
            } else {
                // 错误
                String message = (String) createResponse.get("message");
                throw new RuntimeException("Error creating pad: " + message);
            }
        } catch (HttpClientErrorException e) {
            // 记录详细的错误信息
            System.err.println("Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        }
    }



}
