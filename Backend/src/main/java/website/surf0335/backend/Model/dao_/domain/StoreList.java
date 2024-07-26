package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;

public class StoreList {
    private Integer store_id;
    private String restaurant_name;
    private String position;
    private String manager;
    private String phone;
    private String status;
    private Timestamp startTime;
    private Timestamp closeTime;

    public StoreList() {
    }

    public StoreList(Integer store_id, String restaurant_name, String position, String manager, String phone, String status, Timestamp startTime, Timestamp closeTime) {
        this.store_id = store_id;
        this.restaurant_name = restaurant_name;
        this.position = position;
        this.manager = manager;
        this.phone = phone;
        this.status = status;
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "StoreList{" +
                "store_id=" + store_id +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", position='" + position + '\'' +
                ", manager='" + manager + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
