TODO list


Task 1: 
Создать гит репозиторий и закинуть в файл ссылку на него https://docs.google.com/spreadsheets/d/1gegLh89nJgBq3X0xrnMKJJSdCXZZkeRXjAoQsd0DcIw/edit#gid=0
Спроектировать БД
Инициализировать проект (maven\gradle)
Написать Entity 
Приложение должно быть сконфигурировано таким образом, чтобы запускалось в Tomcat.
подключить H2
Запушить проект в репозиторий (нейминг веток должно соответствовать номеру задачи, которую вы выполняете) 
Task 2: 
Написать слой Controller + Service + Dao (JDBC Template) а также имплементировать следующий функционал.
Sign Up
Sign In
Создать задачу
Удалить задачу
Найти все мои задачи
Пометить задачу как выполненная
Пометить задачу как невыполненная 

Task 3: 
Добавить Юзеру поле SUBSCRIPTION. При регистрации оно должно быть пустым. Предусмотреть функционал, чтобы юзер мог купить подписку. Задачей этого эндпоинта будет вставить слово «secret» в БД с использованием md5 алгоритма.
Подразумевается, что пользователь с бесплатной подпиской не может иметь больше 10-ти задач поэтому необходимо проследить чтобы в БД не содержалось одновременно более 10 тасок. 
Для проверки платности или бесплатности необходимо создать аспект, который при попытке пользователя добавить новую задачу будет проверять поле SUBSCRIPTION, значение которой должно быть распаршено с применением md5 алгоритма. Если значение будет равно "secret", то юзер в платном режиме, иначе, или если опция не указана - в бесплатном. При попытке сохранить более 10 задач необходимо упасть по Exception.

Добавляем приоритет для задачи. (LOW, MEDIUM, HIGHT, IMPORTANT).
Добавляем функционал изменения приоритета задачи. 



Task 4:
Добавляем роль юзеру. Должен быть администратор, который имеет доступ ко всему в БД и обычный юзер. 

Подразумевается, что наше приложение использует как зависимость - legacy модуль системы безопасности, который осуществляет работу с пользователями.
Задача модуля проста и будет заключаться в проверке роли юзера. И допуск его к соответствующим эндпоинтам.  
Логика проверки будет следующая. Мы вызываем метод сервиса легаси модуля, он должен проверить является ли пользователь админом или нет, если нет, возвращаем false и кидаем эксепшн в основном приложении(если обычный пользователь хочет воспользоваться админским эндпоинтом).

Данный модуль должен быть отдельным проектом в той же папке, который собирается через maven и билдится в jar со спринговскими зависимостями. Этот jar должен подключаться в наш исходный проект как зависимость из локального репозитория.
Все спринговские компоненты legacy либы должны быть сконфигурированы на xml (у нас же легаси ). То есть - сбилдили либу, подключили как зависимость у нас, подключили сервис через DI. Тесты на получение юзеров должны быть написаны в новом модуле.

Переписываем весь слой DAO(JDBCTemplate) на Hibernate(EntityManager). Переписываем контроллеры на RestController и подключаем Swagger.

Task 5:
Покрываем весь наш функционал тестами
подключаем ControllerAdvice и делаем красивые эксепшны.
Добавить возможность (создать эндпоинт) прикреплять файл к задаче (только для платной подписки) 
Пишем несколько интеграционных тестов(MockMVC или RestAssured). 

Task 6:
Переписываем весь слой DAO(Hibernate) на использование JPA репозиториев (используем PagingAndSortingRepository где необходимо) 
Создаем DTO для каждой Entity
Подключаем Javax Валидаторы для DTO (JSR 380)
Предусматриваем эндпонит, который сортирует задачи по степени их приоритета. 

Task 7: 
Мигрировать на Spring Boot
Пишем документацию в README.md 
Деплоим проект на Heroku



