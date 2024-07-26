package website.surf0335.backend.Model.dao_.domain;

public class FeedbackOfDelivery {
    private Integer delivery_feedback_id;
    private Integer stars;
    private String feedback;
    private Integer order_id;
    private Integer rider_id;
    private Integer user_id;

    public FeedbackOfDelivery() {
    }

    public FeedbackOfDelivery(Integer delivery_feedback_id, Integer stars, String feedback, Integer order_id, Integer rider_id, Integer user_id) {
        this.delivery_feedback_id = delivery_feedback_id;
        this.stars = stars;
        this.feedback = feedback;
        this.order_id = order_id;
        this.rider_id = rider_id;
        this.user_id = user_id;
    }

    public Integer getDelivery_feedback_id() {
        return delivery_feedback_id;
    }

    public void setDelivery_feedback_id(Integer delivery_feedback_id) {
        this.delivery_feedback_id = delivery_feedback_id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    @Override
    public String toString() {
        return "FeedbackOfDelivery{" +
                "delivery_feedback_id=" + delivery_feedback_id +
                ", stars=" + stars +
                ", feedback='" + feedback + '\'' +
                ", order_id=" + order_id +
                ", rider_id=" + rider_id +
                ", user_id=" + user_id +
                '}';
    }
}
