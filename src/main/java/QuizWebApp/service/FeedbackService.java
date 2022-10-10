package QuizWebApp.service;

import QuizWebApp.dao.ContactDAO;
import QuizWebApp.dao.FeedbackDAO;
import QuizWebApp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("feedbackService")
public class FeedbackService {
    private FeedbackDAO feedbackJdbcDAO;
    private FeedbackDAO feedbackHibernateDAO;

    @Autowired
    @Qualifier("feedbackDAOJdbcImpl")
    public void setContactJdbcDAO(FeedbackDAO contactJdbcDAO) {
        this.feedbackJdbcDAO = feedbackJdbcDAO;
    }

    @Autowired
    @Qualifier("feedbackDAOHibernateImpl")
    public void setContactHibernateDAO(FeedbackDAO feedbackHibernateDAO) {
        this.feedbackHibernateDAO = feedbackHibernateDAO;
    }

    @Transactional
    public List<Feedback> getAllFeedbacks() { return feedbackHibernateDAO.getAllFeedback(); }

    @Transactional
    public Feedback createNewFeedback(Feedback feedback) throws Exception {

        if (!feedbackHibernateDAO.isFeedbackIdValid(feedback.getId()))
            throw new Exception();

        return feedbackHibernateDAO.createNewFeedback(feedback);
    }
}
