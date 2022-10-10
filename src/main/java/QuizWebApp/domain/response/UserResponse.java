package QuizWebApp.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
}
