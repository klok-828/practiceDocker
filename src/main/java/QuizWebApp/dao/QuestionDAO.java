package QuizWebApp.dao;

import QuizWebApp.domain.Question;

import java.util.List;

public interface QuestionDAO {
    void updateQuestionStatusById(int id);
    Question getQuestionById(int id);
    void createNewQuestion(Question question);
    Integer getLatestId();
    List<Question> getRandomQuestionsByCategory(String category);

    List<String> getAllCategory();

    List<Question> getAllQuestions();
}
