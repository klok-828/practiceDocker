package QuizWebApp.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {
    private int orderId;
    private Timestamp time;
    private double totalPrice;
    private List<OrderItemResponse> orderItemResponseList;
}
