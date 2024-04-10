package responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetOrdersResponse {
    private boolean success;
    private List<Orders> orders;
}
