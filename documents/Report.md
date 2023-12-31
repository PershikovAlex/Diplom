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
Успешных: 26(43.33%).
Неудачных: 34(56.66%).
![image](https://github.com/PershikovAlex/Diplom/assets/127410157/9c04a484-7316-4f6d-a283-9844f52559cc)
### Общие рекомендации
1. Составить подробную документацию к приложению.
2. Устранить найденные [ошибки](https://github.com/PershikovAlex/Diplom/issues).
3. Добавить в код страниц CSS селекторы, которые помогут в дальнейшем тестировании приложения (например: `data-test-id=holder`).
4. Предусмотреть различные валидационные сообщения для разного рода ошибок при неправильном вводе в поля, чтобы пользователю было более понятно что он делает не так.
