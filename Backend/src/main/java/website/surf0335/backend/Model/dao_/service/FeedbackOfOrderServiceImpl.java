package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.FeedbackOfOrderDao;
import website.surf0335.backend.Model.dao_.domain.FeedbackOfOrder;

import java.util.List;

@Repository
public class FeedbackOfOrderServiceImpl implements FeedbackOfOrderService {
    private FeedbackOfOrderDao feedbackOfOrderDao = new FeedbackOfOrderDao();
    private static final Logger logger = LoggerFactory.getLogger(FeedbackOfOrderServiceImpl.class);

    @Override
    public FeedbackOfOrder querySingleByOrderFeedbackId(int orderFeedbackId) {
        logger.info("Querying for feedback with Order Feedback ID: {}", orderFeedbackId);
        return feedbackOfOrderDao.querySingle("SELECT * FROM `feedback_of_order` WHERE `order_feedback_id` = ?", FeedbackOfOrder.class, orderFeedbackId);
    }

    @Override
    public List<FeedbackOfOrder> queryMultipleByStoreId(int storeId) {
        logger.info("Querying for feedbacks by Store ID: {}", storeId);
        List<FeedbackOfOrder> feedbacks = feedbackOfOrderDao.queryMultiple("SELECT * FROM `feedback_of_order` WHERE `store_id` = ?", FeedbackOfOrder.class, storeId);
        logger.info("Number of feedbacks retrieved for Store ID {}: {}", storeId, feedbacks.size());
        return feedbacks;
    }

    @Override
    public List<FeedbackOfOrder> queryMultipleByProduct(int productId) {
        return feedbackOfOrderDao.queryMultiple("SELECT * FROM `feedback_of_order` WHERE `product_id` = ?", FeedbackOfOrder.class, productId);
    }

    @Override
    public List<FeedbackOfOrder> getRecent() {
        logger.info("Querying for recent 10 feedbacks");
        return feedbackOfOrderDao.queryMultiple("SELECT * FROM `feedback_of_order` ORDER BY `order_feedback_id` DESC LIMIT 10", FeedbackOfOrder.class);
    }

    @Override
    public boolean addFeedback(FeedbackOfOrder feedbackOfOrder) {

        int update = feedbackOfOrderDao.update("INSERT INTO `feedback_of_order`(`order_feedback_id`, `product_id`, `stars`, `feedback`, `image`, `store_id`) VALUES (?,?, ?,?, ?, ?)",
               feedbackOfOrder.getOrder_feedback_id(), feedbackOfOrder.getProductID(), feedbackOfOrder.getStars(), feedbackOfOrder.getFeedback(), feedbackOfOrder.getImages(), feedbackOfOrder.getStore_id());
        return update > 0;
    }

    @Override
    public boolean deleteFeedback(int orderFeedbackId) {
        logger.info("Attempting to delete feedback with Order Feedback ID: {}", orderFeedbackId);
        int update = feedbackOfOrderDao.update("DELETE FROM `feedback_of_order` WHERE `order_feedback_id` = ?", orderFeedbackId);
        return update > 0;
    }

    @Override
    public boolean updateFeedback(int orderFeedbackId, int stars, int orderItemId, int storeId) {
        logger.info("Updating feedback Order Feedback ID: {}, Stars: {}, Order Item ID: {}, Store ID: {}",
                orderFeedbackId, stars, orderItemId, storeId);
        int update = feedbackOfOrderDao.update("UPDATE `feedback_of_order` SET `stars` = ?, `order_item_id` = ?, `store_id` = ? WHERE `order_feedback_id` = ?",
                stars, orderItemId, storeId, orderFeedbackId);
        return update > 0;
    }

    public static void main(String[] args) {
        FeedbackOfOrderServiceImpl feedbackOfOrderService = new FeedbackOfOrderServiceImpl();
        System.out.println(feedbackOfOrderService.getRecent());
    }


}