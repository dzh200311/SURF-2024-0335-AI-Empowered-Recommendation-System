package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Order;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderService {
    List<Order> getOrdersByUserId(int userId);

    int getNextOrder();
    Order querySingleByOrderId(int orderId);
    List<Order> queryAllOrders();
    public boolean addOrder(Order order);
    boolean deleteOrder(int orderId);
    boolean updateOrder(int orderId, double totalPrice, Integer totalDiscountId, Timestamp orderDate, Timestamp purchasedDate,
                        Integer paymentMethod, Integer orderState, Integer location, Integer diningStyle, Integer requirementId,
                        String tableNumber, Integer addressId, double actualPayment, Integer riderId, Integer userId, Integer storeId);
}
