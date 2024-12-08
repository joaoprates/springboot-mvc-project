```markdown
```markdown
# Product Management Project

## Description
# Product Management Project

## Description
## Updated README

This project is a product management application developed with Java and Spring Boot. It allows listing, adding, updating, deleting, and searching for products with optional filters.

## Technologies Used
- Java
This project is a product management application developed with Java and Spring Boot. It allows listing, adding, updating, deleting, and searching for products with optional filters.

## Technologies Used
- Java
```markdown
# Product Management Project

- Spring Boot
- Spring Data JPA
- Maven
- Spring Boot
- Spring Data JPA
- Maven
## Description
- Swagger (for API documentation)

## Features
- Swagger (for API documentation)

## Features
1. List all products
2. Get a product by ID
1. List all products
2. Get a product by ID
This project is a product management application developed with Java and Spring Boot. It allows listing, adding, updating, deleting, and searching for products with optional filters.

## Technologies Used
3. Add a new product
4. Update an existing product
- Java
- Spring Boot
3. Add a new product
4. Update an existing product
5. Delete a product
5. Delete a product
- Spring Data JPA
- Maven
6. Search products with optional filters (name, category, availability)

## Requirements
- Swagger (for API documentation)

## Features
6. Search products with optional filters (name, category, availability)

## Requirements
- Java 11 or higher
- Maven 3.6 or higher

## How to Run the Project
1. Clone the repository:
   ```bash
- Java 11 or higher
- Maven 3.6 or higher

1. List all products
2. Get a product by ID
3. Add a new product
## How to Run the Project
1. Clone the repository:
   ```bash
4. Update an existing product
5. Delete a product
   git clone https://github.com/joaoprates/projeto-gerenciamento-produtos.git
   ```
2. Navigate to the project directory:
   ```bash
   cd projeto-gerenciamento-produtos
   git clone https://github.com/joaoprates/projeto-gerenciamento-produtos.git
   ```
   ```
3. Compile and run the project using Maven:
6. Search products with optional filters (name, category, availability)

## Requirements
- Java 11 or higher
- Maven 3.6 or higher

2. Navigate to the project directory:
   ```bash
   cd projeto-gerenciamento-produtos
   ```bash
   mvn spring-boot:run
   ```
## How to Run the Project
   ```
1. Clone the repository:
   ```bash
4. Access the application at `http://localhost:9090`.

## API Documentation
3. Compile and run the project using Maven:
   ```bash
   mvn spring-boot:run
   ```
git clone https://github.com/joaoprates/projeto-gerenciamento-produtos.git
   ```
2. Navigate to the project directory:
4. Access the application at `http://localhost:9090`.

## API Documentation
The API documentation can be accessed via Swagger at `http://localhost:9090/swagger-ui.html`.

## Tests
To run the tests, use the command:
   ```bash
   cd projeto-gerenciamento-produtos
   ```
```bash
mvn test
```

## Project Structure
3. Compile and run the project using Maven:
   ```bash
The API documentation can be accessed via Swagger at `http://localhost:9090/swagger-ui.html`.

## Tests
To run the tests, use the command:
- `src/main/java/com/pratesdev/controller`: Contains the REST controllers.
```bash
mvn test
```

## Project Structure
mvn spring-boot:run
   ```
- `src/main/java/com/pratesdev/model`: Contains the JPA entities.
4. Access the application at `http://localhost:9090`.

- `src/main/java/com/pratesdev/controller`: Contains the REST controllers.
## API Documentation
- `src/main/java/com/pratesdev/repository`: Contains the JPA repositories.
- `src/main/java/com/pratesdev/model`: Contains the JPA entities.
The API documentation can be accessed via Swagger at `http://localhost:9090/swagger-ui.html`.

- `src/main/java/com/pratesdev/service`: Contains the service classes.
- `src/main/java/com/pratesdev/repository`: Contains the JPA repositories.
## Tests
To run the tests, use the command:
```bash
- `src/main/java/com/pratesdev/config`: Contains the security configurations.

## Security Configuration
- `src/main/java/com/pratesdev/service`: Contains the service classes.
mvn test
```

## Project Structure
The Swagger routes and the `/health` route are public and do not require authentication. All other routes require authentication.

- `src/main/java/com/pratesdev/config`: Contains the security configurations.

## Security Configuration
## Database
- `src/main/java/com/pratesdev/controller`: Contains the REST controllers.
  The Swagger routes and the `/health` route are public and do not require authentication. All other routes require authentication.

The project no longer uses the H2 database. Please configure your preferred database in the `src/main/resources/application.properties` file.

## Contribution
- `src/main/java/com/pratesdev/model`: Contains the JPA entities.
## Database
- `src/main/java/com/pratesdev/repository`: Contains the JPA repositories.
  To contribute to the project, fork the repository, create a branch for your changes, and submit a pull request.

The project uses a different database for testing and production. The database configuration can be found in the `src/main/resources/application.properties` file.

- `src/main/java/com/pratesdev/service`: Contains the service classes.
## License
## Contribution
This project is licensed under the MIT license.
```- `src/main/java/com/pratesdev/config`: Contains the security configurations.

To contribute to the project, fork the repository, create a branch for your changes, and submit a pull request.

## License
This project is licensed under the MIT license.
```## Security Configuration
The Swagger routes and the `/health` route are public and do not require authentication. All other routes require authentication.

## Database
The project uses a different database for testing and production. The database configuration can be found in the `src/main/resources/application.properties` file.

## Contribution
