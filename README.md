# Project Tranquil Magpie

An E-Commerce Backend API

## About

- A RESTful API designed to serve / process data for an e-commerce frontend client
- Focused on
    - **Reliability**
    - **Security**
        - Token authentication (JWT)
            - HMAC-SHA signing key (symmetrical)
                - HS256 hashing algorithm (HMAC with SHA-256)
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
        - UUID's
- Products
    - Creation (admin)
    - Browse
    - Order
    - Pay

## Setup (WIP)

### Database setup

- Install PostgreSQL on your local system
- Use a SQL databse management tool or command line to run the scripts found in
    - `src/main/resources/db`
        - `db-init.sql` - database and schema creation
            - Note you may first need to run the `CREATE DATABASE` and `CREATE SCHEMA` statements separately
    - `db-data-insert.sql` - initial example data inserts

### Required local environment variables

- Refer to the local environment variables in the following configuration files:
    - `application.properties`
    - `application-test.properties`
- They must first be set up on the local system running to application

### Application secret key (symmetrical)

- This application requires a secret key for its user authentication (via JWT token authentication)
- Generate a secret key and store under a local environment variable:
    - Convert a secret key (text string) of 32 bytes / 256 bits to Base64
    - Store it in a local environment variable called `TRANQUIL_MAGPIE_SK`
- **Never expose the secret key**

## API documentation

- Swagger UI endpoint
    - `/swagger-ui/index.html`
        - e.g. `http://localhost:8080/swagger-ui/index.html`

## Future features

- Integration testing
- OAuth / social authentication
- Order returns
- Enhanced IAM (identity / access management)
- Compatibility with cloud deployments / CI/CD

## Disclaimer

- This application is for demonstration purposes only
    - Not to be used commercially
- **Do not use/expose any real, confidential data with this application**