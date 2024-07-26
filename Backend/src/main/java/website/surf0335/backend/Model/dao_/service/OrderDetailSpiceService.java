package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.OrderDetailSpice;

import java.util.List;

@Repository
public interface OrderDetailSpiceService {
    OrderDetailSpice querySingleByOrderDetailIdAndSpiceId(int orderDetailId, int spiceId);

    List<OrderDetailSpice>queryMultipleByOrderDetailId(int orderDetailId);

    boolean addOrderDetailSpice(int orderDetailId, int spiceId);

    boolean deleteOrderDetailSpice(int orderDetailId, int spiceId);

    boolean updateOrderDetailSpice(int orderDetailId, int newSpiceId, int oldSpiceId);
}
