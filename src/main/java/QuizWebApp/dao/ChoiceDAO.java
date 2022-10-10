package QuizWebApp.dao;

import QuizWebApp.domain.Choice;

import java.util.List;

public interface ChoiceDAO {
    String getAnswerByQuestionId(int id);
    void createNewChoices(List<Choice> choiceList);
    List<Choice> getChoicesByQuestionId(int id);
}
