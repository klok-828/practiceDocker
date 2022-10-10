package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Feedback;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="feedback")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackHibernate extends Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int id;

    @Column
    private String content;

    @Column
    private int rating;

    @Column
    private Timestamp fb_date;
}
