# Сервис фильтрации перелётов

Простое Java-приложение для фильтрации рейсов по различным условиям с использованием функциональных интерфейсов и Stream API.  
Проект легко расширяем и спроектирован с учётом гибкости и читаемости кода.

## Возможности

- Фильтрация рейсов по следующим критериям:
  - Вылет до текущего момента времени.
  - Прибытие раньше вылета.
  - Общее время на земле между сегментами превышает 2 часа.
- Удобное расширение: для добавления нового фильтра нужно реализовать один интерфейс `FlightFilter`.
- Современный подход на Java 8+: Stream API, лямбда-выражения, функциональные интерфейсы.
- В комплекте параметризованные тесты с использованием JUnit 5.

