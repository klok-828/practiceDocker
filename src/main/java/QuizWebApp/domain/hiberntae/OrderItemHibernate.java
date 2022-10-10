package QuizWebApp.domain.hiberntae;

import QuizWebApp.domain.OrderItem;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="orderitem")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemHibernate extends OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int quantity;

    @Column
    private int orderId;

    @Column
    private int itemId;
}
