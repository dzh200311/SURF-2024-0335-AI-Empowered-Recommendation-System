package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.OrderDao;
import website.surf0335.backend.Model.dao_.dao.OrderDetailDao;
import website.surf0335.backend.Model.dao_.domain.OrderDetail;

import java.util.List;
@Repository
public class OrderDetailServiceImpl implements OrderDetailService{
    private OrderDetailDao orderDetailDao = new OrderDetailDao();
    private OrderDao orderDao = new OrderDao();
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    public OrderDetail querySingleByOrderDetailId(int orderDetailId) {
        logger.info("Querying for order detail with ID: {}", orderDetailId);
        return orderDetailDao.querySingle("SELECT * FROM `order_detail` WHERE order_detail_id = ?", OrderDetail.class, orderDetailId);
    }

    public List<OrderDetail> queryMultipleByOrderId(int orderId) {
        logger.info("Querying for order details by order ID: {}", orderId);
        List<OrderDetail> orderDetails = orderDetailDao.queryMultiple("SELECT * FROM `order_detail` WHERE order_id = ?", OrderDetail.class, orderId);
        logger.info("Number of order details retrieved for order ID {}: {}", orderId, orderDetails.size());
        return orderDetails;
    }

    public boolean addOrderDetail(OrderDetail orderDetail) {
        logger.info("Adding new order detail with ID: {}, Order ID: {}, Product ID: {}, Quantity: {}, specification: {}",
                orderDetail.getOrder_detail_id(), orderDetail.getOrder_id(), orderDetail.getProduct_id(), orderDetail.getQuantity(), orderDetail.getSpecification());
        int update = orderDetailDao.update("INSERT INTO `order_detail`(`order_detail_id`, `order_id`, `product_id`, `quantity` , `specification`) VALUES (?, ?, ?, ?,?)",
                orderDetail.getOrder_detail_id(), orderDetail.getOrder_id(), orderDetail.getProduct_id(), orderDetail.getQuantity(), orderDetail.getSpecification());
        return update > 0;
    }

    public boolean deleteOrderDetail(int orderDetailId) {
        logger.info("Attempting to delete order detail with ID: {}", orderDetailId);
        int update = orderDetailDao.update("DELETE FROM `order_detail` WHERE `order_detail_id` = ?", orderDetailId);
        return update > 0;
    }

    public boolean updateOrderDetail(int orderDetailId, int orderId, int productId, int quantity) {
        logger.info("Updating order detail ID: {}, Order ID: {}, Product ID: {}, Quantity: {}",
                orderDetailId, orderId, productId, quantity);
        int update = orderDetailDao.update("UPDATE `order_detail` SET `order_id` = ?, `product_id` = ?, `quantity` = ? WHERE `order_detail_id` = ?",
                orderId, productId, quantity, orderDetailId);
        return update > 0;
    }


    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailServiceImpl();
    }

}
