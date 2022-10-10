package QuizWebApp.dao;

import QuizWebApp.domain.QuizResult;

import java.util.List;

public interface QuizResultDAO {
    void createNewQuizResult(QuizResult quizResult);
    List<QuizResult> getQuizResultByCategoryAndUserId(String category, int userId);
    List<QuizResult> getQuizResultByCategory(String category);
    Integer getLatestId();
    QuizResult getQuizResultByQuizId(int id);
    List<QuizResult> getQuizResultByUserId(int id);
    List<QuizResult> getAllQuizResult();
}
