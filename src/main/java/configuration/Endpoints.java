package configuration;

public class Endpoints {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/api";
    //Endpoint регистрации пользователя
    public static final String CREATE_USER_ENDPOINT = "/auth/register";
    //Endpoint удаления или получения данных о пользователе
    public static final String USER_ENDPOINT = "/auth/user ";
    //Endpoint авторизации пользователя
    public static final String USER_LOGIN_ENDPOINT = "/auth/login";
    //Endpoint заказов
    public static final String ORDER_ENDPOINT = "/orders";
    //Endpoint получения ингридиентов
    public static final String GET_INGREDIENTS_ENDPOINT = "/ingredients";
}
