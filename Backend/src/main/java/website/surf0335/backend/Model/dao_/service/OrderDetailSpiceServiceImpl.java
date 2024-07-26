package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.OrderDetailSpiceDao;
import website.surf0335.backend.Model.dao_.domain.OrderDetailSpice;

import java.util.List;
@Repository
public class OrderDetailSpiceServiceImpl implements OrderDetailSpiceService {
    private OrderDetailSpiceDao orderDetailSpiceDao = new OrderDetailSpiceDao();
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailSpiceServiceImpl.class);

    public OrderDetailSpice querySingleByOrderDetailIdAndSpiceId(int orderDetailId, int spiceId) {
        logger.info("Querying for order detail spice with Order Detail ID: {} and Spice ID: {}", orderDetailId, spiceId);
        return orderDetailSpiceDao.querySingle("SELECT * FROM `order_detail_spice` WHERE `order_detail_id` = ? AND `spice_id` = ?", OrderDetailSpice.class, orderDetailId, spiceId);
    }

    public List<OrderDetailSpice> queryMultipleByOrderDetailId(int orderDetailId) {
        logger.info("Querying for order detail spices by Order Detail ID: {}", orderDetailId);
        List<OrderDetailSpice> orderDetailSpices = orderDetailSpiceDao.queryMultiple("SELECT * FROM `order_detail_spice` WHERE `order_detail_id` = ?", OrderDetailSpice.class, orderDetailId);
        logger.info("Number of order detail spices retrieved for Order Detail ID {}: {}", orderDetailId, orderDetailSpices.size());
        return orderDetailSpices;
    }

    public boolean addOrderDetailSpice(int orderDetailId, int spiceId) {
        logger.info("Adding new order detail spice with Order Detail ID: {} and Spice ID: {}", orderDetailId, spiceId);
        int update = orderDetailSpiceDao.update("INSERT INTO `order_detail_spice`(`order_detail_id`, `spice_id`) VALUES (?, ?)",
                orderDetailId, spiceId);
        return update > 0;
    }

    public boolean deleteOrderDetailSpice(int orderDetailId, int spiceId) {
        logger.info("Attempting to delete order detail spice with Order Detail ID: {} and Spice ID: {}", orderDetailId, spiceId);
        int update = orderDetailSpiceDao.update("DELETE FROM `order_detail_spice` WHERE `order_detail_id` = ? AND `spice_id` = ?",
                orderDetailId, spiceId);
        return update > 0;
    }

    public boolean updateOrderDetailSpice(int orderDetailId, int newSpiceId, int oldSpiceId) {
        logger.info("Updating order detail spice from old Spice ID: {} to new Spice ID: {} for Order Detail ID: {}", oldSpiceId, newSpiceId, orderDetailId);
        int update = orderDetailSpiceDao.update("UPDATE `order_detail_spice` SET `spice_id` = ? WHERE `order_detail_id` = ? AND `spice_id` = ?",
                newSpiceId, orderDetailId, oldSpiceId);
        return update > 0;
    }

    public static void main(String[] args) {
        OrderDetailSpiceServiceImpl orderDetailSpiceService = new OrderDetailSpiceServiceImpl();
        List<OrderDetailSpice> spices = orderDetailSpiceService.queryMultipleByOrderDetailId(1);
        System.out.println(spices);
    }
}
