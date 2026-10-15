# Pub/Sub Registration System

## Overview

This project demonstrates the Publish/Subscribe (Pub/Sub) architecture using Spring Boot and RabbitMQ.

When a user registers, the application publishes a `user.registered` event to a RabbitMQ fanout exchange. The event is delivered to two independent subscribers:

- **Email Subscriber:** Prints a welcome email message.
- **Audit Subscriber:** Logs the user registration timestamp.

## Technologies Used

- Java 21
- Spring Boot
- Spring AMQP
- RabbitMQ
- HTML and CSS
- Maven

## Architecture

```text
User Registration Form
          |
          v
     UserController
          |
          v
      UserService
          |
          v
 RabbitMQ Fanout Exchange
          |
          +-------------------+
          |                   |
          v                   v
     email.queue          audit.queue
          |                   |
          v                   v
     EmailService         AuditService
          |                   |
          v                   v
 Welcome Email Log      Registration Log
```

## Prerequisites

- Java 21
- RabbitMQ 4.x
- Maven or the included Maven wrapper

## Configuration

Configure RabbitMQ in `src/main/resources/application.properties`.

```properties
spring.application.name=pub-sub-registration-system
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

## How to Run

### 1. Start RabbitMQ

From the RabbitMQ installation directory, run:

```bash
./sbin/rabbitmq-server
```

Keep the terminal running.

### 2. Start the Spring Boot Application

Open a terminal in the project root directory and run:

```bash
./mvnw spring-boot:run
```

### 3. Open the Registration Form

Open the following URL in your browser:

http://localhost:8080

Enter the user's name and email address, then submit the form.

## API Endpoint

**Register a user**

- **Method:** `POST`
- **URL:** `/api/register`
- **Parameters:** `name`, `email`

Example request:

```bash
curl -X POST "http://localhost:8080/api/register?name=Teesha&email=teesha@example.com"
```

## Expected Output

After registering a user, the application terminal should display messages similar to these.

**Email Subscriber**

```text
Sending Welcome Email to Teesha (teesha@example.com)
```

**Audit Subscriber**

```text
Logging: New user signed up at 2026-10-15T14:39:30
```

The timestamp will vary for each registration.

## How Pub/Sub Works

1. The user submits the registration form.
2. `UserController` receives the registration request.
3. `UserService` creates a `UserRegisteredEvent` and publishes it to RabbitMQ.
4. The fanout exchange distributes the event to both `email.queue` and `audit.queue`.
5. `EmailService` processes the event and prints the welcome email message.
6. `AuditService` processes the event and logs the registration timestamp.

Both subscribers receive the event independently. The publisher does not call either subscriber directly.

## Limitations

- Email sending is simulated through a terminal message; no actual email is sent.
- Audit information is printed to the terminal and is not stored in a database.
