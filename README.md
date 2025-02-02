# **PetCare Management System**! 🐾

## Overview

The **PetCare Management System** is a full-stack web application designed to streamline the management of veterinarians, pet owners, and appointments. Built with **Spring Boot** for the backend and **React** for the frontend, this system provides a seamless experience for admins, veterinarians, and pet owners to manage their tasks efficiently.

---

## Features

Key Highlights of the Pet Care Appointment Booking System:

- Comprehensive User Management: Implemented secure user registration, login, logout, email verification, and password reset functionalities using Spring Security and JWT.

- Secure Backend with Spring Boot: Developed a scalable and secure backend with Spring Boot 3 and Spring Data JPA for efficient data management and persistence.

- Database Integration: Leveraged Hibernate ORM for seamless interaction with the database, ensuring efficient data queries and storage.

- Frontend Development: Built an intuitive, responsive, and dynamic user interface using React.js, enhancing user experience across devices.

- RESTful API Design: Designed and implemented robust RESTful APIs for seamless communication between the frontend and backend, adhering to best practices.

- Booking Functionality: Developed a core appointment scheduling system allowing users to book, modify, and cancel appointments with pet care professionals.

- Enhanced Security: Integrated advanced authentication and authorization mechanisms to protect user data and system resources.

- Performance Optimization: Focused on optimizing both frontend and backend components for faster loading times and seamless interactions.

---

## Technologies Used

### **Frontend**
- **React**: A JavaScript library for building user interfaces.
- **React Router**: For navigation and routing within the application.
- **Axios**: For making HTTP requests to the backend.
- **Bootstrap/Material-UI**: For styling and responsive design.

### **Backend**
- **Spring Boot**: A Java-based framework for building robust and scalable backend services.
- **Spring Data JPA**: For database operations and ORM (Object-Relational Mapping).
- **Spring Security**: For authentication and authorization.
- **MySQL/PostgreSQL**: As the relational database for storing application data.

---

## Installation and Setup

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Node.js and npm (Node Package Manager)
- MySQL/PostgreSQL database
- Maven

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/petcare-management-system.git
   ```
2. Navigate to the backend directory:
   ```bash
   cd petcare-management-system/backend
   ```
3. Update the `application.properties` file with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/petcare_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Build and run the Spring Boot application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd petcare-management-system/frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React development server:
   ```bash
   npm start
   ```

---

## Usage

1. **Admin Login**:
   - Access the admin dashboard to manage users, schedules, and system settings.

2. **Veterinarian Login**:
   - Log in to view appointments, update pet records, and communicate with pet owners.

3. **Pet Owner Login**:
   - Log in to book appointments, view veterinarian profiles, and track pet health records.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
