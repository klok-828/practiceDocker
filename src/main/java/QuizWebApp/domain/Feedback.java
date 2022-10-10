package QuizWebApp.domain;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    private int id;
    private String content;
    private int rating;
    private Timestamp fb_date;

    public Feedback(int rating, String content, Timestamp fb_date) {
        this.rating = rating;
        this.content = content;
        this.fb_date = fb_date;
    }
}
