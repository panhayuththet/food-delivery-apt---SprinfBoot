# 🍔 Food Delivery Microservices API

A scalable and event-driven Food Delivery System built using **Spring Boot Microservices Architecture**, designed to handle restaurant management, user management, orders, payments, deliveries, notifications, and customer feedback.

---

## 📖 Overview

This project demonstrates a modern microservices architecture using:

* Spring Boot
* Spring Cloud Gateway
* PostgreSQL
* Redis
* Apache Kafka
* Docker

The system follows the **Database per Service** pattern and uses **Kafka** for asynchronous communication between services while **Redis** improves performance through caching.

---

## 🏗️ System Architecture

### Main Components

* API Gateway
* User Service
* Restaurant Service
* Order Service
* Delivery Service
* Payment Service
* Notification Service
* Feedback Service
* PostgreSQL Databases
* Redis Cache
* Apache Kafka

---

## 🔄 Request Flow

```text
Client Application
        │
        ▼
   API Gateway
        │
 ┌──────┼──────────────────────────────┐
 │      │      │      │      │        │
 ▼      ▼      ▼      ▼      ▼        ▼
User Restaurant Order Delivery Payment Feedback
```

---

## ⚡ Event-Driven Communication

### Order Processing Flow

```text
Order Service
      │
      ▼
 Apache Kafka
      │
      ├── Notification Service
      ├── Delivery Service
      └── Future Consumers
```

### Payment Processing Flow

```text
Payment Service
      │
      ▼
 Apache Kafka
      │
      ▼
Notification Service
```

Benefits:

* Loose Coupling
* Better Scalability
* High Availability
* Improved Reliability
* Asynchronous Processing

---

## 🧰 Technology Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Validation
* Lombok

### Database

* PostgreSQL

### Messaging

* Apache Kafka

### Caching

* Redis

### API Gateway

* Spring Cloud Gateway

### Build Tool

* Maven

### Containerization

* Docker
* Docker Compose

---

## 📦 Microservices

### 👤 User Service

Responsible for:

* User Registration
* User Profile Management
* Authentication
* User Information Management

---

### 🍽️ Restaurant Service

Responsible for:

* Restaurant Management
* Menu Management
* Restaurant Information

---

### 🛒 Order Service

Responsible for:

* Order Creation
* Order Management
* Order Status Updates
* Publishing Order Events

---

### 🚚 Delivery Service

Responsible for:

* Delivery Assignment
* Delivery Tracking
* Delivery Status Management

---

### 💳 Payment Service

Responsible for:

* Payment Processing
* Transaction Management
* Publishing Payment Events

---

### 🔔 Notification Service

Responsible for:

* Email Notifications
* SMS Notifications
* Order Updates
* Payment Alerts

---

### ⭐ Feedback Service

Responsible for:

* Customer Reviews
* Ratings
* Feedback Management

---

## 🚀 Redis Caching

Redis is used to cache frequently accessed data:

* User Information
* Restaurant Information
* Customer Feedback

### Benefits

* Faster Response Time
* Reduced Database Queries
* Improved Performance
* Better Scalability

---

## 🗄️ Database Architecture

Each service owns its own PostgreSQL database.

| Service              | Database   |
| -------------------- | ---------- |
| User Service         | PostgreSQL |
| Restaurant Service   | PostgreSQL |
| Order Service        | PostgreSQL |
| Delivery Service     | PostgreSQL |
| Payment Service      | PostgreSQL |
| Notification Service | PostgreSQL |
| Feedback Service     | PostgreSQL |

This implementation follows the **Database-per-Service Pattern**.

---

## ✨ Features

### Customer Features

* User Registration
* Login & Authentication
* Browse Restaurants
* Place Orders
* Online Payments
* Order Tracking
* Notifications
* Reviews & Ratings

### Restaurant Features

* Manage Restaurant Profiles
* Manage Menus
* Receive Orders
* Update Order Status

### Delivery Features

* Delivery Assignment
* Delivery Tracking
* Delivery Status Updates

---

## 📂 Project Structure

```text
food-delivery-api
│
├── api-gateway
├── user-service
├── restaurant-service
├── order-service
├── delivery-service
├── payment-service
├── notification-service
├── feedback-service
│
├── docker-compose.yml
├── pom.xml
├── README.md
└── docs
```

---

## 🐳 Running the Project

### Clone Repository

```bash
git clone https://github.com/your-username/food-delivery-api.git
cd food-delivery-api
```

### Build Project

```bash
mvn clean install
```

### Start Infrastructure

```bash
docker-compose up -d
```

### Run Services

```bash
mvn spring-boot:run
```

---

## 🔮 Future Enhancements

* JWT Authentication
* OAuth2 Integration
* Spring Security
* Eureka Service Discovery
* Config Server
* Circuit Breaker (Resilience4j)
* Distributed Tracing (Zipkin)
* Monitoring with Prometheus & Grafana
* Kubernetes Deployment
* GitHub Actions CI/CD

---

## 👨‍💻 Author

**Thet Panhayuth**

Backend Developer | Java Developer | Spring Boot Developer

GitHub: https://github.com/panhayuththet

---

## 📄 License

This project is created for educational, learning, and portfolio purposes.
