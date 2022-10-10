package QuizWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private int id;
    private Timestamp orderTime;
    private double totalPrice;
    private int userId;
}
