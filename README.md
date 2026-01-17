# CRUD JEE Application

This document provides a comprehensive overview of the CRUD JEE project, its structure, and instructions on how to build and run it.

## Introduction

This project is a complete Create, Read, Update, Delete (CRUD) application built using the Java Enterprise Edition (JEE) stack. It is designed to be run in a containerized environment using Docker and Docker Compose, providing a fully automated setup for the application server and database.

## Technologies Used

- **Backend:** Java EE, JSF (JavaServer Faces), JPA (Java Persistence API)
- **Frontend:** PrimeFaces
- **Application Server:** Wildfly
- **Database:** MariaDB
- **Build Tool:** Apache Maven
- **Containerization:** Docker, Docker Compose

## Project Structure

This project uses a standard multi-module Maven setup to properly build the final Enterprise Archive (`.ear`) file.

- **`CRUD-parent`:** The root parent POM that manages dependencies and plugins for the modules.
- **`CRUD-war`:** The web module containing all the source code, JSF pages, and web resources. This module is packaged as a `.war` file.
- **`CRUD-ear`:** The enterprise module responsible for packaging the final `.ear` file. It includes the `CRUD-war` module as its web component.

### Directory Tree

```
.
├── CRUD-ear
│   └── target
├── CRUD-war
│   ├── src
│   │   └── main
│   │       ├── java
│   │       ├── resources
│   │       └── webapp
│   └── target
└── wildfly
```

## Prerequisites

Before you begin, ensure you have the following installed on your system:
- Java JDK 1.8 or higher
- Apache Maven
- Docker
- Docker Compose

## How to Build

The project can be built from the root directory using a single Maven command. This command will compile all modules and create the final `CRUD-ear.ear` artifact in the `CRUD-ear/target/` directory.

```bash
mvn clean install
```

## How to Run

The entire application environment is managed by Docker Compose.

1.  **Build and Start the Services**

    From the root directory of the project, run the following command. This will build the Wildfly Docker image (which includes your application) and start all the necessary services.

    ```bash
    docker compose up --build -d
    ```

2.  **Services**

    This will start three services:
    - **`mariadb`:** The MariaDB database container.
    - **`wildfly`:** The Wildfly application server with the `CRUD-ear.ear` application deployed.
    - **`phpmyadmin`:** A web interface for managing the MariaDB database.

## Accessing the Application

Once the services are running, you can access the different parts of the environment via your web browser:

- **Application:** [http://localhost:8080/CRUD/](http://localhost:8080/CRUD/)
- **Wildfly Admin Console:** [http://localhost:9990](http://localhost:9990)
  - **Username:** `admin`
  - **Password:** `admin`
- **phpMyAdmin:** [http://localhost:8081](http://localhost:8081)
  - Use the MariaDB connection details from `docker-compose.yml` to log in (e.g., user `root`, password `root_password`).

## Automated Configuration

The `wildfly/Dockerfile` is designed to automate the entire configuration of the Wildfly server. When the Docker image is built, it automatically:

1.  Creates a management user (`admin`/`admin`).
2.  Downloads the MariaDB JDBC driver and installs it as a Wildfly module.
3.  Configures the MariaDB datasource (`MyDS`) in Wildfly's `standalone.xml` so the application can connect to the database without any manual setup.
