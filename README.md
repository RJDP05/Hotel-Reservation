# Hotel Reservation System

## 📌 Overview
The **Hotel Reservation System** is a Java-based application that allows users to book, modify, and manage hotel reservations efficiently. This system is built using Java with JDBC for database connectivity, providing a seamless experience for hotel administrators and customers.

## 🚀 Features
- 🔹 User Authentication (Login & Signup)
- 🔹 Room Booking & Cancellation
- 🔹 MySQL Database Integration

## 🛠️ Installation & Setup
### Prerequisites:
Ensure you have the following installed on your system:
- Java Development Kit (JDK 8+)
- MySQL Database and JDBC Driver Jar
- IntelliJ IDEA / Eclipse (Recommended for running the project)

### Steps to Set Up:
1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-username/hotel-reservation.git
   cd hotel-reservation
   ```
2. **Import into IntelliJ IDEA / Eclipse**
3. **Configure Database**
   - Open MySQL and create a database named `hotel_db`.
   - Import the provided `schema.sql` file to set up tables.
4. **Update Database Credentials**
   - Navigate to `src/com/jdbc/hr/DatabaseConfig.java`
   - Modify the following lines with your MySQL credentials:
   ```java
   String URL = "jdbc:mysql://localhost:3306/hotel_db";
   String USER = "your-username";
   String PASSWORD = "your-password";
   ```
5. **Run the Application**
   - Execute `Main.java` from your IDE.


## 🤝 Contributing
Contributions are welcome! To contribute:
1. Fork the repository
2. Create a new branch (`feature-branch`)
3. Commit changes and push (`git push origin feature-branch`)
4. Submit a Pull Request

## 📜 License
This project is licensed under the MIT License. Feel free to use and modify it.

---
🚀 Happy Coding! Feel free to reach out if you have any questions.

