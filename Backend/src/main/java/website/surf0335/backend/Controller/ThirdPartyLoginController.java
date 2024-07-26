package website.surf0335.backend.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class ThirdPartyLoginController {

    @PostMapping("/github")
    public String githubOAuth(@RequestParam String code) {
        String clientId = "6f1509e4afb4b359bc2a";
        String clientSecret = "ae11a416dd8955751278807fc429900d8508f942";

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(code);

        // Step 1: Get access token
        String tokenUrl = "https://github.com/login/oauth/access_token?client_id=6f1509e4afb4b359bc2a&client_secret=ae11a416dd8955751278807fc429900d8508f942&code=" + code;
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, null, String.class);
        System.out.println(tokenResponse);
        String accessToken = null;
        if (tokenResponse.getStatusCode().is2xxSuccessful()) {
            accessToken = tokenResponse.getBody().split("&")[0].split("=")[1];
        }

        System.out.println(accessToken + "//////asas");

        // Step 2: Get user data
//        String userUrl = "https://api.github.com/user";
//        HttpHeaders userHeaders = new HttpHeaders();
//        userHeaders.set("Authorization", "Bearer" + accessToken);
//        userHeaders.put("Accept", "application/vnd.github.v3+json");
//        userHeaders.setContentType(MediaType.APPLICATION_JSON);
//        ResponseEntity<GithubUserData> userResponse = restTemplate.getForEntity(userUrl, GithubUserData.class);
//        GithubUserData userData = userResponse.getBody();

        String apiUrl = "https://api.github.com/user";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

        // Add headers
        Map<String, List<String>> headers = new HashMap<>();
        List<String> acceptValues = new ArrayList<>();
        acceptValues.add("application/vnd.github.v3+json");
        headers.put("Accept", acceptValues);
        headers.put("Authorization", List.of("token " + accessToken)); // Authorization header
        headers.forEach((key, value) -> value.forEach(val -> requestBuilder.header(key, val)));

        // Build the request
        HttpRequest request = null;
        try {
            request = requestBuilder
                    .uri(new URI(apiUrl))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        }

        String responseBody = "";
        // Send the request and handle the response
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            responseBody = response.body();

            System.out.println("Response Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error making request: " + e.getMessage());
        }

        // Handle user data and session storage here

//        return ResponseEntity.ok().body(userData);
        return responseBody;
    }

    @PostMapping("/test")
    public String test() {
        return "Hello World!";
    }
    @GetMapping("/github1")
    public String githubOAuth1() {
        String clientId = "6f1509e4afb4b359bc2a";
        String clientSecret = "ae11a416dd8955751278807fc429900d8508f942";

        RestTemplate restTemplate = new RestTemplate();

        // Step 1: Get access token
        String tokenUrl = "https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=7f34b92acad4b5ee15e7";
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, null, String.class);
        String accessToken = null;
        if (tokenResponse.getStatusCode().is2xxSuccessful()) {
            accessToken = tokenResponse.getBody().split("&")[0].split("=")[1];
        }
        System.out.println(accessToken);

//        // Step 2: Get user data
//        String userUrl = "https://api.github.com/user";
//        HttpHeaders userHeaders = new HttpHeaders();
//        userHeaders.set("Authorization", "token " + accessToken);
//        userHeaders.setContentType(MediaType.APPLICATION_JSON);
//        ResponseEntity<GithubUserData> userResponse = restTemplate.getForEntity(userUrl, GithubUserData.class);
//        GithubUserData userData = userResponse.getBody();

        // Handle user data and session storage here

//        assert userData != null;
        return accessToken;
    }
}
