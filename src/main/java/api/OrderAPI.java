package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.order.CreateOrderReq;

import static api.BaseAPI.*;
import static configuration.Endpoints.ORDER_ENDPOINT;

public class OrderAPI {
    @Step("Вызов API создания заказа")
    public static Response createOrderAPI(CreateOrderReq body, String token){
        return postReqForCreateOrder(body, ORDER_ENDPOINT, token);
    }

    @Step("Вызов API создания заказа без авторизации")
    public static Response createOrderWithoutAuthAPI(CreateOrderReq body){
        return postReqForCreateOrderWithoutAuth(body, ORDER_ENDPOINT);
    }

    @Step("Вызов API получения списка доступных ингридиентов")
    public static Response getIngredientsForTestAPI(){
        return getIngredientsReq();
    }

    @Step("Вызов API получения списка доступных заказов пользователя")
    public static Response getOrdersAPI(String token){
        return getOrdersReq(ORDER_ENDPOINT, token);
    }

    @Step("Вызов API получения списка доступных заказов без авторизации")
    public static Response getOrdersWithoutAuthAPI(){
        return getOrdersWithoutAuthReq(ORDER_ENDPOINT);
    }
}
