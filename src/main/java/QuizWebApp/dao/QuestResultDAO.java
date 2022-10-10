package QuizWebApp.dao;

import QuizWebApp.domain.QuestResult;

import java.util.List;

public interface QuestResultDAO {
    List<QuestResult> getQuestResultByQuizId(int id);
    void createQuestResults(List<QuestResult> questResultList);
}
