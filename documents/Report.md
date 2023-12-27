# Отчет по итогам тестирования #
### Краткое описание
Выполнена автоматизация тестирования веб-сервиса "Путешествие дня". В ходе тестирования были проверены:
1. Возможность оплаты двумя способами: покупка по карте и покупка в кредит;
2. Взаимодействие с банковским сервисом;
3. Взаимодействие с СУБД: MySQL и PostgreSQL;
4. Обработка ответа и демонстрация соответствующих уведомлений;
5. Выдача сообщений об ошибках при неверном заполнении формы.
### Количество тест-кейсов
Общее кол-во тест-кейсов: 60.
Успешных: 22(36.66%).
Неудачных: 38(63.33%).
![image](https://github.com/PershikovAlex/Diplom/assets/127410157/64f6d206-9cb7-471b-b1cc-35ba4d119598)
### Общие рекомендации
1. Составить подробную документацию к приложению.
2. Устранить найденные ошибки.
3. Добавить в код страниц CSS селкторы, которые помогут в дальнейшем тестировании приложения (например: `data-test-id=holder`).
4. Предусмотреть различные валидационные сообщения для разного рода ошибок при неправильном вводе в поля.