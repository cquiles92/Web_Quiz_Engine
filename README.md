# Web Quiz Engine
A simple REST API to save and access and record Quizzes. 

Description
------------
https://hyperskill.org/projects/91/
A project from the JetBrains Hyperskill academy.

This program can save quizzes and answers to a repository of choice (H2 is being implemented in this current version), and uses Spring Security to secure the API endpoints to only registered users. The following below are the functions of the engine:

GET requests
-------------
"/api/quizzes"

This endpoint allows registered users to get all the available quizzes in the repository with pagination. The pagination default values are the first page with up to 10 quizzes per page.

"/api/quizzes/{id}"

This endpoint allows registered users to get a copy of the quiz by quiz id. The answers are not shown.

"/api/quizzes/completed"

This endpoint returns a record (with pagination using default values) of quizzes successfully solved with timestamps for the user.

POST requests
-------------
"/api/register"

This is an open API endpoint allowing users to register to be able to use the rest of the services provided. Email addresses serve as the username and the password is saved and encrypted using BCrypt.

"/api/quizzes"

This endpoint allows registered users to upload quizzes with answers to the database. 

"/api/quizzes/{id}/solve"

This endpoint allows registered users to upload quiz solutions to the database to then be given validation on the results. Succesful attempts will be saved with a timestamp to the database. 

DELETE requests
----------------
"/api/quizzes/{id}"

This endpoint allows the author of the quiz to be able to delete the quiz from the database.

Technologies
------------
Built using:

*Language: Java
*Frameworks: Spring Boot, Spring MVC, Spring JPA, and Spring Security
*ORM: Hibernate
*Database: H2
*Build Tool: Gradle


Roadmap
----------
Possible create a front end to make the engine and API usable through a web browser with forms and a dynamic page. More features can be added to include updating existing quizzes, as well as a rating system for the quizzes.
