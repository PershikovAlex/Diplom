# Дипломный проект по профессии "Тестировщик" #
## Документы ##
[1. ЗАДАНИЕ](https://github.com/netology-code/qa-diploma/blob/master/README.md)  
[2. ПЛАН АВТОМАТИЗАЦИИ](https://github.com/PershikovAlex/Diplom/blob/main/documents/Plan.md)  
[3. ОТЧЕТ ПО ИТОГАМ ТЕСТИРОВАНИЯ](https://github.com/PershikovAlex/Diplom/blob/main/documents/Report.md)  
[4. ИТОГОВЫЙ ОТЧЕТ](https://github.com/PershikovAlex/Diplom/blob/main/documents/Summary.md)
## Описание проекта ##
#### Веб-сервис "Путешествие дня"
Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

Приложение предлагает купить тур по определённой цене с помощью двух способов:
* Оплата по дебетовой карте;
* Оформление кредита.  

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* Сервису платежей - Payment Gate;
* Кредитному сервису - Credit Gate.

Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно ли он был совершён.

## Начало работы ##
### Необходимое ПО:
* IntelliJ IDEA;
* Git
* Docker Desktop
* Google Chrome
## Установка и запуск ##
  1. Склонировать проект на свой ПК командой:  
     ```
     git clone git@github.com:PershikovAlex/Diplom.git
     ```   
  2. Запустить Docker Desktop.
  3. Открыть проект в IntelliJ IDEA  
  4. Запустить контейнеры в терминале командой:  
     ```
     docker-compose up
     ```
  5. Зупустить SUT командой:
     * для MySQL
       ```
       java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar
       ```
     * для PostgreSQL
       ```
       java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar
       ```
  6. Запустить авто-тесты командой:
     * для MySQL
       ```
       ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
       ```
     * для PostgreSQL
       ```
       ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
  7. Для формирования отчета ввести команду:
       ```
       ./gradlew allureServe
       ```
       После формирования отчет автоматически откроется в бразере.
  8. Завершить работу приложения сочетанием клавиш `CTRL+C`
  9. Остановить работу контейнеров командой в терминале `docker compose down`
