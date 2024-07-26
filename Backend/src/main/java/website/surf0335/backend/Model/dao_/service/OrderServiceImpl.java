package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.OrderDao;
import website.surf0335.backend.Model.dao_.domain.Order;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class OrderServiceImpl implements  OrderService {
    private OrderDao orderDao = new OrderDao();
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order querySingleByOrderId(int orderId) {
        logger.info("Querying for order with ID: {}", orderId);
        return orderDao.querySingle("SELECT * FROM `order` WHERE `order_id` = ?", Order.class, orderId);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        logger.info("Querying for order with user ID: {}", userId);
        return orderDao.queryMultiple("SELECT * FROM `order` WHERE `user_id` = ?", Order.class, userId);
    }

    @Override
    public int getNextOrder() {
        logger.info("Getting next order ID");
        return orderDao.getLastOrderId() + 1;
    }

    @Override
    public List<Order> queryAllOrders() {
        logger.info("Querying for all orders");
        List<Order> orders = orderDao.queryMultiple("SELECT * FROM `order` WHERE `order_state` = 0 ORDER BY order_id DESC", Order.class);
        logger.info("Number of orders retrieved: {}", orders.size());
        return orders;
    }

    @Override
    public boolean addOrder(Order order) {
        logger.info("Adding new order with ID: {}, Total Price: {}, Payment Method: {}, Order State: {}, User ID: {}", order.getOrder_id(), order.getTotal_price(), order.getPayment_method(), order.getOrder_state(), order.getUser_id());
        String sql = "INSERT INTO `order` (`order_id`, `total_price`, `total_discount_id`, `order_date`, `purchased_date`, `payment_method`, `order_state`, `location`, `dining_style`, `requirement_id`, `table_number`, `address_id`, `actual_payment`, `rider_id`, `user_id`, `store_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int update = orderDao.update(sql, order.getOrder_id(), order.getTotal_price(), order.getTotal_discount_id(), order.getOrder_date(), order.getPurchased_date(), order.getPayment_method(), order.getOrder_state(), order.getLocation(), order.getDining_style(), order.getRequirement_id(), order.getTable_number(), order.getAddress_id(), order.getActual_payment(), order.getRider_id(), order.getUser_id(), order.getStore_id());
        return update > 0;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        logger.info("Attempting to delete order with ID: {}", orderId);
        int update = orderDao.update("DELETE FROM `order` WHERE `order_id` = ?", orderId);
        return update > 0;
    }

    @Override
    public boolean updateOrder(int orderId, double totalPrice, Integer totalDiscountId, Timestamp orderDate, Timestamp purchasedDate,
                               Integer paymentMethod, Integer orderState, Integer location, Integer diningStyle, Integer requirementId,
                               String tableNumber, Integer addressId, double actualPayment, Integer riderId, Integer userId, Integer storeId) {
        logger.info("Updating order ID: {}, Total Price: {}, Total Discount ID: {}, Order Date: {}, Purchased Date: {}, Payment Method: {}, Order State: {}, Location: {}, Dining Style: {}, Requirement ID: {}, Table Number: {}, Address ID: {}, Actual Payment: {}, Rider ID: {}, User ID: {}, Store ID: {}",
                orderId, totalPrice, totalDiscountId, orderDate, purchasedDate, paymentMethod, orderState, location, diningStyle, requirementId,
                tableNumber, addressId, actualPayment, riderId, userId, storeId);
        int update = orderDao.update("UPDATE `order` SET `total_price` = ?, `total_discount_id` = ?, `order_date` = ?, `purchased_date` = ?, `payment_method` = ?, `order_state` = ?, `location` = ?, `dining_style` = ?, `requirement_id` = ?, `table_number` = ?, `address_id` = ?, `actual_payment` = ?, `rider_id` = ?, `user_id` = ?, `store_id` = ? WHERE `order_id` = ?",
                totalPrice, totalDiscountId, orderDate, purchasedDate, paymentMethod, orderState, location, diningStyle, requirementId,
                tableNumber, addressId, actualPayment, riderId, userId, storeId, orderId);
        return update > 0;
    }

    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();

        Order order = new Order();
        order.setOrder_id(orderService.getNextOrder());
        order.setTotal_price(100.0);
        order.setTotal_discount_id(1);
        order.setOrder_date(Timestamp.valueOf(LocalDateTime.now()));
        order.setPurchased_date(Timestamp.valueOf(LocalDateTime.now()));
        order.setPayment_method(1);
        order.setOrder_state(0);
        order.setLocation(1);
        order.setDining_style(1);
        order.setRequirement_id(1);
        order.setTable_number("1");
        order.setAddress_id(1);
        order.setActual_payment(100.0);
        order.setRider_id(1);
        order.setUser_id(1);


        System.out.println(orderService.addOrder(order));
    }

}
