package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Letter;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.repository.LetterRepository;
import com.surf0335.AI_Recommendation_System.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@RestController

public class LetterController {

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private UserRepo userRepository;

    @Value("${etherpad.api.url}")
    private String etherpadApiUrl;

    @Value("${etherpad.api.key}")
    private String etherpadApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getLetters")
    public List<Letter> getLettersByUsername(@RequestParam("username") String username) {
        return letterRepository.findByUserUsername(username);
    }

    @DeleteMapping("/deleteLetters")
    public ResponseEntity<?> deleteLetter(@RequestBody DeleteLetterRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        System.out.println("username: "+user.getUsername());

        // 查询需要删除的Letter
        Letter letter = letterRepository.findByPadIdAndUser(request.getPadId(), user);
        if (letter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Letter not found");
        }

        // 删除推荐信
        letterRepository.delete(letter);
        deleteEtherpad(request.getPadId());

        return ResponseEntity.ok().build();
    }

    private Integer deleteEtherpad(String padID){
        System.out.println("createEtherpadDocument");
        String etherpadCreateApiUrl = etherpadApiUrl + "/1/deletePad";
        String createPadRequestUrl = etherpadCreateApiUrl + "?padID=" + padID + "&apikey=" + etherpadApiKey;;

        // 创建 Etherpad 文档
        try {
            // 创建请求体
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();


            // 执行请求
            Map<String, Object> createResponse = restTemplate.postForObject(createPadRequestUrl, requestBody, Map.class);

            if (createResponse == null) {
                throw new RuntimeException("Failed to create pad: response is null");
            }

            // 处理响应
            Integer code = (Integer) createResponse.get("code");
            if (code == 0) {
                // 成功
                System.out.println("删除成功");
                return 0;
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

    public static class DeleteLetterRequest {
        private String username;
        private String padId;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPadId() {
            return padId;
        }

        public void setPadId(String padId) {
            this.padId = padId;
        }
    }

}
