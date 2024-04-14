package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.user.CreateUserReq;
import responses.user.CreateUserBadResponse;
import responses.user.CreateUserResponse;

import static api.UserAPI.createUserAPI;
import static configuration.ErrorMessages.USER_ALREADY_EXISTS;
import static org.junit.Assert.*;

public class UserCreateTest extends CommonParams {


    @Test
    @DisplayName("Тест создания пользователя")
    @Description("Тест создает пользователя и проверяет корректность ответа и статускода 200")
    public void checkAPICanCreateUserTest(){
        createUserResponse.then()
                .assertThat()
                .statusCode(200);
        assertTrue(createUserResponse.as(CreateUserResponse.class).isSuccess());
        assertEquals(createUserReq.getEmail(), createUserResponse.as(CreateUserResponse.class).getUser().getEmail());
    }

    @Test
    @DisplayName("Тест повторного создания пользователя")
    @Description("Тест создает уже существующего пользователя и проверяет корректность ответа и статускода 403")
    public void checkAPICanCreateGeminiUserTest(){
        CreateUserReq geminiReq = createUserReq;

        createUserAPI(geminiReq);

        Response response = createUserAPI(geminiReq);
        response.then()
                .assertThat()
                .statusCode(403);
        assertEquals(USER_ALREADY_EXISTS,response.as(CreateUserBadResponse.class).getMessage());
        assertFalse(response.as(CreateUserBadResponse.class).isSuccess());
    }

}
