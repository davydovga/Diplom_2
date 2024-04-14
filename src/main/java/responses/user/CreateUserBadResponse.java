package responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserBadResponse {
    private boolean success;
    private String message;
}
