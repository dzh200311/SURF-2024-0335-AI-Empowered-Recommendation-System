package website.surf0335.backend.Model.dao_.domain;

public class OrderDetailSpice {
    private Integer order_tail_id;
    private Integer spice_id;

    public OrderDetailSpice() {
    }

    public OrderDetailSpice(Integer order_tail_id, Integer spice_id) {
        this.order_tail_id = order_tail_id;
        this.spice_id = spice_id;
    }

    public Integer getOrder_tail_id() {
        return order_tail_id;
    }

    public void setOrder_tail_id(Integer order_tail_id) {
        this.order_tail_id = order_tail_id;
    }

    public Integer getSpice_id() {
        return spice_id;
    }

    public void setSpice_id(Integer spice_id) {
        this.spice_id = spice_id;
    }

    @Override
    public String toString() {
        return "OrderDetailSpice{" +
                "order_tail_id=" + order_tail_id +
                ", spice_id=" + spice_id +
                '}';
    }
}
