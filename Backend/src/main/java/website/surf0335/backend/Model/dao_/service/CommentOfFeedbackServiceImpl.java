package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.CommentOfFeedbackDao;
import website.surf0335.backend.Model.dao_.domain.CommentOfFeedback;

import java.sql.Timestamp;
import java.util.List;
@Repository
public class CommentOfFeedbackServiceImpl implements CommentOfFeedbackService {
    private CommentOfFeedbackDao commentOfFeedbackDao = new CommentOfFeedbackDao();
    private static final Logger logger = LoggerFactory.getLogger(CommentOfFeedbackServiceImpl.class);


    public CommentOfFeedback querySingleByCommentId(int commentId) {
        logger.info("Querying for comment with ID: {}", commentId);
        return commentOfFeedbackDao.querySingle("SELECT * FROM `comment_of_feedback` WHERE `comment_id` = ?", CommentOfFeedback.class, commentId);
    }

    public List<CommentOfFeedback> queryMultipleByFeedbackId(int feedbackId) {
        logger.info("Querying for comments by feedback ID: {}", feedbackId);
        List<CommentOfFeedback> comments = commentOfFeedbackDao.queryMultiple("SELECT * FROM `comment_of_feedback` WHERE `feedback_id` = ?", CommentOfFeedback.class, feedbackId);
        logger.info("Number of comments found for feedback ID {}: {}", feedbackId, comments.size());
        return comments;
    }

    public boolean addComment(int commentId, int feedbackId, int commentUserId, Timestamp time, String content, int toUserId) {
        logger.info("Adding new comment with ID: {}, Feedback ID: {}, Comment User ID: {}, Time: {}, To User ID: {}",
                commentId, feedbackId, commentUserId, time, toUserId);
        int update = commentOfFeedbackDao.update("INSERT INTO `comment_of_feedback`(`comment_id`, `feedback_id`, `comment_user_id`, `time`, `content`, `to_user_id`) VALUES (?, ?, ?, ?, ?, ?)",
                commentId, feedbackId, commentUserId, time, content, toUserId);
        return update > 0;
    }

    public boolean deleteComment(int commentId) {
        logger.info("Attempting to delete comment with ID: {}", commentId);
        int update = commentOfFeedbackDao.update("DELETE FROM `comment_of_feedback` WHERE `comment_id` = ?", commentId);
        return update > 0;
    }

    public boolean updateComment(int commentId, int feedbackId, int commentUserId, Timestamp time, String content, int toUserId) {
        logger.info("Updating comment ID: {}, Feedback ID: {}, Comment User ID: {}, Time: {}, To User ID: {}",
                commentId, feedbackId, commentUserId, time, toUserId);
        int update = commentOfFeedbackDao.update("UPDATE `comment_of_feedback` SET `feedback_id` = ?, `comment_user_id` = ?, `time` = ?, `content` = ?, `to_user_id` = ? WHERE `comment_id` = ?",
                feedbackId, commentUserId, time, content, toUserId, commentId);
        return update > 0;
    }


}
