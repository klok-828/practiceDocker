package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Permission;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="permission")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionHibernate extends Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String name;
}
