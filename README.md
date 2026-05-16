# 🚀 app-backend — Setup Guide

A Spring Boot backend application. Follow the steps below to get it running on your local machine.

---

## 📋 Prerequisites

Make sure you have the following installed before proceeding:

| Tool  | Recommended Version |

| Java JDK | 17 or higher |

| Maven | Latest stable |

| MySQL Server | 8.0+ |

| MySQL Workbench | Latest stable |

| IntelliJ IDEA| Latest stable (recommended IDE) |

---

## 1. Clone the Repository

```bash
git clone https://github.com/Jhaykeeh/app-backend.git
cd app-backend
```

---

## 2. Open the Project in IntelliJ IDEA

1. Open **IntelliJ IDEA**
2. Click **Open** (or **File > Open**)
3. Navigate to the cloned `app-backend` folder and select it
4. Wait for IntelliJ to index the project and download Maven dependencies

---

## 3. Configure the Database

Open the file at:

```
src/main/resources/application.properties
```

Update the database credentials to match your local MySQL setup:

```properties
spring.datasource.username=root
spring.datasource.password=csit*321
```

> ⚠️ **Important:** Make sure your MySQL server is running and the password matches your local configuration before starting the app.

---

## 4. Run the Application

Open a terminal (CMD or PowerShell) inside the project folder and run:

```bash
mvn spring-boot:run
```

**Example (Windows):**

```bash
cd "C:\Users\Msi\Documents\GitHub\app-backend"
mvn spring-boot:run
```

The server will start on `http://localhost:8080` by default.

---

## 5. Database Access

You can view and manage your database using **MySQL Workbench**.

**Default credentials:**

| Field | Value |
|-------|-------|
| Username | `root` |
| Password | `csit*321` |

---

## ✅ Notes

- Ensure **MySQL is running** before starting the application
- If the database schema doesn't exist yet, Spring Boot may **auto-create the tables** depending on the `spring.jpa.hibernate.ddl-auto` setting in `application.properties`
- If you encounter build errors, try cleaning and reinstalling dependencies first:

```bash
mvn clean install
```
