# Spring Boot Application

There are two Java Spring Boot Microservices for CareConnect.

## 📦 Prerequisites

Make sure you have the following installed:

- [Java JDK 17+](https://adoptopenjdk.net/) (or compatible version)
- [Maven](https://maven.apache.org/) 3.6+
- [PostgreSQL](https://www.postgresql.org/) running on `localhost:5432`

### PostgreSQL Configuration

Ensure a PostgreSQL database is running with the following credentials:

- **Host:** `localhost`
- **Port:** `5432`
- **Database Name:** `careConnect`
- **Username:** `postgres`
- **Password:** `careConnect`

You can create the database manually by running:

```sql
CREATE DATABASE careConnect;
```

## 🔧 Build the user-service

To locate the service run, in the root folder

```bash
cd user-service
```

To build the project and resolve dependencies, run:

```bash
mvn clean install
```
To run the service, run:
```bash
mvn spring-boot:run
```


## 🔧 Build the job-service

To locate the service run, in the root folder

```bash
cd job-service
```

To build the project and resolve dependencies, run:

```bash
mvn clean install
```
To run the service, run:
```bash
mvn spring-boot:run
```
