package website.surf0335.backend.Model.dao_.domain;

public class OrderDetail {
    private Integer order_detail_id;
    private Integer product_id;
    private Integer order_id;
    private Integer quantity;

    private String specification;

    public OrderDetail() {
    }

    public OrderDetail(String specification,Integer order_detail_id, Integer product_id, Integer order_id, Integer quantity) {
        this.order_detail_id = order_detail_id;
        this.product_id = product_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.specification = specification;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(Integer order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order_detail_id=" + order_detail_id +
                ", product_id=" + product_id +
                ", order_id=" + order_id +
                ", quantity=" + quantity +
                '}';
    }
}
