<!-- TOC -->

* [Задание](#задание)
* [Решение](src/main/java)

<!-- TOC -->

## Задание

1. Создать новый проект с системой сборки Gradle
2. Добавить в зависимости библиотеку `org.apache.commons:commons-lang3:3.14.0` (используя `implementation`)
3. Добавить в тестовые зависимости библиотеку `org.junit.jupiter:junit-jupiter:5.10.2` (используя `testImplementation`)
4. Установить значение группы равной `io.github.<github username>`
5. Установить значение версии равно `0.0.1`
6. Обновить конфигурацию Gradle (нажать кнопочку во вкладке Gradle)
7. Скопировать код из старого проекта в новый
8. Создать метод `loadAll` в интерфейсе `Repository`, который не принимает аргументов и возвращает `List<T>`, которое
   представляет собой все записанные объекты репозитория.
9. Реализовать метод `loadAll` во всех наследниках
10. Создать пакет `service`
11. В пакете `service` создать класс `ProductService`, который имеет поле `repository` типа `Repository<Product>` и
    конструктор с 1 аргументом, который устанавливает значение этого поля
12. Перенести метод `loadAllByMaxPrice` из класса `DirectoryProductRepository` в класс `ProductService`.
13. В пакете `service` создать класс `SaleService`, который имеет поле `repository` типа `Repository<Sale>` и
    конструктор с 1 аргументом, который устанавливает значение этого поля
14. Перенести метод `loadAllByPersonId` из `DirectorySaleRepository` в `SaleService`
15. Написать тест для метода `loadAllByMaxPrice` из класса `ProductService`. В качестве `Repository<Product>`
    используйте  `MemoryRepository` или `MemoryRepositoryByLambda`
16. Написать тест для метода `loadAllByPersonId` из класса `SaleService`. В качестве `Repository<Product>`
    используйте  `MemoryRepository` или `MemoryRepositoryByLambda`