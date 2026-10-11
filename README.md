# BookMyShow Backend

A Spring Boot REST API for managing BookMyShow platform data and operations. The current API covers company and theatre administration, halls, movies, employee roles, employee invitations, authentication, and email notifications. It does not yet expose ticket booking or payment endpoints, and it does not include a web frontend.

## Technology

- Java 17
- Spring Boot, Spring MVC, Spring Security, Spring Data JPA
- PostgreSQL
- JWT authentication
- Spring Mail for email notifications
- Maven (Maven Wrapper included)

## Requirements

- JDK 17 or later
- PostgreSQL running locally or reachable from this application

Create the PostgreSQL database configured in `src/main/resources/application.properties` before starting the app. Update that file with the correct local database URL, username, and password. Do not commit real credentials.

## Run the application

From the project directory, run:

```powershell
.\mvnw.cmd spring-boot:run
```

The API listens on `http://localhost:8080` by default. Stop it with `Ctrl+C` in the terminal.

The application has no homepage at `/`. Use an API client such as Postman to send requests to the endpoints below. These routes currently accept `POST` requests, so entering a URL in a browser address bar will not invoke them correctly.

## API routes

| Purpose | Method and path | Access |
|---|---|---|
| Authenticate a user | `POST /api/v1/user/authenticate` | Public |
| Register a theatre company | `POST /api/v1/theatre/company/register` | Public |
| Register a theatre | `POST /api/v1/theatre/register-theatre` | JWT and `CREATE_THEATRE` permission |
| Register a hall and seat mappings | `POST /api/v1/hall/register` | JWT and `CREATE_HALL` permission |
| Register a movie | `POST /api/v1/movie/register-movie` | JWT and movie creation permission |
| Create an employee role | `POST /api/v1/role/create-role` | JWT and `CREATE_ROLE` permission |
| Invite an employee | `POST /api/v1/emp/invite-emp` | JWT and `INVITE_EMPLOYEE` permission |

### Login

Send JSON to `POST http://localhost:8080/api/v1/user/authenticate`:

```json
{
  "email": "registered-user@example.com",
  "password": "the-account-password"
}
```

A successful response contains a JWT:

```json
{
  "token": "<jwt>"
}
```

There are no documented default login credentials. Registering a theatre company creates an admin account and schedules a notification containing its temporary password. Email delivery depends on SMTP settings and notification templates being configured.

### Calling protected routes

In Postman, set the method to `POST`, use the route URL, provide the JSON body required by that route, and add this header:

```text
bmsToken: <jwt returned by login>
```

Send the token as-is; this application reads the token from `bmsToken` and does not expect a `Bearer ` prefix. The token expires after 30 minutes.

## Startup behavior

After Spring finishes starting, `SystemScheduler` invokes `CompanyService.registerInternalCompany()`. It checks the database for the internal BookMyShow company. If found, it logs that the company is already registered; otherwise, it creates the company, its admin employee and role, and schedules an email notification. This happens without an API request.

## Project layout

- `controllers/` — REST endpoints and HTTP request/response handling
- `services/` — business logic, authorization checks, and notification workflows
- `models/` — JPA entities persisted in PostgreSQL
- `repositories/` — Spring Data database access interfaces
- `dtos/` — request and response payloads
- `securites/` — JWT filter, token utilities, and security configuration
- `transformers/` — conversions between request DTOs and entities
- `configurations/` — shared application beans, including mail and async execution
- `schedulers/` — startup actions
- `enums/`, `constants/`, `exceptions/`, `utilities/` — shared types and helpers

## Current development notes

- Employee permissions are role-based and checked against operations stored in the database. The project does not currently include an automatic seed script for the `operations` or notification-template records.
- Email notifications run asynchronously. Missing SMTP credentials or template records can prevent delivery even when the main API operation has been accepted.
- Passwords and application secrets are currently configured insecurely in source. Replace them with environment-managed secrets, hash passwords, and rotate any real credentials before using this application beyond local development.
- The project currently provides management APIs; booking, payment, and customer-facing browsing flows are not exposed yet.


{
"token": "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlTmFtZXMiOlsiQm9vayBNeSBTaG93X1N5c3RlbSBBZG1pbmlzdHJhdG9yIl0sImVtYWlsIjoiaW50ZXJuYWxAYm9va215c2hvdy5jb20iLCJleHAiOjE3OTE2NTI0MTcsImlhdCI6MTc5MTY1MDYxN30.m45pWmQZWINgQ-C1AqigdqfqLrsPzcRnpXoE7mJbzQR7MlO-oRVFB6N3Cm0j5aEU"
}

eyJhbGciOiJIUzM4NCJ9.eyJyb2xlTmFtZXMiOlsiQm9vayBNeSBTaG93X1N5c3RlbSBBZG1pbmlzdHJhdG9yIl0sImVtYWlsIjoiaW50ZXJuYWxAYm9va215c2hvdy5jb20iLCJleHAiOjE3OTE2NTQ3OTYsImlhdCI6MTc5MTY1Mjk5Nn0.-gEKB1vPYzNdd_6UKDZ7UXskuU9RT-mVhCltPwZuc1i3a9Vr1GGUeqFyUfdEg7Re