# JobPortal Java Backend

This is a Spring Boot backend for the existing JobPortal frontend.

## Requirements
- Java 17+
- Maven 3.9+

## Run locally
```bash
cd backend
mvn spring-boot:run
```

The API runs on `http://localhost:8080`.

## Main endpoints
- `GET /api/health`
- `GET /api/jobs`
- `GET /api/jobs/{id}`
- `POST /api/jobs`
- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/applications`
- `GET /api/applications`

## Notes
- Current storage is in-memory for fast setup.
- CORS is enabled for `"/api/**"` to make frontend integration easy.
