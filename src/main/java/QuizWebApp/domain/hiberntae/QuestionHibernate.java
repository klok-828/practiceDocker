package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Question;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="question")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionHibernate extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private boolean is_active;
}
