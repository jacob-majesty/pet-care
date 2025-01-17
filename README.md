# PetCare Management System

## Overview

The **PetCare Management System** is a full-stack web application designed to streamline the management of veterinarians, pet owners, and appointments. Built with **Spring Boot** for the backend and **React** for the frontend, this system provides a seamless experience for admins, veterinarians, and pet owners to manage their tasks efficiently.

---

## Features

### **Admin**
- **User Management**: Create, update, and delete accounts for veterinarians and pet owners.
- **Schedule Management**: View and manage appointment schedules.
- **System Settings**: Configure system-wide settings and preferences.

### **Veterinarian**
- **Appointment Management**: View, update, and manage appointments.
- **Pet Records**: Maintain and update pet health records.
- **Communication**: Send and receive messages with pet owners.

### **Pet Owner**
- **Appointment Booking**: Book, reschedule, or cancel appointments with veterinarians.
- **Veterinarian Profiles**: View profiles and availability of veterinarians.
- **Pet Health Tracking**: Access and track pet health records.

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

### **Other Tools**
- **Maven**: For dependency management and building the project.
- **Postman**: For testing API endpoints.
- **Git**: For version control and collaboration.

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

## API Endpoints (Backend)

### **User Management**
- `GET /api/users`: Fetch all users.
- `POST /api/users`: Create a new user.
- `PUT /api/users/{id}`: Update a user.
- `DELETE /api/users/{id}`: Delete a user.

### **Appointment Management**
- `GET /api/appointments`: Fetch all appointments.
- `POST /api/appointments`: Create a new appointment.
- `PUT /api/appointments/{id}`: Update an appointment.
- `DELETE /api/appointments/{id}`: Delete an appointment.

### **Pet Records**
- `GET /api/pets`: Fetch all pet records.
- `POST /api/pets`: Create a new pet record.
- `PUT /api/pets/{id}`: Update a pet record.
- `DELETE /api/pets/{id}`: Delete a pet record.

---

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push to the branch.
4. Submit a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

**PetCare Management System**! 🐾
