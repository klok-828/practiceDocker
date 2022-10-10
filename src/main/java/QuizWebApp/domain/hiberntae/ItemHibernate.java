package QuizWebApp.domain.hiberntae;


import QuizWebApp.domain.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemHibernate extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;
}
