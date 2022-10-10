package QuizWebApp.domain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestResult {
    private int id;
    private int quizResultId;
    private int questionId;
    private String userChoice;

    public QuestResult(int quizResultId, int questionId, String userChoice) {
        this.quizResultId = quizResultId;
        this.questionId = questionId;
        this.userChoice = userChoice;
    }
}
