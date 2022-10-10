package QuizWebApp.domain;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean isActive;
    private boolean isAdmin;


    public User(String firstName, String lastName, String email, String password, String phone, String address, boolean isActive, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
        this.isAdmin = isAdmin;

    }
}
