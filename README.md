🟦 E-Commerce Microservices — README.md
🚀 Overview

This project is a Microservices-based E-Commerce System built using Spring Boot 3, Spring Cloud, Eureka, API Gateway, JWT Auth, Kafka, and PostgreSQL.
Each service runs independently and communicates through the API Gateway and Service Registry.

This system follows a real-world scalable architecture, suitable for production-grade backend development.

🏗️ Microservices Included
Service	 Description
Auth Service - Handles user registration, login & JWT authentication
Product Service	- CRUD operations for products
Cart Service	- User cart management
Order Service	- Order placement & processing
Payment Service	- Simulated payment workflow
Inventory Service	- Stock management
API Gateway	- Single entry point for all microservices
Eureka Server -	Service Discovery

🔌 Tech Stack
Backend

Java 17
Spring Boot 3
Spring Cloud (Gateway, Eureka)
Spring Security + JWT
Spring Data JPA
PostgreSQL
Kafka 
Actuator

Build Tools

Maven
IntelliJ IDEA 

⚙️ How to Run the Project
1️⃣ Start Eureka Server
mvn spring-boot:run

2️⃣ Start API Gateway
mvn spring-boot:run

3️⃣ Start Each Microservice

Each module must be started individually:

mvn spring-boot:run

Make sure:

✔ Unique server.port
✔ Eureka URL correctly set
✔ Database connected
✔ API Gateway routes match service names

🛠️ Basic Application Properties (LOCAL Setup)
Eureka Server
server.port=8761
spring.application.name=discovery-server
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

API Gateway
server.port=8081
spring.application.name=gateway-service

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

spring.cloud.gateway.globalcors.cors-configurations.[/**].allowedOrigins=*
spring.cloud.gateway.globalcors.cors-configurations.[/**].allowedMethods=*
spring.cloud.gateway.globalcors.cors-configurations.[/**].allowedHeaders=*

Auth Service (PostgreSQL Example)
server.port=8082
spring.application.name=auth-service

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your-secret-key
jwt.expiration=3600000
jwt.refreshExpirationMs=86400000

🧪 API Testing Flow (IMPORTANT + SHORT VERSION)
✔ 1. Health Check
GET /actuator/health

✔ 2. Auth Service
POST /api/auth/register
POST /api/auth/login

✔ 3. Product Service
POST /api/products/
GET /api/products/

✔ 4. Cart Service
POST /api/cart/add
GET /api/cart/{userId}

✔ 5. Order Service
POST /api/orders/create

✔ 6. Payment Service
POST /api/payments/pay


🧩 Architecture Summary
🔹 API Gateway -
Handles incoming requests and redirects them to appropriate services.

🔹 Eureka Server -
Allows services to register and discover each other.

🔹 Microservices

Each service contains its own:

Database
Controllers
Services
Repositories
Configurations

🔹 Decoupled Architecture

Each module can scale horizontally without breaking other services.

🗂 Project Folder Structure
/auth-service
/product-service
/cart-service
/order-service
/payment-service
/inventory-service
/shipping-service
/api-gateway
/eureka-server

📦 Local Setup Tips

✔ Install PostgreSQL
✔ Update credentials inside application.properties
✔ Ensure Eureka is running before any microservice
✔ Use Postman to verify routes through Gateway only

🎯 Highlight Features

✔ Microservices Architecture
✔ JWT Authentication
✔ API Gateway Routing
✔ Service Discovery
✔ Independent Databases
✔ Fault-Tolerant Design
✔ Scalable Structure
✔ Clean Code + Layered Architecture

📘 Future Enhancements

➡ Docker Compose Support
➡ Redis Caching
➡ ELK Logging
➡ React Frontend
➡ Deployment on Cloud



