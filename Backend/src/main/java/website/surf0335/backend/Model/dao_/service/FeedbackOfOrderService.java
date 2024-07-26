package website.surf0335.backend.Model.dao_.service;


import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.FeedbackOfOrder;

import java.util.List;
@Repository
public interface FeedbackOfOrderService {
    public List<FeedbackOfOrder> queryMultipleByProduct(int productId);

    public List<FeedbackOfOrder> getRecent();

    FeedbackOfOrder querySingleByOrderFeedbackId(int orderFeedbackId);

    List<FeedbackOfOrder> queryMultipleByStoreId(int storeId);

    boolean addFeedback(FeedbackOfOrder feedbackOfOrder);

    boolean deleteFeedback(int feedbackId);

    boolean updateFeedback(int feedbackId, int stars, int orderItemId, int storeId);
}