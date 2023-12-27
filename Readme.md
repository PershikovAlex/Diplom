# Дипломный проект по профессии "Тестировщик" #
[1. ЗАДАНИЕ](https://github.com/netology-code/qa-diploma/blob/master/README.md)  
[2. ПЛАН АВТОМАТИЗАЦИИ](https://github.com/PershikovAlex/Diplom/blob/main/documents/Plan.md)  
[3. ОТЧЕТ ОБ АВТОМАТИЗАЦИИ](https://github.com/PershikovAlex/Diplom/blob/main/documents/Summary.md)
## Начало работы ##
### Необходимое ПО:
* IntelliJ IDEA;
* Git
* Docker Desktop
* Google Chrome
## Установка и запуск ##
  1. Склонировать проект на свой ПК командой:  
     ```
     git clone
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
  8. Завершить работу приложения сочетанием клавиш `CTRL+C` 
