package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.FeedbackOfDeliveryDao;
import website.surf0335.backend.Model.dao_.domain.FeedbackOfDelivery;

import java.util.List;

@Repository
public class FeedbackOfDeliveryServiceImpl implements FeedbackOfDeliveryService {
    private FeedbackOfDeliveryDao feedbackOfDeliveryDao = new FeedbackOfDeliveryDao();
    private static final Logger logger = LoggerFactory.getLogger(FeedbackOfDeliveryServiceImpl.class);

    public FeedbackOfDelivery querySingleByDeliveryFeedbackId(int deliveryFeedbackId) {
        logger.info("Querying for feedback of delivery with ID: {}", deliveryFeedbackId);
        return feedbackOfDeliveryDao.querySingle("SELECT * FROM `feedback_of_delivery` WHERE `delivery_feedback_id` = ?", FeedbackOfDelivery.class, deliveryFeedbackId);
    }

    public List<FeedbackOfDelivery> queryMultipleByOrderId(int orderId) {
        logger.info("Querying for feedbacks of delivery by order ID: {}", orderId);
        List<FeedbackOfDelivery> feedbacks = feedbackOfDeliveryDao.queryMultiple("SELECT * FROM `feedback_of_delivery` WHERE `order_id` = ?", FeedbackOfDelivery.class, orderId);
        logger.info("Number of feedbacks retrieved for order ID {}: {}", orderId, feedbacks.size());
        return feedbacks;
    }

    public boolean addFeedbackOfDelivery(int deliveryFeedbackId, int stars, String feedback, int orderId, int riderId, int userId) {
        logger.info("Adding new feedback of delivery with ID: {}, Stars: {}, Order ID: {}, Rider ID: {}, User ID: {}",
                deliveryFeedbackId, stars, orderId, riderId, userId);
        int update = feedbackOfDeliveryDao.update("INSERT INTO `feedback_of_delivery`(`delivery_feedback_id`, `stars`, `feedback`, `order_id`, `rider_id`, `user_id`) VALUES (?, ?, ?, ?, ?, ?)",
                deliveryFeedbackId, stars, feedback, orderId, riderId, userId);
        return update > 0;
    }

    public boolean deleteFeedbackOfDelivery(int deliveryFeedbackId) {
        logger.info("Attempting to delete feedback of delivery with ID: {}", deliveryFeedbackId);
        int update = feedbackOfDeliveryDao.update("DELETE FROM `feedback_of_delivery` WHERE `delivery_feedback_id` = ?", deliveryFeedbackId);
        return update > 0;
    }

    public boolean updateFeedbackOfDelivery(int deliveryFeedbackId, int stars, String feedback, int orderId, int riderId, int userId) {
        logger.info("Updating feedback of delivery ID: {}, Stars: {}, Order ID: {}, Rider ID: {}, User ID: {}",
                deliveryFeedbackId, stars, orderId, riderId, userId);
        int update = feedbackOfDeliveryDao.update("UPDATE `feedback_of_delivery` SET `stars` = ?, `feedback` = ?, `order_id` = ?, `rider_id` = ?, `user_id` = ? WHERE `delivery_feedback_id` = ?",
                stars, feedback, orderId, riderId, userId, deliveryFeedbackId);
        return update > 0;
    }



}
