# DormChoice Project Readme
###### by Haiura Andreea-Isabela & Petrea Daniela

### Overview
DormChoice is a microservices-based application designed to facilitate the management of student housing and room allocation. The system consists of two main microservices realized in OpenLiberty and the server app in Payara:

* Dormitories Service (Microservice 1)
    * Manages dormitories and rooms within each dormitory.
    * Provides endpoints for retrieving dormitory information and room details.

* Student Service (Microservice 2)
    * Handles student-related functionalities, including student registration and eligibility.
    * Allows administrators to import a list of eligible students from a JSON file.

* Payara
    * Admins and users have distinct roles and permissions within the application.
    * User authentication ensures secure access to different functionalities based on roles.

### Communication between Payara and Microservices
The Payara application communicates with the microservices using the ClientBuilder functionality provided by JAX-RS. 

### Database Configuration
We have multiple databases: 
* jndiDataSource - manages user-related data, configured in Payara for locally postgres user, user_groups, groups and preference 
* jdbc/postgresql - used in Openliberty for students and dormitories are configured with Docker and running on ports 5435 and 5434, respectively

### Room Allocation Algorithm
The room allocation algorithm takes into account student preferences and available rooms and dormitories. 

### Usage
##### Admin Functionalities:
* Import eligible students through a JSON file - when this happen each eligible student receives an email with his username and password for logging in the application
* Submit final room assignments using the room allocation algorithm - future work

##### Student Functionalities:
* Students receive a password to log in.
* Students can choose their preferred dormitory and room. Dropdown menus dynamically update based on selections using Ajax.
