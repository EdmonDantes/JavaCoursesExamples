<!-- TOC -->

* [Задание](#задание)
* [Решение](src/main/java)

<!-- TOC -->

## Задание

1. Добавить в
   зависимости `org.projectlombok:lombok:1.18.32`, `com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0`
2. Добавить в зависимости `annotationProcessor` `org.projectlombok:lombok:1.18.32`
3. Добавить в тестовые зависимости `org.projectlombok:lombok:1.18.32`
4. Добавить в тестовые зависимости `annotationProcessor` `org.projectlombok:lombok:1.18.32`
5. Доработки для класса `Sale`
    1. Добавьте конструктор по умолчанию (который не принимает аргументов)
    2. Добавьте аннотацию `@JsonIgnore` для метода `getProducts`
    3. Добавьте аннотацию `@JsonIgnore` для метода `setProducts`
    4. Создайте внутренний класс `JsonProduct` который содержит два поля `product` типа `Product` и `count`
       типа `Double`.
    5. Добавьте аннотации `@Data`, `@AllArgsConstructor` для класса `JsonProduct`
    6. Создайте метод `getJsonProducts` который не принимает аргументов и возвращает значение типа `List<JsonProduct>`,
       которое является преобразованным значением поля `products`. Пометьте его аннотацией `@JsonProperty("products")`.
    7. Создайте метод `setJsonProducts` который принимает один аргумент `jsonProducts`  типа `List<JsonProduct>`, не
       возвращает значение и устанавливает значения для поля `product`. Пометьте его
       аннотацией `@JsonProperty("products")`.
6. Доработки для класса `Person`
    1. Добавить аннотацию `@JsonCreator` для конструктора с `id` и добавить аннотацию `@JsonProperty("id")` для
       параметра
7. Доработки для класса `Product`
    1. Добавить аннотацию `@JsonCreator` для конструктора с `id` и добавить аннотацию `@JsonProperty("id")` для
       параметра
8. Доработки для класса `CurrencyService`
    1. Добавить поле `client` типа `HttpClient`
    2. Добавить поле `mapper` типа `ObjectMapper`
    3. Удалить поле `rates`
    4. Изменить конструктор, который только устанавливает значения полей
    5. Переписать метод `getRate` в классе `CurrencyService` так, чтобы при вызове метода приложение получало данные о
       курсе из стороннего сервиса: HTTP запрос с методом GET на
       адрес `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/<isoName>.min.json`, который
       возвращает json в котором содержиться информация о курсе для данной валюты
9. Создайте класс `Main` с точкой входа `main`
    1. Создайте объект класса `ObjectMapper`
    2. Зарегистрируйте `JavaTimeModule` в `ObjectMapper`
    3. Создайте объект класса `HttpServer`
    4. Создайте внутренний класс `SaleRequest`, который содержит поля `id` типа `Integer`, `ids`
       типа `List<Integer>`, `currencyId` типа `Integer`, `personId` типа `Double`.
    5. Пометьте класс `SaleRequest` аннотациями `@Data`  и `@JsonIgnoreProperties(ignoreUnknown = true)`
    6. Создайте внутренний класс `SaleResponse`, который содержит поля: `sales` типа `List<Sale>`, `amount`
       типа `List<Double>`.
    7. Пометьте класс `SaleResponse` аннотациями `@Data` и `@JsonInclude(Include.NON_EMPTY)`
    8. В методе `main`создайте объект класса `HttpServer`
    9. Создайте `HttpContext` для пути `/sales`
    10. Создайте handler для пути `/sales`, который будет десериализовать тело запроса, как объект класса `SaleRequest`.
        1. Запрос должен возвращать сериализованый объект класса `SaleResponse`
        2. Если присутствует поле `id`, то заполнить поле `sales` найденной покупкой
        3. Если присутствует поле `ids`, то заполнить поле `sales` найденными покупками
        4. Если присутствует поле `personId`, то заполнить поле `sales` найденными покупками для заданного человека
        5. Если присутствует поле `currencyId`, то заполнить поле `amount` суммами в заданной валюте для покупок из
           поля `sales`.
    11. Запустите `HttpServer`