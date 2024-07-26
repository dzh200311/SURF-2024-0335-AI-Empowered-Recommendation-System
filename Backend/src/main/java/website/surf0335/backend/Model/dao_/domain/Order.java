package website.surf0335.backend.Model.dao_.domain;

import java.sql.Time;
import java.sql.Timestamp;

public class Order {
    private Integer order_id;
    private double total_price;
    private Integer total_discount_id;
    private Timestamp order_date;
    private Timestamp purchased_date;
    private Integer payment_method;
    private Integer order_state;
    private Integer location;
    private Integer dining_style;
    private Integer requirement_id;
    private String table_number;
    private Integer address_id;
    private double actual_payment;
    private Integer rider_id;
    private Integer user_id;
    private Integer store_id;

    public Order() {
    }

    public Order(Integer order_id, double total_price, Integer total_discount_id, Timestamp order_date, Timestamp purchased_date, Integer payment_method, Integer order_state, Integer location, Integer dining_style, Integer requirement_id, String table_number, Integer address_id, double actual_payment, Integer rider_id, Integer user_id, Integer store_id) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.total_discount_id = total_discount_id;
        this.order_date = order_date;
        this.purchased_date = purchased_date;
        this.payment_method = payment_method;
        this.order_state = order_state;
        this.location = location;
        this.dining_style = dining_style;
        this.requirement_id = requirement_id;
        this.table_number = table_number;
        this.address_id = address_id;
        this.actual_payment = actual_payment;
        this.rider_id = rider_id;
        this.user_id = user_id;
        this.store_id = store_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Integer getTotal_discount_id() {
        return total_discount_id;
    }

    public void setTotal_discount_id(Integer total_discount_id) {
        this.total_discount_id = total_discount_id;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Timestamp getPurchased_date() {
        return purchased_date;
    }

    public void setPurchased_date(Timestamp purchased_date) {
        this.purchased_date = purchased_date;
    }

    public Integer getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Integer payment_method) {
        this.payment_method = payment_method;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getDining_style() {
        return dining_style;
    }

    public void setDining_style(Integer dining_style) {
        this.dining_style = dining_style;
    }

    public Integer getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(Integer requirement_id) {
        this.requirement_id = requirement_id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public double getActual_payment() {
        return actual_payment;
    }

    public void setActual_payment(double actual_payment) {
        this.actual_payment = actual_payment;
    }

    public Integer getRider_id() {
        return rider_id;
    }

    public void setRider_id(Integer rider_id) {
        this.rider_id = rider_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", total_price=" + total_price +
                ", total_discount_id=" + total_discount_id +
                ", order_date=" + order_date +
                ", purchased_date=" + purchased_date +
                ", payment_method=" + payment_method +
                ", order_state=" + order_state +
                ", location=" + location +
                ", dining_style=" + dining_style +
                ", requirement_id=" + requirement_id +
                ", table_number='" + table_number + '\'' +
                ", address_id=" + address_id +
                ", actual_payment=" + actual_payment +
                ", rider_id=" + rider_id +
                ", user_id=" + user_id +
                ", store_id=" + store_id +
                '}';
    }
}
