package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.QuestResultDAO;
import QuizWebApp.dao.QuestionDAO;
import QuizWebApp.domain.QuestResult;
import QuizWebApp.domain.hiberntae.QuestResultHibernate;
import QuizWebApp.domain.hiberntae.QuestionHibernate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("questResultDAOHibernateImpl")
public class QuestResultDAOHibernate extends AbstractHibernateDAO<QuestResultHibernate> implements QuestResultDAO {
    @Override
    public List<QuestResult> getQuestResultByQuizId(int id) {
        List<QuestResultHibernate> list = this.getAll();
        list = list.stream().filter(qr -> qr.getQuizResultId() == id).collect(Collectors.toList());

        List<QuestResult> questResults = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            questResults.add(list.get(i));
        }

        return questResults;
    }

    @Override
    public void createQuestResults(List<QuestResult> questResultList) {
        for (int i = 0; i < questResultList.size(); i++) {
            add((QuestResultHibernate) questResultList.get(i));
        }
    }
}
