package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.QuizResult;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="quizresult")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultHibernate extends QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizresult_id")
    private int id;

    @Column
    private String category;

    @Column
    private int user_id;

    @Column
    private Timestamp start_time;

    @Column
    private Timestamp end_time;

    @Column
    private boolean is_pass;
}
