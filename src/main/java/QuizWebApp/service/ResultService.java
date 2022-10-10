package QuizWebApp.service;

import QuizWebApp.dao.QuestResultDAO;
import QuizWebApp.dao.QuestionDAO;
import QuizWebApp.dao.QuizResultDAO;
import QuizWebApp.domain.QuestResult;
import QuizWebApp.domain.QuizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private QuestResultDAO questResultHibernateDAO;
    private QuestResultDAO questResultJdbcDAO;
    private QuizResultDAO quizResultHibernateDAO;
    private QuizResultDAO quizResultJdbcDAO;

    @Autowired
    @Qualifier("questResultDAOHibernateImpl")
    public void setQuestResultHibernateDAO(QuestResultDAO questResultHibernateDAO) {
        this.questResultHibernateDAO = questResultHibernateDAO;
    }

    @Autowired
    @Qualifier("questResultDAOJdbcImpl")
    public void setQuestResultJdbcDAO(QuestResultDAO questResultJdbcDAO) {
        this.questResultJdbcDAO = questResultJdbcDAO;
    }

    @Autowired
    @Qualifier("quizResultDAOHibernateImpl")
    public void setQuizResultHibernateDAO(QuizResultDAO QuizResultHibernateDAO) {
        this.quizResultHibernateDAO = quizResultHibernateDAO;
    }

    @Autowired
    @Qualifier("quizResultDAOJdbcImpl")
    public void setQuizResultJdbcDAO(QuizResultDAO quizResultJdbcDAO) {
        this.quizResultJdbcDAO = quizResultJdbcDAO;
    }


    public List<QuestResult> getQuestResultByQuizId(int id) {
        return questResultJdbcDAO.getQuestResultByQuizId(id);
    }
    public void createNewQuestResults(List<QuestResult> questResultList) {
        questResultJdbcDAO.createQuestResults(questResultList);
    }
    public int getLatestId() {
        return quizResultJdbcDAO.getLatestId();
    }
    public void createNewQuizResult(QuizResult quizResult) {
        quizResultJdbcDAO.createNewQuizResult(quizResult);
    }
    public List<QuizResult> getQuizResultByUserId(int id) {
        return quizResultJdbcDAO.getQuizResultByUserId(id);
    }

    public QuizResult getQuizResultById(int id) {
        return quizResultJdbcDAO.getQuizResultByQuizId(id);
    }

    public List<QuizResult> getAllQuizResult() { return quizResultJdbcDAO.getAllQuizResult(); }

    public List<QuizResult> getQuizResultByCategoryAndId(String category, int userId) {
        return quizResultJdbcDAO.getQuizResultByCategoryAndUserId(category, userId);
    }
    public List<QuizResult> getAllQuizResultByCategory(String category) { return quizResultJdbcDAO.getQuizResultByCategory(category); }
}
