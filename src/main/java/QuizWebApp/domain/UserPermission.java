package QuizWebApp.domain;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPermission {
    private int id;
    private int userId;
    private int permissionId;
}
