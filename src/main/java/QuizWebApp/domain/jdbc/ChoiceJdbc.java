package QuizWebApp.domain.jdbc;

import QuizWebApp.domain.Choice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChoiceJdbc extends Choice {

    public ChoiceJdbc(int question_id, String description, boolean is_answer) {
        super(question_id, description, is_answer);
    }
}
