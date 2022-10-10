package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Choice;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="choice")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceHibernate extends Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private int id;

    @Column
    private int question_id;

    @Column
    private String description;

    @Column
    private boolean is_answer;
}
