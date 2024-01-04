# Project Tranquil Magpie

## About

- An E-Commerce Backend API
- A RESTful API designed to serve / process data for an e-commerce frontend client
- Focused on
    - **Reliability**
    - **Security**
        - Token authentication (JWT)
            - HMAC-SHA cryptographic signing key (symmetrical)
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
        - JWT (symmetrical HMAC-SHA)
        - BCrypt
    - JUnit / Mockito (Unit Tests)
    - Stripe API for Java
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
- Using PostgreSQL, run the SQL scripts found in
    - `src/main/resources/db`
        1. `db-create-db-and-schemas.sql`
        2. `db-create-tables.sql`
        3. `db-insert-initial-data.sql`
            - Only required for the `backend` schema

### Required local environment variables

- Refer to the local system environment variables in:
    - `application.properties`
        - They must first be set up on the local system
        - e.g. `${TRANQUIL_MAGPIE_DB_BASE_URL}` = data source base URL

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

- OAuth / social authentication
- Enhanced IAM (identity / access management)
- Compatibility with cloud deployments / CI/CD
- Order returns

## Disclaimer

- This application is for demonstration purposes only
    - Not to be used commercially
- **Do not use/expose any real, confidential data with this application**
 
