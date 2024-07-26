package website.surf0335.backend.Model.dao_.domain;

public class FeedbackOfOrder {
    private Integer order_feedback_id;
    private Integer stars;
    private Integer productID;
    private Integer store_id;

    private String feedback;

    private String images;

    public FeedbackOfOrder() {
    }

    public FeedbackOfOrder(Integer order_feedback_id, Integer stars, Integer productID, Integer store_id, String feedback, String images) {
        this.order_feedback_id = order_feedback_id;
        this.stars = stars;
        this.productID = productID;
        this.store_id = store_id;
        this.feedback = feedback;
        this.images = images;
    }

    @Override
    public String toString() {
        return "FeedbackOfOrder{" +
                "order_feedback_id=" + order_feedback_id +
                ", stars=" + stars +
                ", productID=" + productID +
                ", store_id=" + store_id +
                ", feedback='" + feedback + '\'' +
                ", images='" + images + '\'' +
                '}';
    }

    public Integer getOrder_feedback_id() {
        return order_feedback_id;
    }

    public void setOrder_feedback_id(Integer order_feedback_id) {
        this.order_feedback_id = order_feedback_id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
