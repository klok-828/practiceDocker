package QuizWebApp.service;

import QuizWebApp.dao.ChoiceDAO;
import QuizWebApp.dao.FeedbackDAO;
import QuizWebApp.dao.QuestionDAO;
import QuizWebApp.domain.Choice;
import QuizWebApp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionService {
    private QuestionDAO questionJdbcDAO;
    private QuestionDAO questionHibernateDAO;
    private ChoiceDAO choiceHibernateDAO;
    private ChoiceDAO choiceJdbcDAO;

    @Autowired
    @Qualifier("questionDAOHibernateImpl")
    public void setQuestionHibernateDAO(QuestionDAO questionHibernateDAO) {
        this.questionHibernateDAO = questionHibernateDAO;
    }

    @Autowired
    @Qualifier("questionDAOJdbcImpl")
    public void setQuestionJdbcDAO(QuestionDAO questionJdbcDAO) {
        this.questionJdbcDAO = questionJdbcDAO;
    }

    @Autowired
    @Qualifier("choiceDAOHibernateImpl")
    public void setChoiceHibernateDAO(ChoiceDAO choiceHibernateDAO) {
        this.choiceHibernateDAO = choiceHibernateDAO;
    }

    @Autowired
    @Qualifier("choiceDAOJdbcImpl")
    public void setChoiceJdbcDAO(ChoiceDAO choiceJdbcDAO) {
        this.choiceJdbcDAO = choiceJdbcDAO;
    }


    @Transactional
    public boolean checkAns(String userChoice, int qid) {
        String ans = getAnswerByQuestionId(qid);
        if (userChoice == null) {
            return false;
        } else if (userChoice.equals(ans)) {
            return true;
        }
        return false;
    }
    @Transactional
    public String getAnswerByQuestionId(int id) {
        return choiceHibernateDAO.getAnswerByQuestionId(id);
    }
    @Transactional
    public Question getQuestionById(int id) { return questionHibernateDAO.getQuestionById(id); }
    @Transactional
    public List<Question> getAllQuestions() { return questionJdbcDAO.getAllQuestions(); }

    @Transactional
    public void reverseQuestionStatus(int id) { questionHibernateDAO.updateQuestionStatusById(id); }
    @Transactional
    public void createQuestion(Question question) { questionJdbcDAO.createNewQuestion(question); }

    @Transactional
    public void createChoices(List<Choice> choices) {
        choiceJdbcDAO.createNewChoices(choices);
    }

    @Transactional
    public int getLatestId() { return questionJdbcDAO.getLatestId(); }
    @Transactional
    public List<String> getAllCategory() { return questionHibernateDAO.getAllCategory(); }

    @Transactional
    public List<Question> getRandomQuestionsByCategory(String category) { return questionJdbcDAO.getRandomQuestionsByCategory(category); }

    @Transactional
    public List<Choice> getChoicesByQuestionId(int id) { return choiceHibernateDAO.getChoicesByQuestionId(id); }

}
