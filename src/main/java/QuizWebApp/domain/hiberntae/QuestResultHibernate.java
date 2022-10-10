package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.QuestResult;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="questresult")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestResultHibernate extends QuestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questresult_id")
    private int id;

    @Column
    private int question_id;

    @Column
    private String user_choice;

    @Column
    private int quizresult_id;
}
