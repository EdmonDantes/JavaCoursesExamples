<!-- TOC -->

* [Задание](#задание)
* [Решение](src/main/java)

<!-- TOC -->

## Задание

1. Почитать про [semver](https://semver.org/lang/ru/spec/v2.0.0.html)
2. Добавить в зависимости библиотеку `com.fasterxml.jackson.core:jackson-databind:2.17.0`
3. Скачать и сохранить себе в ресурсы файлы `currencies.json` и `currencies_rates.json`.
    1. В `currencies.json` содержится список валют с полями `id`, `isoName` и `fullName`
    2. В `currencies_rates.json` содержится объект полями, которого являются `id` валют, а значениями объекты, полями
       которых является `id` валют, а значения вещественные числа.
4. В пакете `entity` создать класс `Currency` с полями `id` типа `int`, `fullName` типа `String` и  `isoName`
   типа `String`.
    1. Реализовать getter-ы (геттеры) для класса `Currency`
    2. Реализовать или сгенерировать `equals` и `hashCode` для класса `Currency`
5. Установить модификатор доступа `protected` для поля `storage` в классе `MemoryRepository`
6. В пакете `repository` создать класс `CurrencyResourceRepository`, который наследуется от `MemoryRepository<Currency>`
   и загружает информацию о валютах из файла `currencies.json`.
    1. Создайте конструктор для файла `CurrencyResourceRepository`, который принимает аргумент `mapper`
       типа `ObjectMapper` и читает файл `currencies.json` в память.
    2. Метод `save` в классе `CurrencyResourceRepository` должен всегда кидать исключение.
7. Добавьте поле `currency` типа `Currency` в класс `Product`
8. Удалите поле `amount` из класса `Sale`.
9. Создайте метод `getAmount` в классе `Sale`, который возвращает `Map<Currency, Double>`, являющейся всей суммой для
   всех валют, которые используются в покупке. (Если продукты будут с разной валютой, то их стоимость пойдет в разные
   ячейки в конечном результате). Так же учитывайте кол-во купленного продукта. Пример: Если в покупке будут такие
   продукты: Молоко (`100 руб, 2 штуки`), Пакет (`1 евро, 1 штука`), Картошка (`100 руб, 0.7 кг`), то конечная стоимость
   будет: `руб = 270, евро = 1`
10. В пакете `service` создайте класс `CurrencyService` с полями: `currencyRepository` типа `Repository<Currency>`
    и `rates` типа `Map<Currency, Map<Currency, Double>>`.
    1. В классе `CurrencyService` создайте конструктор который принимает аргументы: `mapper` типа `ObjectMapper`
       и `currencyRepository` типа `Repository<Currency>`, и загружает данные для поля `rates` из
       ресурса `currencies_rates.json`
    2. В классе `CurrencyService` реализуйте метод `getById`, который принимает аргумент `id` типа `int` и возвращает
       значение типа `Currency`, которое является валютой с указанным `id`.
    3. В классе `CurrencyService` реализуйте метод `getById`, который принимает аргумент `isoName` типа `String` и
       возвращает значение типа `Currency`, которое является валютой с указанным `isoName`.
    4. В классе `CurrencyService` реализуйте метод `getRate`, который принимает аргументы `from` и `to`  типа `Currency`
       и возвращает значение типа `double`, которое является курсом для валютной пары `from-to` (т.е. отношение
       сколько `from` содержится в `to`). Если не возможно определить курс для заданной валютной пары, вернуть `-1`.
11. Доработки для класса `SaleService`
    1. Добавить поле `currencyService` типа `CurrencyService`.
    2. Добавить аргумент в конструктор, который устанавливает значение поля `currencyService`.
    3. Реализовать метод `getAmount`, который принимает аргументы: `sale` типа `Sale` и `currency` типа `Currency`, и
       возвращает значение типа `double`, которое является всей суммой данной покупки в заданной валюте (т.е. взять все
       суммы в разных валютах, привести их к заданной валюте и сложить)
12. В пакете `repository` создать класс `JsonFilesRepository<T>`, который наследуется от `Repository<T>`
    1. Создать поле `mapper` типа `ObjectMapper`
    2. Создать поле `type` типа `TypeReference<T>`
    3. Создать поле `dir` типа `File`
    4. Создать поле `getIdLambda` типа `Function<T, Integer>`
    5. Создать конструктор который принимает аргументы для заполнения полей
    6. Реализуйте методы `save(T obj)`, `load(int id)`, `laod(List<Integer> ids)` и `loadAll` с
       использованием `ObjectMapper`