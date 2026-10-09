# Inventory Management System - Backend

## Tech Stack
* Java 25
* Spring Boot 4
* Spring Security
* Spring Data JPA / Hibernate
* MySQL
* Maven

## Initial Setup
**Database Configuration** <br> <br>
   Ensure MySQL is running on `localhost:3306`. Update your database credentials in `src/main/resources/application.properties`: <br> <br>
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ims_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

   <br>
   Then Run the Application <br> <br>
   Default Seed Accounts <br>
On initial startup, the database automatically seeds two default user accounts:

    Admin: Username admin | Password admin123

    User: Username user | Password user123
