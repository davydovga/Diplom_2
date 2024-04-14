package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import requests.user.AuthReq;
import responses.user.CreateUserBadResponse;
import responses.user.CreateUserResponse;

import static api.LoginAPI.loginUserAPI;
import static configuration.ErrorMessages.LOGIN_BAD_REQ;
import static org.junit.Assert.*;

public class LoginTest extends CommonParams {

    @Test
    @DisplayName("Тест успешного логина пользователя")
    @Description("Тест проверяет что пользователь может залогиниться под своими данными")
    public void userCanLoginTest(){
        AuthReq authReq = new AuthReq(createUserReq.getEmail(), createUserReq.getPassword());
        Response response = loginUserAPI(authReq);

        response.then()
                .assertThat()
                .statusCode(200);
        assertTrue(response.as(CreateUserResponse.class).isSuccess());
        assertEquals(createUserReq.getEmail(),response.as(CreateUserResponse.class).getUser().getEmail());


    }

    @Test
    @DisplayName("Тест логина пользователя под некорректными данными")
    @Description("Тест проверяет что пользователь не может залогиниться под некорректными данными")
    public void userCantLoginTest(){
        AuthReq authInvalidReq = new AuthReq(RandomStringUtils.randomAlphabetic(2) + createUserReq.getEmail(),
                RandomStringUtils.randomAlphabetic(2)+ createUserReq.getPassword());

        Response response = loginUserAPI(authInvalidReq);

        response.then()
                .assertThat()
                .statusCode(401);
        assertEquals(LOGIN_BAD_REQ,response.as(CreateUserBadResponse.class).getMessage());
        assertFalse(response.as(CreateUserBadResponse.class).isSuccess());
    }

}
