package QuizWebApp.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int id;
    private int question_id;
    private String description;
    private boolean is_answer;

    public Choice(int question_id, String description, boolean is_answer) {
        this.question_id = question_id;
        this.description = description;
        this.is_answer = is_answer;
    }
}
