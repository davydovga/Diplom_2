package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.order.CreateOrderReq;
import responses.order.CreateOrderResponse;
import responses.order.GetOrdersBadResponse;
import responses.order.GetOrdersResponse;

import static api.OrderAPI.*;
import static configuration.ErrorMessages.GET_ORDER_NO_AUTH;
import static org.junit.Assert.*;

public class GetOrdersTest extends CommonParams{


    @Test
    @DisplayName("Тест получения заказов с авторизацией")
    @Description("Метод создает заказ под токеном пользователя, затем, под этим же токеном, получает созданный заказ и сверяет номер")
    public void getOrderTest(){
        CreateOrderReq createOrderReq = dataGenerator.createIngredientsForOrder();

        Response responseForNumber = createOrderAPI(createOrderReq, token);
        int orderNumber = responseForNumber.as(CreateOrderResponse.class).getOrder().getNumber();
        Response response = getOrdersAPI(token);

        response.then()
                .assertThat()
                .statusCode(200);

        assertTrue(response.as(GetOrdersResponse.class).isSuccess());
        assertEquals("Номер созданного заказа не совпадает, либо был получен лишний заказ",
                orderNumber,
                response.as(GetOrdersResponse.class).getOrders().get(0).getNumber());
    }

    @Test
    @DisplayName("Тест получения заказов без авторизации")
    @Description("Метод проверяет что пользователь не может получить список заказов без авторизации")
    public void getOrderWithoutAuthTest(){
        Response response = getOrdersWithoutAuthAPI();
        response.then()
                .assertThat()
                .statusCode(401);

        assertFalse(response.as(GetOrdersBadResponse.class).isSuccess());
        assertEquals("Метод отработал без необходимой авторизации", GET_ORDER_NO_AUTH,response.as(GetOrdersBadResponse.class).getMessage());
    }


}
