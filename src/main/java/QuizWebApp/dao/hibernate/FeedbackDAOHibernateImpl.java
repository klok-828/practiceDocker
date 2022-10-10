package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.FeedbackDAO;
import QuizWebApp.domain.Contact;
import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.hiberntae.ContactHibernate;
import QuizWebApp.domain.hiberntae.FeedbackHibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository("feedbackDAOHibernateImpl")
public class FeedbackDAOHibernateImpl extends AbstractHibernateDAO<FeedbackHibernate> implements FeedbackDAO {

    public FeedbackDAOHibernateImpl() {
        setClazz(FeedbackHibernate.class);
    }

    @Override
    public Feedback createNewFeedback(Feedback feedback) {
        Integer feedbackId = add((FeedbackHibernate) feedback);
        return getFeedbackById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        List<FeedbackHibernate> list = this.getAll();
        return list.stream().map(feedback -> (Feedback) feedback).collect(Collectors.toList());
    }

    public boolean isFeedbackIdValid(int id) {
        Query query = getCurrentSession().createQuery("from Feedback where id = :id");
        query.setParameter("id", id);
        return query.getResultList().size() == 0;
    }

    public Feedback getFeedbackById(Integer id) {
        return findById(id);
    }
}
