package responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderBadResponse {
    private boolean success;
    private String message;
}
