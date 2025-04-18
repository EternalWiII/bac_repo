# Cloud Storage Backend

A **Java-based application** serving as the backend for a cloud storage service.

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── practiceproject
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── dto
│   │   │               ├── entity
│   │   │               ├── exception
│   │   │               ├── repository
│   │   │               ├── service
│   │   │               └── utils
│   │   └── resources
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── practiceproject
├── storage
└── target
    ├── classes
    │   ├── com
    │   │   └── example
    │   │       └── practiceproject
    │   │           ├── config
    │   │           ├── controller
    │   │           ├── dto
    │   │           ├── entity
    │   │           ├── exception
    │   │           ├── repository
    │   │           ├── service
    │   │           └── utils
    │   └── templates
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-archiver
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       └── testCompile
    │           └── default-testCompile
    └── test-classes
        └── com
            └── example
                └── practiceproject
```
## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17
- Maven 3.6 or higher
- PostgreSQL 14 or higher

## Installation

Clone the repository:

```
git clone https://github.com/EternalWiII/bac_repo.git
```

Navigate to the project directory:
```
cd bac_repo
````
Change bac_repo/src/main/resources/application.yaml for your configuration:
- Spring datasource config
- Jwt secret key (Can be generated by https://jwtsecret.com/generate)

Build the project using Maven:
- mvn clean install

## Running the Application:
```
java -jar target/bac_repo-1.0-SNAPSHOT.jar
```

## Usage

# Registration

```
curl --location 'http://localhost:8080/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user123ddaad",
    "email": "random122adadd@gmail.com",
    "password": "1234567a!",
    "confirmPassword": "1234567a!"
}'
```

# Login (returns a JWT-TOKEN itself)

'''
curl --location 'http://localhost:80/auth' \
--header 'Content-Type: application/json' \
--data '{
  "username": "user12",
  "password": "12345"
}'
'''

# Download

```
curl -X GET "http://localhost:8080/download/example.pdf" \
  -H "Authorization: Bearer your_token_here" \
  -o example.pdf
```

# Upload

```
curl -X POST "http://localhost:8080/upload" \
  -H "Authorization: Bearer your_token_here" \
  -F "file=@/path/to/your/file.pdf"

```

## Code Documentation Guidelines

### Standards
We use **JavaDoc** to document code and **Springdoc OpenAPI** to automatically generate REST API documentation.

### What to Document
- All **public methods** and **classes**, especially in the `controller`, `service`, and `repository` layers.
- REST API methods should include a clear description of parameters, return values, and possible exceptions.

### Tools
- **JavaDoc** — use `mvn javadoc:javadoc` to generate HTML documentation for the codebase.
- **Springdoc OpenAPI** — automatically generates REST API documentation available at `http://localhost:8080/swagger-ui.html`.

### JavaDoc Example
```java
/**
 * Uploads a file to the user's cloud storage.
 *
 * @param userId ID of the user
 * @param file Multipart file to upload
 * @return URL of the uploaded file
 */
public String uploadFile(Long userId, MultipartFile file) { ... }
```

## Developer Setup Guide

This section will guide you through setting up the project in a development environment on a freshly installed operating system.

---

### 1. Required Dependencies and Software

Install the following software:

#### System Dependencies:
- **Git** – version control system
- **Java 17** – JDK for the backend
- **PostgreSQL 15+** – database
- **Maven 3.6+** – for building the Java project

#### Commands for Ubuntu/Debian:

```bash
sudo apt update
sudo apt install git openjdk-17-jdk postgresql maven
```

### Setting up the Development Environment
1. Clone the repository
```bash
https://github.com/EternalWiII/bac_repo
cd bac_repo
```

2. Install and Configure Dependencies
```bash
cd backend
mvn clean install
```

3. Database Setup and Configuration
Start PostgreSQL (if not already running):
```bash
systemctl start postgresql
```

Create a database and user(or just do from postgres):
```bash
sudo -u postgres psql
CREATE USER clouduser WITH PASSWORD 'cloudpass';
CREATE DATABASE cloudstorage OWNER clouduser;
\q
```

Configure application.yaml:
```bash
datasource:
    username: postgres
    url: jdbc:postgresql://localhost:5432/postgres
    password: ${DB_PASSWORD}

jwt:
  secret: ${JWT_SECRET}
  lifetime: 30m
```
4. Run the project
```bash
cd bac_repo
java -jar target/{NAME}.jar
```
5. Basic commands and operations:
Build: mvn clean install
Tests: mvn test
Linting: mvn checkstyles:check

