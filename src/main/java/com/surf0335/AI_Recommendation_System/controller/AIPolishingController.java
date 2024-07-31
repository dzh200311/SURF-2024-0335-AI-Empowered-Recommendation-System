package com.surf0335.AI_Recommendation_System.controller;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.auth.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class AIPolishingController {
    //@Autowired
    //private AIPolishService polishService;

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
        Qianfan qianfan = new Qianfan(Auth.TYPE_OAUTH, "YUgx4nhKHxcjqPeSOLgJZ7Mc", "8PShKKkAlfsGdwniI5oGFZSw5aD1PK0v");
        executor.execute(() -> {
            try {
                qianfan.chatCompletion()
                        .model("ERNIE-4.0-8K")
                        .addMessage("user", message)
                        .executeStream()
                        .forEachRemaining(chunk -> {
                            try {
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


}
