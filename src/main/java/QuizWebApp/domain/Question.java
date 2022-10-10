package QuizWebApp.domain;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int id;
    private String category;
    private String description;
    private boolean isActive;

    public Question(String category, String description, boolean isActive) {
        this.category = category;
        this.description = description;
        this.isActive = isActive;
    }
}
