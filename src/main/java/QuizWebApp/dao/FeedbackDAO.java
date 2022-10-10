package QuizWebApp.dao;

import QuizWebApp.domain.Feedback;

import java.util.List;

public interface FeedbackDAO {
    Feedback createNewFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();

    boolean isFeedbackIdValid(int id);

}
