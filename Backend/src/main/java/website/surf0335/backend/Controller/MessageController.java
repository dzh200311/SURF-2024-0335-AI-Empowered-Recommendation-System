package website.surf0335.backend.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import website.surf0335.backend.Model.dao_.domain.Receive;
import website.surf0335.backend.Model.dao_.domain.Send;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Receive greeting(Send message) throws Exception {
        Thread.sleep(1000);
        return new Receive("Line1: " + HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/cook")
    @SendTo("/topic/cook")
    public Receive greeting1(Send message) throws Exception {
        Thread.sleep(1000);
        return new Receive(HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/payment")
    @SendTo("/topic/payment")
    public Receive payment(Send message) throws Exception {
        Thread.sleep(1000);
        return new Receive(HtmlUtils.htmlEscape(message.getName()));
    }
}

