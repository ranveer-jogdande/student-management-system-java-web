Student Management System (Java + HTML/CSS/JavaScript)

This is a small project where I built a basic student management system using Java for the backend and a simple web interface for the frontend. The main idea of this project was to understand file handling in Java and how a web page can interact with a backend server.

The system allows adding students, viewing all students, searching, and deleting records. All the data is stored in a CSV file (students.csv).

Features

Add student (name, roll number, grade)

View list of all students

Search students by name or roll number

Delete student from the list

Shows total student count

Simple notifications for actions (add or delete)

Clean and minimal UI using basic HTML and CSS

Backend built using Java’s built-in HTTP server

Data stored using plain CSV file handling

Folder Structure

Student_Management/
Backend/
Server.java
Student.java
StudentDatabase.java
students.csv
Frontend/
index.html
script.js
style.css

How to Run the Project

Run the Backend (Java)
Navigate to the Backend folder in terminal and run:
javac *.java
java Server

If it runs correctly, the backend starts on:
http://localhost:8000

Run the Frontend
Open the Frontend folder and double-click index.html.
The page will open in your browser.
Make sure the backend is running before using the web page.

How Data is Stored

The project uses a simple CSV file named students.csv.
Each line follows this format:
name,roll,grade

Example:
Rahul,1,A
Sneha,2,B+

Whenever a student is added or deleted, the CSV file updates automatically.

API Endpoints

GET /students
Returns a JSON list of all students.

POST /add
Body format:
name=<name>&roll=<roll>&grade=<grade>

GET /delete?roll=<roll>
Deletes the student with the specified roll number.

What I Learned

How to build a small backend using Java

How to use HttpServer in Java

Reading and writing files using Java File Handling

Connecting frontend (HTML/JS) with backend through HTTP

Basic DOM operations with JavaScript

Implementing search and delete features

Organizing a small full-stack project

Future Improvements

Edit/Update student details

Better UI and layout

More validation in input fields

Replace CSV with a proper database like SQLite or MySQL

This project helped me understand how a simple backend and frontend can work together using basic tools and concepts.
