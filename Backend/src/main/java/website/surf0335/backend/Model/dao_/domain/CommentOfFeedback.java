package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;

public class CommentOfFeedback {
    private Integer comment_id;
    private Integer feedback_id;
    private Integer comment_user_id;
    private Timestamp time;
    private String content;
    private Integer to_user_id;

    public CommentOfFeedback() {
    }

    public CommentOfFeedback(int comment_id, int feedback_id, int comment_user_id, Timestamp time, String content, int to_user_id) {
        this.comment_id = comment_id;
        this.feedback_id = feedback_id;
        this.comment_user_id = comment_user_id;
        this.time = time;
        this.content = content;
        this.to_user_id = to_user_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(int comment_user_id) {
        this.comment_user_id = comment_user_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    @Override
    public String toString() {
        return "CommentOfFeedback{" +
                "comment_id=" + comment_id +
                ", feedback_id=" + feedback_id +
                ", comment_user_id=" + comment_user_id +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", to_user_id=" + to_user_id +
                '}';
    }
}
