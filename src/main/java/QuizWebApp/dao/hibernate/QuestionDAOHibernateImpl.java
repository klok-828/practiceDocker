package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.QuestionDAO;
import QuizWebApp.domain.Question;
import QuizWebApp.domain.hiberntae.QuestionHibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("questionDAOHibernateImpl")
public class QuestionDAOHibernateImpl extends AbstractHibernateDAO<QuestionHibernate> implements QuestionDAO {

    public QuestionDAOHibernateImpl() {
        setClazz(QuestionHibernate.class);
    }
    @Override
    public void updateQuestionStatusById(int id) {
        Question question = findById(id);
        boolean status = !question.isActive();

        Session session = getCurrentSession();
        Query query = session.createQuery("update QuestionHibernate q set q.is_active = :status WHERE q.id = :id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        int result = query.executeUpdate();


    }

    @Override
    public Question getQuestionById(int id) {
        return findById(id);
    }

    @Override
    public void createNewQuestion(Question question) {
        add((QuestionHibernate) question);
    }

    @Override
    public Integer getLatestId() {
        Session session = getCurrentSession();
        Query query = session.createQuery("select q.id from Question q where q.id = (select max(qq.id) from Question qq)");
        int id = (int) query.uniqueResult();
        return id;
    }

    @Override
    public List<Question> getRandomQuestionsByCategory(String category) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Question where category = :category");
        List<Question> questionList= query.list();
        return questionList;
    }

    @Override
    public List<String> getAllCategory() {
        List<QuestionHibernate> list = this.getAll();
        Set<String> set = new HashSet<>();
        list = list.stream().filter(q -> set.add(q.getCategory())).collect(Collectors.toList());

        List<String> categorys = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            categorys.add(list.get(i).getCategory());
        }
        return categorys;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<QuestionHibernate> lst = this.getAll();
        return lst.stream().map(q -> (Question) q).collect(Collectors.toList());
    }
}
