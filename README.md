> #### Functional Details
>
> - This project exposes 3 APIs to perform create, update and retrieve operation on in memory database table.
> - A user can call the above APIs to add/update/retrieve holidays
> - Project is extensible and other operations like delete or findBy* can be implemented easily

> #### Software/Library Version Details
>
> - Java version 22
> - Spring boot 3.3.2
> - Springdoc 2.5.0
> - h2 2.2.224
> - Maven 3.9.8
> - Postman v11.4.0

> #### How to run locally
>
> - git clone the repository https://github.com/farhanraza15/holidays.git OR Unzip the holidays.zip
> - Make sure Java 22 and Maven 3.9.8 is installed on your machine
> - Use DOS CLI or GIT BASH. e.g. cd holidays
> - Inside /holidays folder run > mvn clean install
> - If everything goes fine then go to cd holidays/target
> - Now run java -jar target/holidays-0.0.1-SNAPSHOT.jar OR
> - If you have Spring ToolSuite 4 then import the project into and run this as Spring Boot App
> - Check the starup logs on console at end following message would appear:
Tomcat started on port 8080 (http) with context path '/'
Started FederalHolidaysApplication in...

> #### How to access
>
> - Once spring boot app is up
> - h2 database - http://localhost:8080/h2-console. Refer src/main/resources/application.properties to access the DB console
> - Open API Document - http://localhost:8080/swagger-ui/index.html
> - Swagger/Json - http://localhost:8080/api-docs

> #### How to load test data
>
> - src/test/resources/holiday-data.sql file is provided with insert queries. This would handle CANADA & USA federal holidays but can be further extended
> - Open http://localhost:8080/h2-console and copy paste the entire script on SQL panel and hit run button
> - This would load the whole data into in memory h2 database

> #### How to run APIs

> - Install postman and import src/test/resources/Federal Holidays REST API.postman_collection.json
> - Collection comes with 3 APIs and prefilled test data
> - Use postman to add, update or retrieve records from holiday table

