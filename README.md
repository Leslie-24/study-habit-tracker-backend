# Study Habit Tracker Backend

**Student:** Akariza GASANA Leslie
**Student ID:** 27413
**Project:** Study Habit Tracker (Spring Boot, Spring Data JPA, PostgreSQL)

## What it does
A backend API where students plan study sessions supervised by mentors. Mentors are assigned to a study room and hold several subjects. Sessions move from PLANNED to IN_PROGRESS and end FINISHED or SKIPPED. Study logs are written for FINISHED sessions.

## How to run
1. Install Java 21 and PostgreSQL.
2. In pgAdmin, create a database named `study_habit_tracker`.
3. Open `src/main/resources/application.properties` and set `spring.datasource.username` and `spring.datasource.password` to your own PostgreSQL login.
4. In a terminal in the project folder, run `./mvnw spring-boot:run` (on Windows: `.\mvnw spring-boot:run`).
5. The API runs at http://localhost:8080. Tables are created automatically.
6. Import `study-habit-tracker-midterm.postman_collection.json` into Postman to test the endpoints.