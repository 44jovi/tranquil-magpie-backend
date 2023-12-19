# Project Tranquil Magpie
An E-Commerce Backend API

## About
- A RESTful API designed to serve / process data for an e-commerce frontend client
- Focused on
  - **Reliability**
  - **Security**
    - Token authentication (JWT)
    - User authorisation by role
    - Data hashing (BCrypt)
    - Trusted third party payments processing (Stripe)
  - **Relational data**
    - Database design
    - Data integrity

## Main languages and tools
- **Java**
  - Spring Boot
  - Spring Security
  - JUnit / Mockito (Unit Tests)
  - Stripe API for Java
  - BCrypt
- **PostgreSQL**

## Key features
- User / admin accounts
  - Creation
  - Access
    - Public and secured endpoints
  - Management
- Products
  - Creation (admin)
  - Browse
  - Order
  - Pay

## Future features
- Integration testing
- OAuth / social authentication
- Order returns
- Enhanced IAM (identy / access management)
- Compatibility with cloud deployments / CI/CD
