package website.surf0335.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import website.surf0335.backend.Model.dao_.domain.CommentOfFeedback;
import website.surf0335.backend.Model.dao_.domain.FeedbackOfOrder;
import website.surf0335.backend.Model.dao_.service.CommentOfFeedbackService;
import website.surf0335.backend.Model.dao_.service.FeedbackOfOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackOfOrderService feedbackOfOrderService;

    @Autowired
    private CommentOfFeedbackService commentOfFeedbackService;

    @GetMapping("/get_recent_feedback")
    public List<FeedbackOfOrder> getRecentFeedback() {
        return feedbackOfOrderService.getRecent();
    }

    @GetMapping("/get_feedback_by_product")
    public List<FeedbackOfOrder> getFeedbackByProduct(int productId) {
        return feedbackOfOrderService.queryMultipleByProduct(productId);
    }

    @GetMapping("/get_comment")
    public List<CommentOfFeedback> getComment(int orderID) {
        return commentOfFeedbackService.queryMultipleByFeedbackId(orderID);
    }

    @PostMapping("/add_feedback")
    public boolean addFeedback(@RequestParam ("feedback") List<FeedbackOfOrder> feedbackOfOrders){
        for (FeedbackOfOrder feedbackOfOrder : feedbackOfOrders){
            boolean result = feedbackOfOrderService.addFeedback(feedbackOfOrder);
            if (!result) {
                return false;
            }
        }
        return true;
    }
    @PostMapping("/add_feedback1")
    public boolean addFeedback1(){
        FeedbackOfOrder feedbackOfOrder = new FeedbackOfOrder(null,5,1,1,"good","");
            boolean result = feedbackOfOrderService.addFeedback(feedbackOfOrder);
            if (!result) {
                return false;
            }

        return true;
    }

}
