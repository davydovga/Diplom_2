package responses.ingredients;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetIngredientsResponse {
    private boolean success;
    private List<responses.ingredients.Data> data;

}
