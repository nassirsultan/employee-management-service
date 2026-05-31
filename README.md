[readme_employee.md](https://github.com/user-attachments/files/28436660/readme_employee.md)
# Employee Management System

A RESTful API built with **Java 17** and **Spring Boot** for managing employee data in an organization. Features secure authentication, role-based access, and clean REST endpoints with full validation and error handling.

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT Authentication**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Swagger / OpenAPI** (API documentation)
- **Maven**

## Features

- JWT-based authentication and authorization
- Employee CRUD operations (Create, Read, Update, Delete)
- Filter employees by department and role
- Pagination support for employee listing
- DTO-based request/response with input validation
- Global exception handling (404 Not Found, 409 Conflict for duplicate emails, validation errors)
- Swagger UI for API exploration and testing
- Spring Actuator for application health monitoring

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/login | Authenticate and get JWT token |
| GET | /employees | Get all employees (paginated) |
| GET | /employees/{id} | Get employee by ID |
| POST | /employees | Create new employee |
| PUT | /employees/{id} | Update employee |
| DELETE | /employees/{id} | Delete employee |
| GET | /employees?department=&role= | Filter by department and role |

## Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven

### Run the Application

```bash
git clone https://github.com/nassirsultan/employee-management-system
cd employee-management-system
# Configure database in src/main/resources/application.properties
mvn spring-boot:run
```

### Access Swagger UI
```
http://localhost:8080/swagger-ui.html
```

## Project Structure

```
src/
├── controller/      # REST controllers
├── service/         # Business logic
├── repository/      # JPA repositories
├── model/           # Entity classes
├── dto/             # Request/Response DTOs
├── security/        # JWT filter and security config
└── exception/       # Global exception handler
```
