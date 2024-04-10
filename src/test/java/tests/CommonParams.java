package tests;

import configuration.Generators;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import requests.user.CreateUserReq;
import responses.user.CreateUserResponse;

import static api.UserAPI.createUserAPI;
import static api.UserAPI.deleteUserAPI;

public class CommonParams {
    //Генератор тестовых данных
    protected static Generators dataGenerator = new Generators();

    //Генерирование данных для учетной записи покупателя
    protected static CreateUserReq createUserReq = dataGenerator.createUser();

    //Создание учетной записи покупателя
    protected Response createUserResponse = createUserAPI(createUserReq);

    //Получение токена для тестов
    protected String token = createUserResponse.as(CreateUserResponse.class).getAccessToken().substring(7);


    @After
    @DisplayName("Метод удаления тестовой учетной записи покупателя")
    @Description("Удаляет созданные для тестов данные")
    public void deleteTestUsers(){
        deleteUserAPI(token);
    }

}
