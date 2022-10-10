package QuizWebApp.domain.jdbc;

import QuizWebApp.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserJdbc extends User {

    public UserJdbc(String firstName, String lastName, String email, String password, String phone, String address, boolean isActive, boolean isAdmin) {
        super(firstName, lastName, email, password, phone, address, isActive, isAdmin);
    }
}
