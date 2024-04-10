package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.order.CreateOrderReq;
import responses.ingredients.Data;
import responses.order.CreateOrderBadResponse;

import java.util.List;

import static api.OrderAPI.createOrderAPI;
import static configuration.ErrorMessages.CREATE_ORDER_BAD_INGREDIENT;
import static configuration.ErrorMessages.CREATE_ORDER_NO_INGREDIENTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class OrderParametrizedTest extends CommonParams{

    private List<Data> testList;
    private String message;

    @Parameterized.Parameters()
    public static Object[][] switchBetweenIncorrectParams() {
        return new Object[][]{
                {List.of(new Data("61c0c5a71d1f82001bd22263")), CREATE_ORDER_BAD_INGREDIENT},
                {List.of(), CREATE_ORDER_NO_INGREDIENTS}
        };
    }

    @Test
    @DisplayName("Негативный тест создания заказа")
    @Description("Тест передает в метод некорректные данные, свряет овтвет и статускод")
    public void checkOrderCreationErrorTest(){
        CreateOrderReq createOrderReq = new CreateOrderReq(testList);

        Response response = createOrderAPI(createOrderReq, token);
        response.then()
                .assertThat()
                .statusCode(400);

        assertFalse(response.as(CreateOrderBadResponse.class).isSuccess());
        assertEquals("Получена иная ошибка",message, response.as(CreateOrderBadResponse.class).getMessage());
    }
}
