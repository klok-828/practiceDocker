package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.QuizResultDAO;
import QuizWebApp.domain.QuizResult;
import QuizWebApp.domain.hiberntae.QuizResultHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("quizResultDAOHibernateImpl")
public class QuizResultDAOHibernateImpl extends AbstractHibernateDAO<QuizResultHibernate> implements QuizResultDAO {
    @Override
    public void createNewQuizResult(QuizResult quizResult) {
        add((QuizResultHibernate) quizResult);
    }

    @Override
    public List<QuizResult> getQuizResultByCategoryAndUserId(String category, int userId) {
        return null;
    }

    @Override
    public List<QuizResult> getQuizResultByCategory(String category) {
        List<QuizResultHibernate> list = this.getAll();
        list = list.stream().filter(qz -> qz.getCategory() == category).collect(Collectors.toList());

        List<QuizResult> quizResults = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            quizResults.add(list.get(i));
        }

        return quizResults;
    }

    @Override
    public Integer getLatestId() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select qz.id from QuizResult qz where qz.id = (select max(qqzz.id) from QuizResult qqzz)");
        int id = (int) query.uniqueResult();
        return id;
    }

    @Override
    public QuizResult getQuizResultByQuizId(int id) {
        return findById(id);
    }

    @Override
    public List<QuizResult> getQuizResultByUserId(int id) {
        List<QuizResultHibernate> list = this.getAll();
        list = list.stream().filter(qz -> qz.getUser_id() == id).collect(Collectors.toList());

        List<QuizResult> quizResults = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            quizResults.add(list.get(i));
        }

        return quizResults;
    }

    @Override
    public List<QuizResult> getAllQuizResult() {
        List<QuizResultHibernate> list = this.getAll();
        return list.stream().map(qz -> (QuizResult) qz).collect(Collectors.toList());
    }
}
