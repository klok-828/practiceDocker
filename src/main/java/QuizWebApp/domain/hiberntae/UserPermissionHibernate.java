package QuizWebApp.domain.hiberntae;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="userpermission")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int userId;

    @Column
    private int permissionId;
}
