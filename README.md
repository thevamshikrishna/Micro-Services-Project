
# NeoBank – Modular Digital Banking System

A highly scalable, modular, and production-ready digital banking platform built with Java 17+, Spring Boot 3+, and Microservices Architecture. NeoBank demonstrates core banking functionalities such as user authentication, account management, fund transfers, loan handling, and notifications. Each service is independently deployable and built using SOLID principles and established design patterns.

## 📚 Table of Contents
- Overview
- System Architecture
- Microservices
- Tech Stack
- Design Patterns
- Setup & Installation
- Running the Project
- API Documentation
- Contributors
- License

## 🔍 Overview
NeoBank is a cloud-ready digital banking system designed for extensibility, performance, and ease of maintenance. It follows a microservice-based architecture, ensuring each domain (user, account, transactions, loans, notifications) operates as an independent and loosely coupled module.

Each service:
- Is independently deployable
- Maintains its own database schema (per service)
- Exposes RESTful APIs
- Can scale independently based on load

## 🏗️ System Architecture
```
                +----------------+
                |  API Gateway   |
                +--------+-------+
                         |
    +--------------------+--------------------+
    |        |           |          |         |
+---+--+  +--+---+   +---+---+  +----+---+  +--+---+
|User |  |Account|   |Transaction| | Loan |  |Notification|
+-----+  +-------+   +-----------+ +--------+  +----------+
    |         |             |         |           |
 [MySQL]   [MySQL]       [MySQL]   [MySQL]     [MySQL]
```
Optional Components:
- **Eureka Server** – Service discovery
- **Config Server** – Centralized configuration management

## 🔧 Microservices

### 1. User Service
**Responsibilities:**
- User registration & login
- JWT-based authentication
- Role-based authorization

**Entities:** User, Role  
**Design Patterns:** Factory (role creation), Singleton (JWT utility)  
**SOLID:** SRP for AuthService, UserService

### 2. Account Service
**Responsibilities:**
- Account creation & linking to user
- Balance management

**Entities:** Account, AccountType  
**Design Patterns:** Builder, Repository

### 3. Transaction Service
**Responsibilities:**
- Fund transfers (NEFT, IMPS, RTGS)
- Transaction logs
- Mini statements

**Entities:** Transaction, TransactionType  
**Design Pattern:** Strategy (transaction type handling)

### 4. Loan Service
**Responsibilities:**
- Loan application processing
- Approval/rejection
- EMI calculation

**Entities:** Loan, EMI, LoanType  
**Design Patterns:** Chain of Responsibility (loan approval), Decorator (insurance)

### 5. Notification Service
**Responsibilities:**
- Email/SMS notifications
- Alerting on transactions or updates

**Entities:** Notification, NotificationType  
**Design Pattern:** Observer (event-driven communication)

### 6. Gateway Service
**Responsibilities:**
- Centralized entry point for all requests
- Request routing & token validation

**Tools:** Spring Cloud Gateway, Netflix Eureka

## 🧰 Tech Stack
| Category        | Technology |
|-----------------|------------|
| Language        | Java 17+ |
| Framework       | Spring Boot 3+ |
| API Design      | REST, Spring Web |
| Security        | Spring Security, JWT |
| Databases       | MySQL (one per service) |
| Microservices   | Spring Cloud, Eureka |
| Config Management | Spring Cloud Config (opt.) |
| Mapping/Boilerplate | MapStruct, Lombok |
| Build Tool      | Maven |
| Containerization| Docker, Docker Compose |
| Documentation   | Swagger (OpenAPI) |

## 🧠 Design Patterns
| Pattern | Applied In |
|---------|------------|
| Factory | User role creation |
| Singleton | JWT Utility |
| Repository | JPA-based data access |
| Builder | Account creation logic |
| Strategy | Transaction handling |
| Chain of Responsibility | Loan approvals |
| Decorator | Add insurance to loans |
| Observer | Notification events |

## ⚙️ Setup & Installation

### ✅ Prerequisites
- Java 17+
- Maven
- MySQL
- Docker (optional)

### 📦 Clone the Repository
```bash
git clone https://github.com/yourusername/NeoBank.git
cd NeoBank
```

### 🔧 Configure MySQL
Each microservice uses its own MySQL database:

| Service | Database Name |
|---------|---------------|
| User Service | user_service_db |
| Account Service | account_service_db |
| Transaction Service | transaction_service_db |
| Loan Service | loan_service_db |
| Notification Service | notification_service_db |

Update the respective `application.yml` or `application.properties` in each service.

### 🛠️ Build All Services
```bash
mvn clean install
```

## ▶️ Running the Project

### 1. Run Services Individually
```bash
cd user-service
mvn spring-boot:run
```
Repeat for each service.

### 2. Run with Docker (Optional)
Build Docker Images:
```bash
docker build -t user-service ./user-service
docker build -t account-service ./account-service
docker build -t transaction-service ./transaction-service
```
Run with Docker Compose:
```bash
docker-compose up --build
```

## 📖 API Documentation
Swagger UI for each service:
- User Service: http://localhost:8080/swagger-ui/index.html
- Account Service: http://localhost:8081/swagger-ui.html
- Transaction Service: http://localhost:8082/swagger-ui.html
- Loan Service: http://localhost:8083/swagger-ui.html
- Notification Service: http://localhost:8084/swagger-ui.html
