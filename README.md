# 🛒 E-Commerce Backend System

A complete **E-Commerce backend application** built using **Spring Boot** following clean architecture and best practices.  
The system provides secure, scalable REST APIs for managing users, products, categories, carts, and orders.

---

## 🚀 Features

- User Registration & Login with **JWT Authentication**
- Role-based Authorization (**USER / ADMIN**)
- Product & Category Management
- Shopping Cart functionality
- Order creation from cart
- Secure APIs using Spring Security
- Clean layered architecture
- Implemented pagination and sorting using Spring Data Pageable
- DTOs & Mappers (no entity exposure)
- Global exception handling & validation

---

## 🔐 Security

- Spring Security (Latest Version)
- JWT-based authentication (Stateless)
- BCrypt password hashing
- Custom JWT Authentication Filter
- Role-based access control:
  - **USER**: browse products, manage cart, create orders
  - **ADMIN**: manage products & categories

---
## 🐳 Docker Support

This project is fully dockerized using Docker and Docker Compose.

### Run the application using Docker

## 🧱 Architecture

The project follows a **layered architecture**:

controller
service
repository
dto
mapper
entity
config
exception

yaml
Copy code

Each layer has a single responsibility to ensure maintainability and scalability.

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Lombok
- Postman (API Testing)

---

## 📦 Modules

### 👤 User Module
- Register
- Login (JWT generation)
- Role support
- One-to-One relation with Cart

### 🗂 Category Module
- Create / Update / Delete (ADMIN)
- View categories (USER, ADMIN)

### 📦 Product Module
- CRUD operations (ADMIN)
- Browse products (USER, ADMIN)

### 🛒 Cart Module
- Add product to cart
- Remove product from cart
- Clear cart
- One cart per user

### 📄 Order Module
- Create order from cart
- Store product price at purchase time
- View user orders

---


## 🔗 API Security Rules

| Endpoint | Access |
|--------|--------|
| `/api/user/register` | Public |
| `/api/user/login` | Public |
| `/api/products/**` | USER, ADMIN |
| `/api/categories/**` | USER (GET), ADMIN (CRUD) |
| `/api/cart/**` | USER |
| `/api/orders/**` | USER |

---

## ▶ How to Run the Project

1. Clone the repository:
```bash
git clone https://github.com/your-username/ecommerce-spring-boot.git
Configure database in application.properties

Run the project:

bash
Copy code
mvn spring-boot:run
Test APIs using Postman (JWT required for secured endpoints)

🧪 Testing
APIs tested using Postman

JWT-protected endpoints validated

Role-based authorization verified

📌 Future Improvements
Product reviews & ratings

Wishlist feature


Payment integration

Admin dashboard

👨‍💻 Author
Samir Ahmed
Backend Developer (Java | Spring Boot)

⭐ If you like this project, feel free to give it a star!
