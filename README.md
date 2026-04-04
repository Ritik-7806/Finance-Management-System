# Finance Management System Backend

Live Url: "https://finance-management-system-28ja.onrender.com/"

A production-style backend project for managing personal finance records with role-based access control, JWT authentication, containerized deployment, and Kubernetes support.

This project is built as a backend-focused system that demonstrates:

- clean layered architecture
- REST API design
- Spring Security with JWT-based authentication
- role-based authorization for `ADMIN`, `ANALYST`, and `VIEWER`
- MySQL persistence using Spring Data JPA
- Docker containerization
- Kubernetes deployment with backend, database, service, ingress, and persistent storage setup

## Project Overview

The Finance Management System Backend is designed to help users manage income and expense records securely. The system supports different user roles with different levels of access:

- `VIEWER`: manages personal profile and personal finance records
- `ANALYST`: reviews finance data and dashboards
- `ADMIN`: manages users and privileged account creation

The project is intended to showcase backend development skills across application design, security, data persistence, and deployment workflows.

## Core Features

- User signup and login
- JWT-based authentication
- Password encryption using BCrypt
- Role-based endpoint protection
- Create, update, view, and delete personal finance records
- Fetch user-specific finance records
- Dashboard summary generation
- Admin APIs to create admins and analysts
- Admin API to list all users
- Analyst APIs to inspect records and dashboard data
- MySQL-backed persistence layer
- Dockerized application packaging
- Kubernetes manifests for backend and database deployment

## Tech Stack

- `Java`
- `Spring Boot`
- `Spring Web MVC`
- `Spring Security`
- `Spring Data JPA`
- `Hibernate`
- `MySQL`
- `JWT (jjwt)`
- `Lombok`
- `Maven`
- `Docker`
- `Docker Compose`
- `Kubernetes`

## Project Structure

