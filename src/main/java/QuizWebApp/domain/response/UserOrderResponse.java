package QuizWebApp.domain.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserOrderResponse {
    OrderResponse orderResponse;
    UserResponse userResponse;
}
