package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.FeedbackOfDelivery;

import java.util.List;

@Repository
public interface FeedbackOfDeliveryService {
    FeedbackOfDelivery querySingleByDeliveryFeedbackId(int deliveryFeedbackId);

    List<FeedbackOfDelivery> queryMultipleByOrderId(int orderId);

    boolean addFeedbackOfDelivery(int deliveryFeedbackId, int stars, String feedback, int orderId, int riderId, int userId);

    boolean deleteFeedbackOfDelivery(int deliveryFeedbackId);

    boolean updateFeedbackOfDelivery(int deliveryFeedbackId, int stars, String feedback, int orderId, int riderId, int userId);
}
