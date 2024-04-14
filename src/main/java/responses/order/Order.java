package responses.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private List<Ingredients> ingredients;
    private int number;
}
