package QuizWebApp.domain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String subject;
    private String content;
    private int user_id;

    public Contact (String firstName, String lastName, String subject, String content, int user_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.content = content;
        this.user_id = user_id;
    }

}
