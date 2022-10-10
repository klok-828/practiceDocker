package QuizWebApp.domain.jdbc;

import QuizWebApp.domain.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactJdbc extends Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String subject;
    private String content;
    private int user_id;

    public ContactJdbc (String firstName, String lastName, String subject, String content, int user_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.content = content;
        this.user_id = user_id;
    }
}
