package configuration;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import requests.order.CreateOrderReq;
import requests.user.CreateUserReq;
import responses.ingredients.Data;
import responses.ingredients.GetIngredientsResponse;

import java.util.List;
import java.util.Random;

import static api.OrderAPI.getIngredientsForTestAPI;

public class Generators {

    Faker faker = new Faker();

    //Геренатор пользователя
    public CreateUserReq createUser(){
        return new CreateUserReq(
            faker.internet().emailAddress(),
            faker.internet().password(),
            faker.name().username());
    }


    //Генератор ингридиентов, получает список доступных ингридиентов и оставляет только 3
    public CreateOrderReq createIngredientsForOrder(){
        Response response = getIngredientsForTestAPI();

        List<Data> hashIngredientList = response.as(GetIngredientsResponse.class).getData();

        while (hashIngredientList.size() > 3){
        hashIngredientList.remove(new Random().nextInt(hashIngredientList.size()));
        }
        return new CreateOrderReq(hashIngredientList);
    }


}
