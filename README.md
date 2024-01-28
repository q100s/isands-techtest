Для запуска приложения необходимо:
1) Создать базу данных с настройками, указанными в файле application.properties. Путь к файлу в корне проекта: src/main/resources/application.properties. Настройки для создания базы данных:
  spring.datasource.url=jdbc:postgresql://localhost:5432/isands
  spring.datasource.username=testUser
  spring.datasource.password=password
2) Запустить приложение методом main класса AppliancesCatalogApplication, который находится в пакете ru.isands.appliancescatalog
3) Запустить скрипты на заполнение базы данных, расположенные в файле fill_db.sql. Путь к файлу в корне проекта: src/main/resources/liquibase/scripts/fill_db.sql.
4) Перейти по ссылке в браузере: http://localhost:8080/swagger-ui.html для доступа к Swagger-UI.
