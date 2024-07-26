package website.surf0335.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.surf0335.backend.Model.dao_.domain.Order;
import website.surf0335.backend.Model.dao_.domain.OrderDetail;
import website.surf0335.backend.Model.dao_.service.OrderDetailService;
import website.surf0335.backend.Model.dao_.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping("/get_next_order")
    private int nextOrder(){
        return orderService.getNextOrder();
    }


    @RequestMapping("/get_order")
    public List<Order> getOrder(@RequestParam("user_id") int userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @RequestMapping("/get_order_detail")
    public List<OrderDetail> getOrderDetail(@RequestParam("order_id") int orderId) {
        return orderDetailService.queryMultipleByOrderId(orderId);
    }

    @GetMapping("/cook/get_order")
    public List<Order> getCookOrder() {
        return orderService.queryAllOrders();
    }


    @PostMapping("/add_order_detail")
    public ResponseEntity<String> addOrderDetail(@RequestParam("order_details") List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails){
            boolean result = orderDetailService.addOrderDetail(orderDetail);
            if (!result) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add order detail."
                        + orderDetail.toString());
            }
        }
        return ResponseEntity.ok("Order details added successfully.");
    }

    @PostMapping("/add_order")
    public ResponseEntity<String> addOrder(@RequestParam Order order) {

        // 请在数据库内的“total_discount”，“location”，customer_requirement，address，rider，store_list添加id为1的默认条目，否则会添加失败
        boolean result = orderService.addOrder(order);
        if (result) {
            return ResponseEntity.ok("Order added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add order.");
        }
    }


}
