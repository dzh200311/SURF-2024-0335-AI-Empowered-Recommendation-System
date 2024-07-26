package website.surf0335.backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestFrontEndController {

    @GetMapping("/send_email")
    public String getEmailPage(){
        return "testEmail";
    }

    @GetMapping("/page1")
    public String getEmailPage1(){
        return "page1";
    }

    @GetMapping("/sender")
    public String getSend(){
        return "sender";
    }
    @GetMapping("receiver")
    public String getReceive(){
        return "receiver";
    }
}
