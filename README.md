# CoffeeDeliveryEventSource
Реализация записи Event Source в БД.

Файл для MySQL src/main/resources/CoffeeDelivery.sql

API

POST добавление нового события /addEvent
тело события:

Регистрация
{
    "consumer_id": 1,
    "order_id": 1,
    "employee_id": 1,
    "plan_date_delivery": "30.05.2023",
    "product_id": 1,
    "price": 99.99,
    "date": "28.05.2023",
    "status": 1
}

Принят в работу
{
    "order_id": 1,
    "employee_id": 1,
    "date": "28.05.2023",
    "status": 2
}

Готов к выдаче
{
    "order_id": 1,
    "employee_id": 1,
    "date": "28.05.2023",
    "status": 3
}

Выполнено
{
    "order_id": 1,
    "employee_id": 1,
    "date": "28.05.2023",
    "status": 5
}

Отмена
{
    "order_id": 1,
    "employee_id": 1,
    "date": "28.05.2023",
    "cancellation_reasons": "клиент отказался",
    "status": 4
}

Статусы хранятся в БД
1	Registered
2	Assert To Work
3	Ready for delivery
4	Cancellation
5	Issued

В application.properties указаны важные переменные необходимые для запрета выполнения добавления события:
status.Registered=1
status.Cancellation=4
status.Issued=5


GET получение информации по заказу /get/id
{
    "id": 1,
    "plan_date_delivery": "30.05.2023",
    "past_status": [
        {
            "id": 1,
            "name": "Registered",
            "date": "28.05.2023"
        },
        {
            "id": 2,
            "name": "Assert To Work",
            "date": "28.05.2023"
        },
        {
            "id": 3,
            "name": "Ready for delivery",
            "date": "28.05.2023"
        }
    ],
    "cancellation_reasons": null,
    "date": "28.05.2023",
    "status": {
        "id": 5,
        "name": "Issued"
    },
    "product": {
        "id": 1,
        "price": 99.99
    },
    "employee": {
        "id": 1
    },
    "consumer": {
        "id": 1
    }
}

Добавлены настройки для POSTMAN 
CoffeeDelivery.postman_collection.json
