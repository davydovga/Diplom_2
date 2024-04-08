package tests;


import configuration.Generators;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.user.CreateUserReq;
import responses.user.CreateUserBadResponse;

import static api.UserAPI.createUserAPI;
import static configuration.ErrorMessages.USER_CREATION_BAD_REQ;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class UserCreateParametrizedTest extends CommonParams {

    private final String email;
    private final String password;
    private final String name;

    @Parameterized.Parameters()
    public static Object[][] switchBetweenNullParams() {
        return new Object[][]{
                //Тело запроса с пустым логином
                {null, createUserReq.getPassword(), createUserReq.getName()},
                //Тело запроса с пустым паролем
                {createUserReq.getEmail(),  null, createUserReq.getName()},
                //Тело запроса с пустым именем
                {createUserReq.getEmail(), createUserReq.getPassword(), null}
        };
    }


    @Test
    @DisplayName("Метод проверки некорретного создания пользователя")
    @Description("На вход передается три набора параметров с пустым логином,паролем и именем")
    public void checkUserCreationErrorTest(){
        CreateUserReq userParametrizedReq = new CreateUserReq(email, password, name);
        Response response = createUserAPI(userParametrizedReq);

        response.then()
                .assertThat()
                .statusCode(403);

        assertEquals(USER_CREATION_BAD_REQ, response.as(CreateUserBadResponse.class).getMessage());
        assertFalse(response.as(CreateUserBadResponse.class).isSuccess());
    }

}
