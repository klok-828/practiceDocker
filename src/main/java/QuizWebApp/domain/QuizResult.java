package QuizWebApp.domain;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
    private int id;
    private String category;
    private int userId;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean isPass;

    public QuizResult(String category, int userId, Timestamp startTime, Timestamp endTime, boolean isPass) {
        this.category = category;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPass = isPass;
    }
}

