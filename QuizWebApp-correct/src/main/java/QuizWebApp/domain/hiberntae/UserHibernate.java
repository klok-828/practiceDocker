package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHibernate extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_admin")
    private boolean isAdmin;


//    public UserHibernate(String firstName, String lastName, String email, String password, String phone, String address, boolean isActive, boolean isAdmin) {
//        super(firstName, lastName, email, password, phone, address, isActive, isAdmin);
//    }
}
