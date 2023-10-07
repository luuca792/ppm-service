# Overview
Production process management service for agriculture and aquaculture.
# Required:
```diff
+ Docker.
+ Maven.
+ JDK 17 and above.
```
# Deployment
#### After cloning the repository, do the following steps:
1. Navigate to project root.
```java
{YOUR-WORKDIR}/ppm-service/
```
1. Build project.
```java
mvn clean install
```
3. Build the application's Docker image.
```java
docker build -t ppm-service:{VERSION} .
```
4. Create Docker network (for first-time deployment).
```
docker network create ppm-net
```
5. Run dependency PostgreSQL by launching the official Postgres Docker image.
```java
docker run --network ppm-net -p {DB_HOST_PORT}:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=ctu!@# -e POSTGRES_DB=ppm-db --name=ppm-postgres-db postgres
```
6. Run the application.
```java
docker run --network ppm-net -p {APP_HOST_PORT}:8081 --name=ppm-service ppm-service:{VERSION}
```
