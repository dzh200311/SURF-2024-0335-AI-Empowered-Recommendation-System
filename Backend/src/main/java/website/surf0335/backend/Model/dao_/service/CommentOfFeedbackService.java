package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.CommentOfFeedback;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentOfFeedbackService {
    CommentOfFeedback querySingleByCommentId(int commentId);

    List<CommentOfFeedback> queryMultipleByFeedbackId(int feedbackId);

    boolean addComment(int commentId, int feedbackId, int commentUserId, Timestamp time, String content, int toUserId);

    boolean deleteComment(int commentId);

    boolean updateComment(int commentId, int feedbackId, int commentUserId, Timestamp time, String content, int toUserId);
}