```text
fms/
|-- src/main/java/com/backend/fms
|   |-- Config
|   |-- Controller
|   |-- DTO
|   |-- Entity
|   |-- Enum
|   |-- Repository
|   |-- Service
|   `-- FmsApplication.java
|-- src/main/resources
|   `-- application.properties
|-- src/test/java/com/backend/fms
|   `-- FmsApplicationTests.java
|-- k8s
|   |-- namespace.yml
|   |-- mysql-pvc.yml
|   |-- mysql-deployment.yml
|   |-- mysql-service.yml
|   |-- fms-deployment.yml
|   |-- fms-service.yml
|   `-- ingress.yml
|-- Dockerfile
|-- docker-compose.yml
|-- pom.xml
`-- README.md
```

## Architecture

This project follows a layered backend architecture:

- `Controller`: handles HTTP requests and responses
- `Service`: contains business logic
- `Repository`: communicates with the database
- `Entity`: defines database models
- `DTO`: handles structured request payloads
- `Config`: contains security and JWT-related configuration

This separation improves readability, maintainability, and scalability.

## Roles and Access Model

### `VIEWER`

- sign up and log in
- update own profile
- delete own account
- create finance records
- update own records
- delete own records
- view own records

### `ANALYST`

- access analyst endpoints
- inspect records of a selected user
- fetch dashboard summary data

### `ADMIN`

- create new admin users
- create analyst users
- list all users
- delete users

## Authentication and Security

Security is implemented using Spring Security and JWT.

### Current security flow

- users authenticate via `/public/login`
- credentials are verified using `AuthenticationManager`
- on successful login, a JWT token is generated
- protected endpoints require `Authorization: Bearer <token>`
- roles are resolved using `UserDetailsService`
- route protection is handled through Spring Security request matchers

### Security highlights

- passwords are stored in encrypted form using BCrypt
- stateless authentication model
- route-level access restrictions for admin and analyst endpoints

## Database Design

The system mainly uses two entities:

### User

- `id`
- `username`
- `password`
- `role`
- list of finance records

### FinanceRecord

- `id`
- `amount`
- `type` (`INCOME` or `EXPENSE`)
- `category`
- `createdDate`
- `notes`
- associated `user`

### Relationship

- one user can have many finance records
- each finance record belongs to one user

## API Modules

### Public APIs

Base path: `/public`

- `GET /public/health-check`
- `POST /public/signup`
- `POST /public/login`
- `GET /public/logout`

### User APIs

Base path: `/user`

- `POST /user/update`
- `POST /user/delete`

### Record APIs

Base path: `/record`

- `POST /record/create-record`
- `GET /record/my-records`
- `PUT /record/update-records/{recordId}`
- `DELETE /record/{recordId}`

### Admin APIs

Base path: `/admin`

- `POST /admin/new-admin`
- `POST /admin/new-analyst`
- `DELETE /admin/delete-user/{userId}`
- `GET /admin/all-users`

### Analyst APIs

Base path: `/analyst`

- `GET /analyst/record/{userId}`
- `GET /analyst/dashboard/{userId}`

### Dashboard API

Base path: `/dashboard`

- `GET /dashboard/{userId}`

## Sample Request Payloads

### Signup

```json
{
  "username": "ritik",
  "password": "ritik123"
}
```

### Login

```json
{
  "username": "ritik",
  "password": "ritik123"
}
```

### Create Record

```json
{
  "amount": 2500,
  "type": "INCOME",
  "category": "Freelancing",
  "createdDate": "2026-04-05",
  "notes": "Client payment"
}
```

### Update User

```json
{
  "password": "oldPassword",
  "newUsername": "newUsername",
  "newPassword": "newPassword"
}
```

## Dashboard Logic

The dashboard module computes:

- total income
- total expense
- net balance
- savings

This provides a quick financial summary based on stored records.

## Validation

Validation support is included through `spring-boot-starter-validation`.

For finance record creation, the DTO already validates:

- amount must be present
- amount must be greater than zero
- type must be present
- category must be present
- date must be present

## Local Development Setup

### Prerequisites

- Java
- Maven or Maven Wrapper
- MySQL
- Docker Desktop
- Kubernetes cluster such as Docker Desktop Kubernetes or Minikube

### Run with MySQL locally

1. Create a MySQL database named `fms`
2. Update datasource values in `application.properties` or environment variables
3. Start the application

Using Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

The app runs on:

```text
http://localhost:8082
```

## Docker Support

The project includes a multi-stage Docker build.

### Build image

```powershell
docker build -t fms-backend:latest .
```

### Run container

```powershell
docker run -p 8082:8082 fms-backend:latest
```

## Docker Compose Support

The repository includes `docker-compose.yml` for running:

- backend service
- MySQL database

### Start with Docker Compose

```powershell
docker compose up --build
```

## Kubernetes Deployment

The `k8s` folder contains manifests for full deployment.

### Included Kubernetes resources

- namespace
- MySQL deployment
- MySQL service
- MySQL PVC
- backend deployment
- backend service
- ingress

### Deploy to Kubernetes

```powershell
kubectl apply -f k8s\namespace.yml
kubectl apply -f k8s\mysql-pvc.yml
kubectl apply -f k8s\mysql-deployment.yml
kubectl apply -f k8s\mysql-service.yml
kubectl apply -f k8s\fms-deployment.yml
kubectl apply -f k8s\fms-service.yml
kubectl apply -f k8s\ingress.yml
```

### Useful Kubernetes commands

```powershell
kubectl get pods -n fms
kubectl get svc -n fms
kubectl get ingress -n fms
kubectl logs -n fms deployment/fms-backend
```

## Why This Project Stands Out

- combines backend development with security, persistence, and deployment
- demonstrates real-world authentication using JWT
- uses role-based authorization instead of simple open CRUD APIs
- includes containerization and orchestration support
- shows understanding of layered architecture and modular backend design
- covers both development and deployment lifecycle

This makes the project stronger than a basic CRUD assignment because it goes beyond database operations and includes security and infrastructure concerns as well.

## Current Scope and Future Enhancements

Planned or possible next improvements:

- stronger DTO-based API responses
- global exception handling
- improved validation coverage using `@Valid`
- environment-driven secret management
- more complete automated test coverage
- analytics DTOs for cleaner dashboard responses
- API documentation with Swagger/OpenAPI
- CI/CD pipeline integration

## Submission Notes

This project is suitable as a backend internship assessment submission because it demonstrates:

- backend fundamentals
- authentication and authorization
- clean project organization
- database integration
- deployment readiness
- practical DevOps exposure through Docker and Kubernetes

It is especially useful as a portfolio-style backend project because it reflects both coding ability and deployment awareness.

## Author

Developed as a backend-focused finance management system project using Spring Boot, MySQL, JWT, Docker, and Kubernetes.
