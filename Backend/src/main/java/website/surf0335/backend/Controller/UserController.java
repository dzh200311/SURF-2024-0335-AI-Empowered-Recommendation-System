package website.surf0335.backend.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import website.surf0335.backend.Model.dao_.domain.User;
import website.surf0335.backend.Model.dao_.service.UserService;
import website.surf0335.backend.Service.TokenService;
import website.surf0335.backend.utils.AjaxResult;
import website.surf0335.backend.utils.Result;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/modify_password")
    public boolean modifyPassword(@RequestBody User user){
        return userService.updatePassword(user);
    }


    @GetMapping("/get_user_pages")
    public int getUserPages(){
        Long total = userService.getUserNumber();
        System.out.println("total: " + total);
        System.out.println("total pages :" + (int) Math.ceil(total / 20.0));
        return (int) Math.ceil(total / 20.0);
    }

    @GetMapping("/get_user_total")
    public int getUserTotal(){
        Long total = userService.getUserNumber();
        System.out.println("total: " + total);
        return total.intValue();
    }

    @PostMapping("/block_user")
    public boolean blockUser(@RequestParam("id") int id){
        return userService.blockUser(id);
    }

    @PostMapping("/unblock_user")
    public boolean unblockUser(@RequestParam("id") int id){
        return userService.unblockUser(id);
    }

    @GetMapping("/get_all_user")
    public List<Map<String, Object>> getAllUser(@RequestParam("page") int page) {
        List<Map<String, Object>> userList = new ArrayList<>();
        int begin = (page - 1) * 20 + 1;
        List<User> users = userService.getPartUsers(begin);


        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userName", user.getUserName());
            userMap.put("name", user.getFullName());
            userMap.put("email", user.getEmail());
            userMap.put("github_login", user.getGithub_login());
            userMap.put("facebook_login", user.getFacebook_login());
            userMap.put("google_login", user.getGoogle_login());
            userMap.put("user_ID", user.getUser_id());
            userMap.put("phone", user.getPhone());
            userMap.put("enable", user.getEnable());
            userMap.put("membership", user.getMembership());
            userMap.put("credits", user.getCredits());
            userMap.put("avatar", user.getAvatar());

            userList.add(userMap);
        }
        System.out.println("userList: " + userList);

        return userList;
    }

    @PostMapping("/check_email")
    public Result checkEmail(@RequestParam("email") String email){
        boolean result = userService.checkEmail(email);
        if (result){
            return Result.error().data("error", "email already exists");
    }
        return Result.ok().data("message", "email is available");
    }

    @PostMapping("/reset_password")//api/user/reset_password?email=xxx&password=xxx
    public Boolean resetPassword(@RequestParam("email") String email,
                                 @RequestParam("password") String newPassword){
        return userService.updateUserPassword(newPassword, email);
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody String json) {
        System.out.println("Received JSON: " + json);

        // 解析 JSON
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Random random = new Random();
        int randomNumber = random.nextInt(12) + 1;

        // 获取用户名和密码
        String username = map.get("username");
        String password = map.get("password");
        String email = map.get("email");
        System.out.println("fullname: " + username + "; password: " + password);
        try {
            User user = new User();
            user.setUserName(username);
            user.setPasswordHash(password);
            user.setEmail(email);
            user.setEnable(99);
            user.setAvatar("http://120.26.136.194:8000/api/image/avatar/download?fileName=default_" + randomNumber + ".jpg");
            userService.addUser(user);
            System.out.println("finish add user");
            return AjaxResult.success(200,"ok");
        } catch (IllegalArgumentException c) {
            // Handle other exceptions;
            return AjaxResult.fail(500, c.getMessage());
        }
    }


    @PostMapping("/login")
    public Result login(@RequestBody String json){
        System.out.println("Received JSON: " + json);

        // 解析 JSON
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 获取用户名和密码
        String email = map.get("username");
        String password = map.get("password");

        User user  = userService.userLogin(email);
        if (user != null) {
            if (user.getPasswordHash().equals(password)){
                if (user.getEnable() == 1){
                    return Result.error().data("error", "user blocked");
                }else {
                    Map<String,String> message = new HashMap<>();
                    String token = tokenService.generateTempToken(String.valueOf(user.getUser_id()));
                    System.out.println("success token: " + token);
                    message.put("token", token);
                    message.put("id", String.valueOf(user.getUser_id()));
                    message.put("name", user.getUserName());
                    message.put("email", user.getEmail());
                    message.put("avatar", user.getAvatar());

                    if (user.getEnable() == 99){
                        message.put("enable", "99");
                        return Result.ok().data(message);
                    }
                    return Result.ok().data(message);
                }

            }else {
                return Result.error().data("error", "password error");
            }
        }else {
            return Result.error().data("error", "user not found");
        }

    }
}
