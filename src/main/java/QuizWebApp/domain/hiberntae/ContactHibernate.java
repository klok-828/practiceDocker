package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Contact;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactHibernate extends Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String subject;

    @Column
    private String content;

    @Column
    private int user_id;
}
