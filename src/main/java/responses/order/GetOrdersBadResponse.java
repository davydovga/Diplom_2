package responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOrdersBadResponse {
    private boolean success;
    private String message;
}
