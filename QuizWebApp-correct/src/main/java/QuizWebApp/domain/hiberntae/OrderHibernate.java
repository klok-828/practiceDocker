package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="`order`")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHibernate extends Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private Timestamp orderTime;

    @Column
    private double totalPrice;

    @Column
    private int userId;

}
