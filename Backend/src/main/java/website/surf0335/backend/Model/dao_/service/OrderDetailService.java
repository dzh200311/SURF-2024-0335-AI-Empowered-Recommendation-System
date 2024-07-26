package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailService {
    OrderDetail querySingleByOrderDetailId(int orderDetailId);

    List<OrderDetail> queryMultipleByOrderId(int orderId);

    boolean addOrderDetail(OrderDetail orderDetail);

    boolean deleteOrderDetail(int orderDetailId);

    boolean updateOrderDetail(int orderDetailId, int orderId, int productId, int quantity);

}
