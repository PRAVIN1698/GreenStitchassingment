# Spring Security h2 database

## Description

signup 
    -- USER WILL BE SIGNEDiN IN 
    --Information IN MEMEORY DATABSE - H2
     
Login
    ==  After login user need to login inorder to retrive the Bearer token

 Hello
    -- After Bearer token user will required token to access the hello api 
    -- Will receive welcome message 



## Prerequisites

- Java JDK 
- Maven 
- Git

# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console Configuration
spring.security.enabled=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console







## Installation

1. Clone the repository: `git clone https://github.com/yourusername/your-project.git`
2. Navigate to the project directory: `cd your-project`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`



## Usage

Explain how to use your application and provide examples if needed.


## Endpoints

List and explain the available API endpoints, including sample requests and responses.

- **POST /api/users/signup**
  - Description: Create a new user account
  - Request Body:
    ```json
    {
      "username": "john_doe",
      "password": "securepassword"
    }
    ```
  - Response:
    ```json
    {
      User succesfully created.
    }
    ```

    **POST /api/users/login**
    Request Body:
    ```json
    {
      "username": "john_doe",
      "password": "securepassword"
    }
    ```
    Response
    ---json
    {

    secure string

- **GET /api/users/hello**
  Hello fromn GreenStitch


  - Description: Retrieve a greeting message
  - Authentication: Requires a valid JWT token in the Authorization header

## Security

Need Beaear token to access the end point hello.

