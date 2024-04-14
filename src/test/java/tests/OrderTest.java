package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.order.CreateOrderReq;
import responses.order.CreateOrderResponse;

import static api.OrderAPI.createOrderAPI;
import static api.OrderAPI.createOrderWithoutAuthAPI;
import static org.junit.Assert.*;

public class OrderTest extends CommonParams{


    @Test
    @DisplayName("Метод проверки корретного создания заказа с авторизацией")
    @Description("Создает заказ c авторизацией через токен и проверяет корректность ответа")
    public void orderCreationTest(){
        CreateOrderReq createOrderReq = dataGenerator.createIngredientsForOrder();

        Response response = createOrderAPI(createOrderReq, token);

        response.then()
                .assertThat()
                .statusCode(200);

        assertTrue(response.as(CreateOrderResponse.class).isSuccess());
        assertNotNull(response.as(CreateOrderResponse.class).getOrder().getIngredients());
    }

    @Test
    @DisplayName("Метод проверки корретного создания заказа без авторизаци")
    @Description("Создает заказ и проверяет корректность ответа")
    public void orderCreationWithoutAuthTest(){
        CreateOrderReq createOrderReq = dataGenerator.createIngredientsForOrder();

        Response response = createOrderWithoutAuthAPI(createOrderReq);

        response.then()
                .assertThat()
                .statusCode(200);

        assertTrue(response.as(CreateOrderResponse.class).isSuccess());
        assertNotEquals("Получено некорректное значение в номере заказа",0 ,response.as(CreateOrderResponse.class).getOrder().getNumber());
    }




}
