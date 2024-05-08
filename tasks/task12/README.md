<!-- TOC -->

* [Задание](#задание)
* [Решение](src/main/java)

<!-- TOC -->

## Задание

1. В пакете `repository` создать класс `MemoryRepositoryByLambda`, который наследуется от `MemoryRepository` с
   полем `getIdLambda` типа `Function<T, Integer>`
    1. Создать конструктор который принимает значение для поля `getIdLambda`
    2. Реализовать метод `getId`, который вызывает лямбду из поля `getIdLambda`
2. В классе `DirectorySaleRepository` создать метод `loadAllByPersonId`, принимающий аргумент `id` типа `int`, и
   возвращает значение типа `List<Sale>`, которое представляет собой все покупки совершенные человеком с
   определенным `id`. (Использовать `StreamAPI`)
3. В классе `DirectoryProductRepository` создать метод `loadAllByMaxPrice`, принимающий аргумент `maxPrice`
   типа `double`, и возвращает значение типа `List<Product>`, которое представляет собой все продукты у которых
   стоимость меньше чем аргумент `maxPrice`. (Использовать `StreamAPI`)